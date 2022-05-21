package Model;


class Terreno {
	protected String image;
	protected int aluguelAtual;
	protected String dono;
	protected int preco;
	protected int precoConstrucao;
	
	String getImage() {
		return image;
	}
	
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
