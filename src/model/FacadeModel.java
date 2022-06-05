package model;

public class FacadeModel {
	private static FacadeModel instance = new FacadeModel();
	
	private Dado dado = Dado.getInstance();
	private BaralhoSorte baralhoSorte = BaralhoSorte.getInstance();
	private Tabuleiro tabuleiro = Tabuleiro.getInstance();
	private CartaSorteReves cartaSorte = CartaSorteReves.getInstance();
	
	
	private FacadeModel(){}
	
	public static FacadeModel getInstance() {
		return instance;
	}
	
	public int[] getDados(int x) {
		int[] resultados = {0,0};
		for(int i=0; i < x; i++) {
			resultados[i] = dado.rodaDado();
		}
		return resultados;
	}
	
	public int getCartaSorteReves() {
		return baralhoSorte.getCarta();
	}
//	public int getTerreno() {}
	

}
