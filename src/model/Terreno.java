package model;


class Terreno {
	protected String nome;
	protected int aluguelAtual;
	protected String dono;
	protected int preco;
	protected int precoConstrucao;
	
	String getDono() {
		return dono;
	}
	
	int getAluguel() {
		return aluguelAtual;
	}
	
	int getPreco() {
		return preco;
	}
	
	void Compra(String dono) {
		this.dono = dono;
	}
	
	void Venda() {
		this.dono = "";
	}

}
