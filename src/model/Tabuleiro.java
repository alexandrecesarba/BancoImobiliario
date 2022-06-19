 package model;

class Tabuleiro {
	private static Tabuleiro instance = new Tabuleiro();
	
	
	private Tabuleiro(){	}
	
	static Tabuleiro getInstance() {
		return instance;
	}
	
	public static Terreno tabuleiro[] = {
			new TerrenoNeutro(TipoTerreno.Inicio),
			new TerrenoAvenida("Leblon", 
					new int[]{6,30,90,270,400,500}, 
					100, 50, CorTerreno.petroleo),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida("Av. Presidente Vargas",
					new int[]{2,10,30,90,160,250},
					60, 50, CorTerreno.rosa),
			new TerrenoAvenida("Av. Nossa S. de Copacabana",
					new int[]{4,20,60,180,320,450},
					60, 50, CorTerreno.rosa),			
			new TerrenoCompanhia("company1",50,200),
			new TerrenoAvenida("Av. Brigadero Faria Lima",
					new int[]{20,100,300,750,925,1100},
					240, 150, CorTerreno.azul),
			new TerrenoCompanhia("company2",50,200),
			new TerrenoAvenida("Av. Rebouças",
					new int[]{18,90,250,70,875,1050},
					220, 150, CorTerreno.azul),
			new TerrenoAvenida("Av. 9 de Julho",
					new int[]{18,90,250,700,875,1050},
					220, 150, CorTerreno.azul),
			new TerrenoNeutro(TipoTerreno.Prisao),
			new TerrenoAvenida("Av. Europa",			
					new int[]{16,80,220,600,800,1000},
					200, 100, CorTerreno.marrom),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida("Rua Augusta",
					new int[]{14,70,200,550,750,950},
					180, 100, CorTerreno.marrom),
			new TerrenoAvenida("Av. Pacaembú",
					new int[]{14,70,200,550,750,950},
					180, 100, CorTerreno.marrom),
			new TerrenoCompanhia("company3",40,150),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida("Interlagos",
					new int[]{35,175,500,1100,1300,1500},
					350, 200, CorTerreno.laranja),
			new TerrenoNeutro(TipoTerreno.LucrosOuDividendos),
			new TerrenoAvenida("Morumbi",
					new int[]{50,200,600,1400,1700,2000},
					400,200,CorTerreno.laranja),
			new TerrenoNeutro(TipoTerreno.ParadaLivre),
			new TerrenoAvenida("Flamengo",
					new int[]{8,40,100,300,450,600},
					120,50,CorTerreno.vermelho),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida("Botafogo",
					new int[]{6,30,90,270,400,500},
					100,100,CorTerreno.vermelho),
			new TerrenoNeutro(TipoTerreno.Imposto),
			new TerrenoCompanhia("company4",40,150),
			new TerrenoAvenida("Av. Brasil",
					new int[]{12,60,180,500,700,900},
					160,100,CorTerreno.amarelo),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida("Av. Paulista",
					new int[]{10,50,150,450,625,750},
					140, 100, CorTerreno.amarelo),
			new TerrenoAvenida("Jardim Europa",
					new int[]{10,50,150,450,625,750},
					140, 100, CorTerreno.amarelo),
			new TerrenoNeutro(TipoTerreno.VaPrisao),
			new TerrenoAvenida("Copacabana",
					new int[]{22,110,330,800,975,1150},
					260, 150, CorTerreno.verde),
			new TerrenoCompanhia("company5",50,200),
			new TerrenoAvenida("Av. Vieira Souto",			
					new int[]{28,150,450,1000,1200,1400},
					320, 200, CorTerreno.verde),			
			new TerrenoAvenida("Av. Atlântica",
					new int[]{26,130,390,900,1100,1275},
					300, 200, CorTerreno.verde),
			new TerrenoCompanhia("company6",50,200),
			new TerrenoAvenida("Ipanema",
					new int[]{26,130,390,900,1100,1275},
					300, 200, CorTerreno.verde),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida("Jardim Paulista",
					new int[]{24,120,360,850,1025,1200},
					280, 150, CorTerreno.petroleo),
			new TerrenoAvenida("Brooklin",
					new int[]{22,110,330,800,975,1150},
					260,150,CorTerreno.petroleo)
		};
	
	Terreno getTerreno(int pos) {
		return tabuleiro[pos];
	}
	
	int getTipoTerreno(int pos) {
		return tabuleiro[pos].tipo;
	}
	
	Terreno getTerrenoPorNome(String terreno) {
		for(int pos=0; pos < tabuleiro.length; pos++) {
			if(tabuleiro[pos].getNome().equals(terreno)) {
				return tabuleiro[pos];
			}
		}
		return tabuleiro[0];
	}
	
	void jogadorFaliu(int jogador) {
		for(int pos=0; pos < tabuleiro.length; pos++) {
			if(tabuleiro[pos].dono == jogador) {
				tabuleiro[pos].Venda();
			}
		}
	}
}
