package model;

import java.util.ArrayList;

class Jogador{
	private CorPino cor;
	private String nome;
	private int posicao = 0;
	boolean naPrisao = false;
	boolean falido = false;
	boolean temCartaSaiaPrisao = false;
	private ArrayList<String> propriedades = new ArrayList<String>();
	private int dinheiro = 4000;
	
	public Jogador(int n) {
		Integer myInt = Integer.valueOf(n);
		String s = myInt.toString();
		this.nome = "Jogador "+ s;
		this.cor = CorPino.values()[n];
	}
	
	CorPino getCor() {
		return this.cor;
	}
	
	String getNome() {
		return this.nome;
	}
	
	void setPosicao(int dado) {
		this.posicao += dado;
	}
	
	void faliu() {
		falido = true;
	}
	
	boolean getFalido() {
		return falido;
	}
	
	void compraPropriedades(String propriedade) {
		this.propriedades.add(propriedade);
	}
	
	void vendaPropriedades(String propriedade) {
		this.propriedades.remove(propriedade);
	}
	
	void vendaPropriedades() {
		this.propriedades.clear();
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
