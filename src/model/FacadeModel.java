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
	
	public int[] getDados() {
		int[] resultados = {0,0};
		for(int i=0; i < 2; i++) {
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
	
	public int getPrecoTerreno(int pos) {
		 return tabuleiro.getTerreno(pos).getPreco();
	}
	
	
	public void setDonoTerreno(int dono,int pos) {
		 tabuleiro.getTerreno(pos).setDono(dono);
	}
	
	
	public String getNomeTerreno(int pos) {
		return tabuleiro.getTerreno(pos).getNome();
	}
	
	public int terrenoPossuiCasa(int pos) {
		TerrenoAvenida aux = (TerrenoAvenida)tabuleiro.getTerreno(pos);
		 return aux.getCasas();
	}
	
	public boolean terrenoPossuiHotel(int pos) {
		TerrenoAvenida aux = (TerrenoAvenida)tabuleiro.getTerreno(pos);
		 return aux.temHotel();
	}
	
	public int getTerrenoConstrucaoPreco(int pos) {
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
		aux.resetAluguel();
	}
	
	public boolean jogoContinua() { // retorna true se ainda há pelo menos dois jogadores não falidos
//		int jogadores_falidos = 0;
//		
//		for(int i=0; i < jogadores.length; i++) {
//			if(jogadores[i].getFalido()) {
//				jogadores_falidos++;
//			}
//		}
//		
//		if((jogadores_falidos - jogadores.length) > 3) {
//			return true;
//		}
//		else {
//			return false;
//		}
		return true;
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
	
	public String getNomeJogador(int jogadorDaVez) {
		return jogadores[jogadorDaVez].getNome();
	}
	
	public String getCorJogador(int jogadorDaVez) {
		return jogadores[jogadorDaVez].getCor().toString();
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
	
	public void resetPosicaoJogador(int jogadorDaVez, int pos) {
		jogadores[jogadorDaVez].resetPosicao(pos);
	}
	
	public int jogadorAndou(int jogadorDaVez, int casas) {
		jogadores[jogadorDaVez].setPosicao(casas);
		int checaPosicao = jogadores[jogadorDaVez].getPosicao();
		if(checaPosicao >= (Tabuleiro.tabuleiro.length-1)) {
			jogadores[jogadorDaVez].resetPosicao(checaPosicao - (Tabuleiro.tabuleiro.length-1));
			System.out.println("checaPosicao");
			System.out.println(checaPosicao);
			System.out.println("Tabuleiro.tabuleiro.length-1");
			System.out.println(Tabuleiro.tabuleiro.length-1);
			System.out.println("checaPosicao >= Tabuleiro.tabuleiro.length-1");
			System.out.println(checaPosicao - (Tabuleiro.tabuleiro.length-1));
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
		return tabuleiro.getTerreno(pos).preco;
	}
	
	public void checaEstadoJogador(int jogadorDaVez) {
		if(jogadores[jogadorDaVez].getDinheiro() < 0) {
			jogadores[jogadorDaVez].faliu();
			tabuleiro.jogadorFaliu(jogadorDaVez);
			jogadores[jogadorDaVez].vendaPropriedades();
			this.atualizaPropriedadesJogadores();
			this.atualizaDinheiroJogadores();
			this.atualizaPropriedadesJogadores();
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
	
	public int quemTemCartaLivreDaPrisão() {
		for(int i=0; i < jogadores.length; i++) {
			if(jogadores[i].temCartaSaiaPrisao) {
				return i;
			}
		}
		return -1;
	}

	public int[] atualizaDinheiroJogadores() {
		int[] dinJ = new int[jogadores.length];
		for(int i=0; i < jogadores.length; i++) {
			dinJ[i] = jogadores[i].getDinheiro();
		}
		return dinJ;
	}

	public boolean[] atualizaHotelPropriedades() {
		TerrenoAvenida aux;
		boolean[] temHotel = new boolean[tabuleiro.tabuleiro.length];
		for(int i=0; i < tabuleiro.tabuleiro.length; i++) {
			aux = (TerrenoAvenida)tabuleiro.getTerreno(i);
			temHotel[i] = aux.temHotel();
		}
		return temHotel;
	}	
	
	public int[] atualizaCasasPropriedades() {
		TerrenoAvenida aux;
		int[] qntdCasas = new int[tabuleiro.tabuleiro.length];
		for(int i=0; i < tabuleiro.tabuleiro.length; i++) {
			aux = (TerrenoAvenida)tabuleiro.getTerreno(i);
			qntdCasas[i] = aux.getCasas();
		}
		return qntdCasas;
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
