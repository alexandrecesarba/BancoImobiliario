package model;


class Terreno {
	protected String nome;
	protected int aluguelAtual;
	protected int dono;
	protected int preco;
	protected int precoConstrucao;
	protected int tipo;
	
	int getDono() {
		return dono;
	}
	
	int getAluguel() {
		return aluguelAtual;
	}
	
	int getPreco() {
		return preco;
	}
	
	void Compra(int dono) {
		this.dono = dono;
	}
	
	void Venda() {
		this.dono = -1;
	}

}
