package simulacaodepedagio;

import java.util.ArrayList;
import java.util.Random;

public class Gerador {
  
 public void GeradorDeChegadasDouble(ArrayList<Double> ListaComum, ArrayList<Double> ListaPasseLivre){
		
		double tempoAnterior = 0;
		double proximaChegada = 0;
		double numeroAleatorio = 0;
		double constanteAuxiliar = -(1/Constantes.TaxaChegadaVeiculos)*60;
		Random gerador = new Random();
		   
		while(tempoAnterior <= 18000){
			
			numeroAleatorio = 1 - gerador.nextDouble(); // numero aleatorio de ]0,1];
			proximaChegada = constanteAuxiliar*Math.log(numeroAleatorio) + tempoAnterior;
			tempoAnterior = proximaChegada;
			int verificadorDePasseLivre = gerador.nextInt(100) + 1;
			if(verificadorDePasseLivre <= Constantes.PorcentagemPassesLivres){
				ListaPasseLivre.add(proximaChegada);
			}else{
				ListaComum.add(proximaChegada);
			}
			
		}
    }
	
	public ArrayList<Double> GeradorTempoDeAtendimento(int numeroDeVeiculos){
		
		ArrayList<Double> saida = new ArrayList<Double>();
		
		double tempoAtendimento = 0;
		double numeroAleatorio = 0;
		double constanteAuxiliar = -(1/Constantes.TaxaSaidaGuicheNormal)*60;// passando para segundos a medida do tempo
		Random gerador = new Random();
		
		
		
		for (int i = 0; i < numeroDeVeiculos; i++) {
			numeroAleatorio = 1 - gerador.nextDouble(); // numero aleatorio de ]0,1];
			tempoAtendimento = constanteAuxiliar*Math.log(numeroAleatorio);
			saida.add(tempoAtendimento);
		}
		
		return saida;
	}
}
