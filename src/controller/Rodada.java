package controller;


class Rodada {
	//private GameController controller = new GameController();
	private int[] jogadores;
	public int jogadorDaVez;
	
	Rodada(int n_jogadores){
		this.jogadores = new int[n_jogadores];
		for(int i=0; i < n_jogadores; i++) {
			this.jogadores[i] = i;
		}
		jogadorDaVez = jogadores[0];
	}
	
	void proximaRodada() {
		if(jogadorDaVez == (jogadores.length-1)) { // jogadorDaVez = 0 a 5  -  jogadores.lenght = 6 (max)
			jogadorDaVez = jogadores[0];
		}
		else {
			jogadorDaVez = jogadores[jogadorDaVez++];
		}
	}
	
	
		
}
