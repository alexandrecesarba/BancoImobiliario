package model;

class CartaSorteReves {
	private CartaSorteReves instance = new CartaSorteReves();

	private CartaSorteReves(){	}
	
	CartaSorteReves getInstance() {
		return this.instance;
	}
	
	int getEfeito(SorteReves carta) {
		switch(carta) {
		case Chance1:
			return 25;			
			
		case Chance2:
			return 150;			
			
		case Chance3:
			return 80;			
			
		case Chance4:
			return 200;			
			
		case Chance5:
			return 50;
					
		case Chance6:
			return 50;		
			
		case Chance7:
			return 100;			
			
		case Chance8:
			return 100;
						
		case Chance9:
			return 0; //saída livre da prisão			
			
		case Chance10:
			return 200;			
			
		case Chance11:
			return 1; //receba 50 de cada jogador			
			
		case Chance12:
			return 45;
						
		case Chance13:
			return 100;			
			
		case Chance14:
			return 100;			
			
		case Chance15:
			return 20;			
	
		case Chance16:
			return -15;		
			
		case Chance17:
			return -25;
			
		case Chance18:
			return -45;			
			
		case Chance19:
			return -30;			
			
		case Chance20:
			return -100;
			
		case Chance21:
			return -100;
			
		case Chance22:
			return -40;
			
		case Chance23:
			return -1; //va para a prisao
			
		case Chance24:
			return -30;
			
		case Chance25:
			return -50;
			
		case Chance26:
			return -25;
		
		case Chance27:
			return -30;

		case Chance28:
			return -45;
			
		case Chance29:
			return -50;

		case Chance30:
			return -50;
			
		}
		return 8888;
		
	}
	
	
	
	
	


}
