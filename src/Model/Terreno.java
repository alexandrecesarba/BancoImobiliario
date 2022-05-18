package Model;


class Terreno {
	protected String image;
	protected int aluguelAtual;
	protected String dono;
	//protected int preco[];
	
	String getImage() {
		return image;
	}
	
	int getAluguel() {
		return aluguelAtual;
	}
	
	void Compra(String dono) {
		this.dono = dono;
	}
	
	void Venda(String dono) {
		this.dono = "";
	}
	

}
