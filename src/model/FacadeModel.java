package model;

public class FacadeModel {
	private static FacadeModel instance = new FacadeModel();
	
	private Dado dado = Dado.getInstance();
	private BaralhoSorte baralhoSorte = BaralhoSorte.getInstance();
	private Tabuleiro tabuleiro = Tabuleiro.getInstance();
	private CartaSorteReves cartaSorte = CartaSorteReves.getInstance();
	
	private FacadeModel(){}
	
	static FacadeModel getInstance() {
		return instance;
	}
	
//	public int getCartaSorteReves() {
//		
//	}
//	public int getTerreno() {}
	

}
