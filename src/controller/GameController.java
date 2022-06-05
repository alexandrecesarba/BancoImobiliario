package controller;

import model.FacadeModel;

public class GameController {
	//rodada
	//jogador
	//roda dado
	//caiu dupla? sim, n�o
	//n�o-> anda
	//sim-> roda de novo, caiu dupla? n�o-> anda, sim? tira de novo, se tiraer uma terceira dupla, vai para a pris�o;
	//caiu num terreno-> terreno companhia ou avenida pode comprar, ou puxa carta, ou faz nada.
	// caso o dono do terreno seja ele, pode construir - caso j� tenha casa, pode construir um hotel.
	// encerra jogo;
	
	private static GameController instance = new GameController();
	private FacadeModel model = FacadeModel.getInstance();
	
	private GameController(){}
	
	public static GameController getInstance() {
		return instance;
	}
	
	public int getCartaSorte() {
		return model.getCartaSorteReves();
	}
	
	public int[] rodaDados(int x) {
		int[] dados= model.getDados(x);
		return dados;
	}
	
	void encerraJogo() {
		
	}
	
}
