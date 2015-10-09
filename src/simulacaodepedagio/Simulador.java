package simulacaodepedagio;

import java.util.ArrayList;

public class Simulador {
	
	public int simulacao() {
		
		ArrayList<ArrayList<Double>> filasChegadas = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> tempoAtendimentos = new ArrayList<ArrayList<Double>>();
		Gerador gerador = new Gerador();
		ArrayList<Double> filaPasseLivre = new ArrayList<Double>();
		double tempoNaFila = 0;
		
		//preparar listas de chegadas e tempo de atendimentos
		for (int i = 0; i < Constantes.QuantidadeDeGuichesComuns; i++) {
			ArrayList<Double> chegada = new ArrayList<Double>();
			gerador.GeradorDeChegadasDouble(chegada, filaPasseLivre);
			filasChegadas.add(chegada);
			ArrayList<Double> atendimento = gerador.GeradorTempoDeAtendimento(chegada.size());
			tempoAtendimentos.add(atendimento);
		
		}
		
		boolean falha = false;
		
		// simular cada fila
		while (filasChegadas.size() > 0 && falha==false) {
			double horaChegada = 0;
			double horaGuiche = 0;
			
			ArrayList<Double> filaAtual = filasChegadas.remove(0);
		
			ArrayList<Double> atendimentos = tempoAtendimentos.remove(0);
			
			while (filaAtual.size() > 0 && falha==false) {
				
				horaChegada = filaAtual.remove(0);
				tempoNaFila = horaGuiche - horaChegada;
				if (tempoNaFila < 0) tempoNaFila = 0;
				
				if(horaChegada >= horaGuiche){
					horaGuiche = horaChegada + atendimentos.remove(0);
				}else{
					horaGuiche = horaGuiche + atendimentos.remove(0);
				}
				
				double ultimaChegada = 0;
				int tamanhoDaFila = 0;
				while(ultimaChegada < horaGuiche && ultimaChegada <= Constantes.TempoDeSimulação){
				
					if(filaAtual.size() > tamanhoDaFila){
						ultimaChegada = filaAtual.get(tamanhoDaFila);
						tamanhoDaFila++;
					}else{
						ultimaChegada = Constantes.TempoDeSimulação+1;
					}					
				}
				
				if(tamanhoDaFila > Constantes.TamanhoMaximoPermitidoDaFila) {
					falha = true;
					//System.out.println("tamanho maximo da fila EXCEDIDO: " + tamanhoDaFila);
					//System.out.println("tempo para ocorrência: " + horaGuiche/60);
					return 1;
				}
				if(tempoNaFila > Constantes.TempoMaximoPermitidoNaFila) {
					falha = true;
					//System.out.println("tempo maximo na fila EXCEDIDO: " + tempoNaFila/60);
					return 2;
				}			
			}
			
		}
		//System.out.println("tempo maximo na fila: " + tempoNaFila/60);
		return 0;
	}
	
	public static void main(String[] args) {
		
		int sucesso = 0;
		int tamanhoFila = 0;
		int tempoEspera = 0;
		Simulador simulador = new Simulador();
		int resultado = 0;
		for (int i = 0; i < Constantes.NumeroDeSimulacoes; i++) {
			resultado = simulador.simulacao();
			switch (resultado) {
			case 1:
				tamanhoFila++;
				break;
				
			case 2:
				tempoEspera++;
				break;

			default:
				sucesso++;
				break;
			}
		}
		
		System.out.println("numero de sucessos: " + sucesso);
		System.out.println("numero de falhas por tamanho da fila: " + tamanhoFila);
		System.out.println("numero de falhas por tempo de espera: " + tempoEspera);
	}
}
