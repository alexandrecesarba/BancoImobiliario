package Model;

import java.util.ArrayList;

class Jogador{
	enum Cor {
		vermelho, azul,laranja,amarelo,roxo,preto
	}
	private Cor cor;
	private String image;
	private int posicao = 0;
	private boolean naPrisao = false;
	private boolean temCartaSaiaPrisao = false;
	private ArrayList<String> propriedades = new ArrayList<String>();
	private int dinheiro = 4000;
	
	public Jogador(int pos) {
		Integer myInt = Integer.valueOf(pos);
		String s = myInt.toString();
		this.image = "pin" + s;
		this.cor = Cor.values()[pos];
	}
	
	String getImage() {
		return this.image;
	}
	
	Cor getCor() {
		return this.cor;
	}
	
	void setPosicao(int dado) {
		this.posicao += dado;
	}
	
	void compraPropriedades(String propriedade) {
		this.propriedades.add(propriedade);
	}
	
	void vendaPropriedades(String propriedade) {
		this.propriedades.remove(propriedade);
	}
	
	void mudaEstadoPreso() {
		this.naPrisao = !naPrisao;
	}
	
	void mudaEstadoCartaSaiaPrisao() {
		this.temCartaSaiaPrisao = !temCartaSaiaPrisao;
	}
	
	int getPosicao(int dado) {
		return posicao;
	}
	
	int getDinheiro() {
		return dinheiro;
	}
	
	void setDinheiro(int valor) {
		this.dinheiro += valor;
	}
	
	ArrayList<String> getPropriedades() {
		if(this.propriedades.isEmpty()) {return null;}
		return this.propriedades;
	}
		
}
