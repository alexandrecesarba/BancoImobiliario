package model;

class TerrenoAvenida extends Terreno{
	private int aluguel[];
	private CorTerreno cor;
	private int qntdCasa = 0;
	private boolean hotel = false;
	
	TerrenoAvenida(String nome, int aluguel[], int preco, int precoConstrucao, CorTerreno cor) {
		this.nome = nome;
		this.aluguel = aluguel;
		this.aluguelAtual = aluguel[0];
		this.preco = 220;
		this.precoConstrucao = 150;
		this.cor = cor;
	}
	
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
			aluguelAtual += aluguel[5];
		}
		if(qntdCasa != 0) {
			for(int i=1; i < qntdCasa; i++) {
				aluguelAtual += aluguel[i];
			}
		}	
	}

}
