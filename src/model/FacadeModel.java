package model;

import java.util.ArrayList;

public class FacadeModel {
	private Dado dado = Dado.getInstance();
	private BaralhoSorte baralhoSorte = BaralhoSorte.getInstance();
	private Tabuleiro tabuleiro = Tabuleiro.getInstance();
	private CartaSorteReves cartaSorte = CartaSorteReves.getInstance();
	private Jogador[] jogadores;
	
	public FacadeModel(int n_jogadores){
		this.jogadores = new Jogador[n_jogadores];
		for(int i=0; i < n_jogadores; i++) {
			jogadores[i]= new Jogador(i);
		}
		
	}
	
	public int[] getDados(int x) {
		int[] resultados = {0,0};
		for(int i=0; i < x; i++) {
			resultados[i] = dado.rodaDado();
		}
		return resultados;
	}
	
	public int getCartaSorteReves() {
		return baralhoSorte.getCarta();
	}
	
	public int getEfeitoCarta(int carta) {
		return cartaSorte.getEfeito(carta);
	}
	
	public int getAluguelTerreno(int pos) {
		return tabuleiro.getTerreno(pos).aluguelAtual;
	}
	
	public int getTipoTerreno(int pos) {
		return tabuleiro.getTipoTerreno(pos);
	}
	
	public int getDonoTerreno(int pos) {
		 return tabuleiro.getTerreno(pos).dono;
	}
	
	public int terrenoPossuiCasa(int pos) {
		TerrenoAvenida aux = (TerrenoAvenida)tabuleiro.getTerreno(pos);
		 return aux.getCasas();
	}
	
	public boolean terrenoPossuiHotel(int pos) {
		TerrenoAvenida aux = (TerrenoAvenida)tabuleiro.getTerreno(pos);
		 return aux.temHotel();
	}
	
	public int getTerrenoConstrucaoPreço(int pos,int tipo) {
		TerrenoAvenida aux = (TerrenoAvenida)tabuleiro.getTerreno(pos); 
		return aux.getPrecoConstrucao();
	}
	
	public void construirNoTerreno(int pos,int tipo) {
		TerrenoAvenida aux = (TerrenoAvenida)tabuleiro.getTerreno(pos); // checar se esse aux realmente preenche os dados no tabuleiro[pos]
		if(tipo == 0) {
			aux.adicionaCasa();
		}else {
			aux.setHotel();
		}
	}
	
	public boolean jogoContinua() { // retorna true se ainda há pelo menos dois jogadores não falidos
		int jogadores_falidos = 0;
		
		for(int i=0; i < jogadores.length; i++) {
			if(jogadores[i].getFalido()) {
				jogadores_falidos++;
			}
		}
		
		if((jogadores_falidos - jogadores.length) > 3) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String ranking() { // retorna true se ainda há pelo menos dois jogadores não falidos
		String rank = "";
		for(int i=0; i < jogadores.length-1; i++) {
			if(jogadores[i].getDinheiro() >= jogadores[i+1].getDinheiro()) {
				rank.concat(jogadores[i].getNome()+ " ");
			}
			else {
				rank.concat(jogadores[i+1].getNome()+ " ");
			}
		}
		return rank;
	}
	
	
	public void mudaEstadoCartaLivreDaPrisão(int jogadorDaVez) {
		jogadores[jogadorDaVez].mudaEstadoCartaSaiaPrisao();
		if(jogadores[jogadorDaVez].temCartaSaiaPrisao) {
			baralhoSorte.removeCartaLivrePrisao();
		}
		else {
			baralhoSorte.adicionaCartaLivrePrisao();
		}
	}
	
	public boolean temCartaLivreDaPrisão(int jogadorDaVez) {
		return jogadores[jogadorDaVez].temCartaSaiaPrisao;
	}
	
	public boolean mudaPrisãoJogador(int jogadorDaVez) {
		return jogadores[jogadorDaVez].mudaEstadoPreso();
	}
	
	public boolean jogadorPreso(int jogadorDaVez) {
		return jogadores[jogadorDaVez].naPrisao;
	}
	
	public int jogadorAndou(int jogadorDaVez, int casas) {
		jogadores[jogadorDaVez].setPosicao(casas);
		int checaPosicao = jogadores[jogadorDaVez].getPosicao();
		if(checaPosicao == Tabuleiro.tabuleiro.length-1) {
			jogadores[jogadorDaVez].setPosicao(0);
		}
		return jogadores[jogadorDaVez].getPosicao();
	}
	
	public void transacaoJogador(int jogadorDaVez, int preco) {
		jogadores[jogadorDaVez].setDinheiro(preco);
	}
	
	public int jogadorComprou(int jogadorDaVez, int pos) {
		tabuleiro.getTerreno(pos).Compra(jogadorDaVez);
		jogadores[jogadorDaVez].setDinheiro(-tabuleiro.getTerreno(pos).preco);
		jogadores[jogadorDaVez].compraPropriedades(tabuleiro.getTerreno(pos).nome);
		return jogadores[jogadorDaVez].getDinheiro();
	}
	
	public boolean checaEstadoJogador(int jogadorDaVez) {
		if(jogadores[jogadorDaVez].getDinheiro() > 0) {
			return true;
		}
		else {
			jogadores[jogadorDaVez].faliu();
			tabuleiro.jogadorFaliu(jogadorDaVez);
			jogadores[jogadorDaVez].vendaPropriedades();
			return false;
		}
	}
	
	public boolean jogadorFalido(int jogadorDaVez) {
		return jogadores[jogadorDaVez].getFalido();
	}
	
	public boolean[] atualizaJogadoresPresos() {
		boolean[] jogadoresPresos = new boolean[jogadores.length];
		for(int i=0; i < jogadores.length; i++) {
			jogadoresPresos[i] = jogadores[i].naPrisao;
		}
		return jogadoresPresos;
	}
	
	public int[] atualizaPosJogadores() {
		int[] posJ = new int[jogadores.length];
		for(int i=0; i < jogadores.length; i++) {
			posJ[i] = jogadores[i].getPosicao();
		}
		return posJ;
	}

	public int[] atualizaDinheiroJogadores() {
		int[] dinJ = new int[jogadores.length];
		for(int i=0; i < jogadores.length; i++) {
			dinJ[i] = jogadores[i].getDinheiro();
		}
		return dinJ;
	}

	public ArrayList<String>[] atualizaPropriedadesJogadores() {
		@SuppressWarnings("unchecked")
		ArrayList<String>[] propJ = new ArrayList[jogadores.length];
		for(int i=0; i < jogadores.length; i++) {
			propJ[i] = jogadores[i].getPropriedades();
		}
		return propJ;
	}
}
