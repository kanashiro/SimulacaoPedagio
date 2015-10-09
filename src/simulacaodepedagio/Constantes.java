package simulacaodepedagio;

public class Constantes {
	
	
	public static final double TaxaSaidaGuicheNormal = 5; // atendimentos por minuto (poisson)

	public static final double TaxaSaidaGuichePasseLivre = 5; // 5 atendimentos por minuto (deterministico)
	
	public static final double TamanhoMaximoPermitidoDaFila = 40; // maximo de veículos na fila
	
	public static final double PorcentagemPassesLivres = 20;
	
	public static final double QuantidadeDeGuichesComuns = 18;
	
	public static final double QuantidadeDeGuichesPasseLivre = 1;
	
	public static final double TaxaChegadaVeiculos = 80/QuantidadeDeGuichesComuns; //  carros por minuto (poisson)
	
	public static final double TempoMaximoPermitidoNaFila = 20*60; // 20 min
	
	public static final double TempoDeSimulação = 5*60*60; // 18000 segundos
	
	public static final double NumeroDeSimulacoes = 10000; // 
}
