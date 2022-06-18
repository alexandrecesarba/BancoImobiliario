package model;

class TerrenoCompanhia extends Terreno{
	TerrenoCompanhia(String nome, int aluguelAtual, int preco){
		this.aluguelAtual = aluguelAtual;
		this.preco = preco;
		this.nome = nome;
		this.tipo = 1;
		this.dono = -1;
	}

}
