package model;


class Terreno {
	protected String nome;
	protected int aluguelAtual;
	protected int dono = -1;
	protected int preco;
	protected int precoConstrucao;
	protected int tipo;
	
//	void setAluguel() {}
//	void setHotel() {}
//	void setQntdCasas() {}
//	int getPrecoConstrucao() {}
//	void setHotel() {}
//	void adicionaCasa() {}
//	void getCasas() {}
//	void temHotel() {}
//	void setAluguel() {}
	
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
