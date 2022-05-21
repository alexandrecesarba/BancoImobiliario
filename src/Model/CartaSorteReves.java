package Model;

class CartaSorteReves {
	enum SorteReves {
		Chance1, Chance2, Chance3, Chance4, Chance5,
		Chance6, Chance7, Chance8, Chance9, Chance10, 
		Chance11, Chance12, Chance13, Chance14, Chance15,
		Chance16, Chance17, Chance18, Chance19, Chance20,
		Chance21 ,Chance22, Chance23, Chance24, Chance25,
		Chance26, Chance27, Chance28, Chance29, Chance30
	}
	
	private String image;
	private int efeito;
	
	String getImage() {
		return image;
	}
	
	int getEfeito(SorteReves carta) {
		switch(carta) {
		case Chance1:
			efeito = 25;
			image = "chance1";
			break;
			
		case Chance2:
			efeito = 150;
			image = "chance2";
			break;
			
		case Chance3:
			efeito = 80;
			image = "chance3";
			break;
			
		case Chance4:
			efeito = 200;
			image = "chance4";
			break;
			
		case Chance5:
			efeito = 50;
			image = "chance5";
			break;
			
		case Chance6:
			efeito = 50;
			image = "chance6";
			break;
			
		case Chance7:
			efeito = 100;
			image = "chance7";
			break;
			
		case Chance8:
			efeito = 100;
			image = "chance8";
			break;
			
		case Chance9:
			efeito = 0; //saída livre da prisão
			image = "chance9";
			break;
			
		case Chance10:
			efeito = 200;
			image = "chance10";
			break;
			
		case Chance11:
			efeito = 1; //receba 50 de cada jogador
			image = "chance11";
			break;
			
		case Chance12:
			efeito = 45;
			image = "chance12";
			break;
			
		case Chance13:
			efeito = 100;
			image = "chance13";
			break;
			
		case Chance14:
			efeito = 100;
			image = "chance14";
			break;
			
		case Chance15:
			efeito = 20;
			image = "chance15";
			break;
			
		case Chance16:
			efeito = -15;
			image = "chance16";
			break;
			
		case Chance17:
			efeito = -25;
			image = "chance17";
			break;
			
		case Chance18:
			efeito = -45;
			image = "chance18";
			break;
			
		case Chance19:
			efeito = -30;
			image = "chance19";
			break;
			
		case Chance20:
			efeito = -100;
			image = "chance20";
			break;
			
		case Chance21:
			efeito = -100;
			image = "chance21";
			break;
			
		case Chance22:
			efeito = -40;
			image = "chance22";
			break;
			
		case Chance23:
			efeito = -1; //va para a prisao
			image = "chance23";
			break;
			
		case Chance24:
			efeito = -30;
			image = "chance24";
			break;
			
		case Chance25:
			efeito = -50;
			image = "chance25";
			break;
			
		case Chance26:
			efeito = -25;
			image = "chance26";
			break;
		case Chance27:
			
			efeito = -30;
			image = "chance27";
			break;
			
		case Chance28:
			efeito = -45;
			image = "chance28";
			break;
			
		case Chance29:
			efeito = -50;
			image = "chance29";
			break;
			
		case Chance30:
			efeito = -50;
			image = "chance30";
			break;
		}
		return efeito;
		
	}
	
	
	
	
	


}
