package model;

class CartaSorteReves {
	private static CartaSorteReves instance = new CartaSorteReves();

	private CartaSorteReves(){	}
	
	static CartaSorteReves getInstance() {
		 return instance;
	}
	
	// 0 -> saída livre da prisão
	// 1 -> receba 50 de cada jogador
	//-1 -> vá para a prisão
	
	private int efeito[] = new int[]{25,150,80,200,50,50,100,100,0,200,1,45,100,100,20,-15,-25,-45,-30,-100,-100,-40,-1,-30,-50,-25,-30,-45,-50,-50};
	
	int getEfeito(int carta) {
		return efeito[carta];
	}

}
