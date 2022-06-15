package model;

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
	
	public void mudaPrisãoJogador(int jogadorDaVez) {
		jogadores[jogadorDaVez].mudaEstadoPreso();
	}
	
	public boolean jogadorPreso(int jogadorDaVez) {
		return jogadores[jogadorDaVez].naPrisao;
	}
	
	public void jogadorAndou(int jogadorDaVez, int casas) {
		jogadores[jogadorDaVez].setPosicao(casas);
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
			// precisa vender automaticamente todas as propriedades do jogador que faliu;
			jogadores[jogadorDaVez].vendaPropriedades();
			return false;
		}
	}
	
	public boolean jogadorFalido(int jogadorDaVez) {
		return jogadores[jogadorDaVez].getFalido();
	}
	

}
