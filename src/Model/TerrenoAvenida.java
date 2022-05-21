package Model;

class TerrenoAvenida extends Terreno{
	enum CorTerreno{
		verde, laranja, marrom, azul, amarelo, rosa, vermelho, petroleo;
	}
	enum Territorio {
		Av9Julho, AvAtlantica, AvBrasil, AvBrigadeiro, AvEuropa,
		AvNossaSraCopacabana, AvPacaembu, AvPaulista, AvPresidenteVargas, AvReboucas, 
		AvVieiraSouto, Botafogo, Brooklin, Copacabana, Flamengo,
		Interlagos, Ipanema, JardimEuropa, JardimPaulista, Leblon,
		Morumbi, RuaAugusta
	}
	
	private int aluguel[];
	private CorTerreno cor;
	private int qntdCasa = 0;
	private boolean hotel = false;
	
	CorTerreno getCor() {
		return cor;
	}
	
	int getPrecoConstrucao() {
		return precoConstrucao;
	}
	
	void setHotel() {
		this.hotel = true;
	}
	
	void adicionaCasa() {
		this.qntdCasa += 1;
	}
	
	void setAluguel() {
		if(hotel) {
			aluguelAtual += aluguel[4];
		}
		if(qntdCasa != 0) {
			for(int i=1; i < qntdCasa; i++) {
				aluguelAtual += aluguel[i];
			}
		}	
	}
	
	public TerrenoAvenida(Territorio carta) {
		switch(carta) {
		case Av9Julho:
			aluguel = new int[]{90,250,700,875,1050};
			aluguelAtual = 18;
			preco = 220;
			precoConstrucao = 150;
			image = "Av. 9 de Julho.png";
			cor = CorTerreno.azul;
			break;
			
		case AvAtlantica:
			aluguel = new int[]{130,390,900,1100,1275};
			aluguelAtual = 26;
			preco = 300;
			precoConstrucao = 200;
			image = "Av. Atlântica.png";
			cor = CorTerreno.verde;
			break;
			
		case AvBrasil:
			aluguel = new int[]{60,180,500,700,900};
			aluguelAtual = 12;
			preco = 160;
			precoConstrucao = 100;
			image = "Av. Brasil.png";
			cor = CorTerreno.amarelo;
			break;
			
		case AvBrigadeiro:
			aluguel = new int[]{100,300,750,925,1100};
			aluguelAtual = 20;
			preco = 240;
			precoConstrucao = 150;
			image = "Av. Brigadeiro Faria Lima.png";
			cor = CorTerreno.azul;
			break;
			
		case AvEuropa:
			aluguel = new int[]{80,220,600,800,1000};
			aluguelAtual = 16;
			preco = 200;
			precoConstrucao = 100;
			image = "Av. Europa.png";
			cor = CorTerreno.marrom;
			break;
			
		case AvNossaSraCopacabana:
			aluguel = new int[]{4,20,60,180,320,450};
			preco = 60;
			precoConstrucao = 50;
			image = "Av. Nossa S. de Copacabana.png";
			cor = CorTerreno.rosa;
			break;
			
		case AvPacaembu:
			aluguel = new int[]{70,200,550,750,950};
			aluguelAtual = 14;
			preco = 180;
			precoConstrucao = 100;
			image = "Av. Pacaembú.png";
			cor = CorTerreno.marrom;
			break;
			
		case AvPaulista:
			aluguel = new int[]{50,150,450,625,750};
			aluguelAtual = 10;
			preco = 140;
			precoConstrucao = 100;
			image = "Av. Paulista.png";
			cor = CorTerreno.amarelo;
			break;
			
		case AvPresidenteVargas:
			aluguel = new int[]{10,30,90,160,250};
			aluguelAtual = 2;
			preco = 60;
			precoConstrucao = 50;
			image = "Av. Presidente Vargas.png";
			cor = CorTerreno.rosa;
			break;
			
		case AvReboucas:
			aluguel = new int[]{90,250,70,875,1050};
			aluguelAtual = 18;
			preco = 220;
			precoConstrucao = 150;
			image = "Av. Rebouças.png";
			cor = CorTerreno.azul;
			break;
			
		case AvVieiraSouto:
			aluguel = new int[]{150,450,1000,1200,1400};
			aluguelAtual = 28;
			preco = 320;
			precoConstrucao = 200;
			image = "Av. Vieira Souto.png";
			cor = CorTerreno.verde;
			break;		
		case Botafogo:
			aluguel = new int[]{30,90,270,400,500};
			aluguelAtual = 6;
			preco = 100;
			precoConstrucao = 50;
			image = "Botafogo.png";
			cor = CorTerreno.vermelho;
			break;
			
		case Brooklin:
			aluguel = new int[]{110,330,800,975,1150};
			aluguelAtual = 22;
			preco = 260;
			precoConstrucao = 150;
			image = "Brooklin.png";
			cor = CorTerreno.petroleo;
			break;
			
		case Copacabana:
			aluguel = new int[]{110,330,800,975,1150};
			aluguelAtual = 22;
			preco = 260;
			precoConstrucao = 150;
			image = "Copacabana.png";
			cor = CorTerreno.verde;
			break;
			
		case Flamengo:
			aluguel = new int[]{40,100,300,450,600};
			aluguelAtual = 8;
			preco = 120;
			precoConstrucao = 50;
			image = "Flamengo.png";
			cor = CorTerreno.vermelho;
			break;
			
		case Interlagos:
			aluguel = new int[]{175,500,1100,1300,1500};
			aluguelAtual = 35;
			preco = 350;
			precoConstrucao = 200;
			image = "Interlagos.png";
			cor = CorTerreno.laranja;
			break;
			
		case Ipanema:
			aluguel = new int[]{130,390,900,1100,1275};
			aluguelAtual = 26;
			preco = 300;
			precoConstrucao = 200;
			image = "Ipanema.png";
			cor = CorTerreno.verde;
			break;
			
		case JardimEuropa:
			aluguel = new int[]{50,150,450,625,750};
			aluguelAtual = 10;
			preco = 140;
			precoConstrucao = 100;
			image = "Jardim Europa.png";
			cor = CorTerreno.amarelo;
			break;
			
		case JardimPaulista:
			aluguel = new int[]{120,360,850,1025,1200};
			aluguelAtual = 24;
			preco = 280;
			precoConstrucao = 150;
			image = "Jardim Paulista.png";
			cor = CorTerreno.petroleo;
			break;
			
		case Leblon:
			aluguel = new int[]{30,90,270,400,500};
			aluguelAtual = 6;
			preco = 100;
			precoConstrucao = 50;
			image = "Leblon.png";
			cor = CorTerreno.petroleo;
			break;
			
		case Morumbi:
			aluguel = new int[]{200,600,1400,1700,2000};
			aluguelAtual = 50;
			preco = 400;
			precoConstrucao = 200;
			image = "Morumbi.png";
			cor = CorTerreno.laranja;
			break;
			
		case RuaAugusta:
			aluguel = new int[]{70,200,550,750,950};
			aluguelAtual = 14;
			preco = 180;
			precoConstrucao = 100;
			image = "Rua Augusta.png";
			cor = CorTerreno.marrom;
			break;
		}
	}

}
