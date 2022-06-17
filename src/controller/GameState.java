package controller;

import java.util.ArrayList;
import java.util.Collections;

//Let's set the gameState string format!
// numero de jogadores /n
// pos1 14 dinheiro1 3246337 propriedades1 {wsajhkawsju} /n
// pos2 dinheiro propriedades preso /n
// pos3 dinheiro propriedades /n --> para os jogadores que não estiverem no jogo não há linhas
// jogadorDaVez 2
// dados 66 //resultado do dado1 + resultado do dado 2
// qtdDuplasNoDado 0 //int qntd de duplas tiradas no dado nesta rodada
// total_dados 12 //int total dos dados
// carta 23 //int cartaSorte
// dinheiro do banco
// ranking 
//

public class GameState {
	private static GameState instance = new GameState();
	private Integer myInt;
	private String aux;
	private String aux2;
	
	static GameState getInstance() {
		return instance;
	}
//	{
//	    ArrayList<String> test1 = new ArrayList<String>();
//	    ArrayList<String> test2 = new ArrayList<String>();
//	    ArrayList<String> test3 = new ArrayList<String>();
//	    test1.add("AvBB");
//	    test2.add("AvBB");
//	    test3.add("AvBB");
//	    @SuppressWarnings("unchecked")
//		ArrayList<String>[] test = new ArrayList[3];
//	    test[0] = test1;
//	    test[1] = test2;
//	    test[2] = test3;
//	    setState(3, new int[]{20,21,23}, new int[]{2000,2100,2300}, test, new int[]{6,6} , 12, 29, 1000, "");
//	}	
	private GameState() {}
	
	String setState(int n_jogadores, int[] posJ, int[] dinheiroJ, ArrayList<String>[] propriedadesJ, boolean[] jogadorPreso,int jogadorDaVez, int[] dados, int qtdDuplasNoDado,int total_dados, int cartaSorte, int banco, String rank) {
		String state = "";
		this.myInt = Integer.valueOf(n_jogadores);
		this.aux = myInt.toString();
		state = state.concat(aux+"\n");
		for(int i=0; i < n_jogadores; i++) {
			this.myInt = Integer.valueOf(i);
			this.aux2 = myInt.toString();
			
			this.myInt = Integer.valueOf(posJ[i]);
			this.aux = myInt.toString();
			
			state = state.concat("pos" + aux2 +" "+ aux);
			
			this.myInt = Integer.valueOf(dinheiroJ[i]);
			this.aux = myInt.toString();
			state = state.concat("dinheiro" + aux2 +" "+aux);
			state = state.concat("propriedades"+aux2+" "+propriedadesJ[i].toString() +" \n");
			state = state.concat("preso"+aux2+" "+String.valueOf(jogadorPreso[i])+" \n");
//			state = state.concat("propriedades" + aux2 +" ");
//			for(int j=0; j < propriedadesJ[i].size(); j++) {
//				state = state.concat(propriedadesJ[i].get(j));
//			}
//			state = state.concat("endOf"+aux2+"\n");
			
		}
				
		this.myInt = Integer.valueOf(jogadorDaVez);
		this.aux = myInt.toString();
		
		state = state.concat("jogadorDaVez "+aux+"\n");
		
		this.myInt = Integer.valueOf(dados[0]);
		this.aux = myInt.toString();
		this.myInt = Integer.valueOf(dados[1]);
		this.aux2 = myInt.toString();
		
		state = state.concat("dados "+aux+aux2+" \n");
		
		this.myInt = Integer.valueOf(qtdDuplasNoDado);
		this.aux = myInt.toString();
		
		state = state.concat("qtdDuplasNoDado "+aux+" \n");
		
		this.myInt = Integer.valueOf(total_dados);
		this.aux = myInt.toString();
		
		state = state.concat("total_dados "+aux+" \n");
		
		this.myInt = Integer.valueOf(cartaSorte);
		this.aux = myInt.toString();
		
		state = state.concat("carta "+aux+" \n");
		
		this.myInt = Integer.valueOf(banco);
		this.aux = myInt.toString();
		
		state = state.concat("banco "+aux+" endOfbanco\n");
		
		state = state.concat("ranking "+rank+"\n");

		return state;
		
	}
	
	public int getNJogadores(String state, int jogador) {
		aux = state.substring(0,1);
		aux.trim();
		return Integer.parseInt(aux);
	}
	
	public int getPosJogador(String state, int jogador) {
		this.myInt = Integer.valueOf(jogador);
		this.aux2 = myInt.toString();
		System.out.println(state);
		aux = state.substring(state.indexOf("pos" + aux2 +" ") + 5, state.indexOf("dinheiro"+ aux2));
		System.out.println(aux);
		aux.trim();
		System.out.println(aux);
		return Integer.parseInt(aux);
	}
	
	public int getDinheiroJogador(String state, int jogador) {
		this.myInt = Integer.valueOf(jogador);
		this.aux2 = myInt.toString();
		aux = state.substring(state.indexOf("dinheiro" + aux2 +" ") + 10, state.indexOf("propriedades"+ aux2));
		aux.trim();
		return Integer.parseInt(aux);
	}
	
	public String getPropriedadesJogadores(String state, int jogador) {
		this.myInt = Integer.valueOf(jogador);
		this.aux2 = myInt.toString();
		aux = state.substring(state.indexOf("propriedades"+ aux2 +" ") + 14, state.indexOf("preso" + aux2));
		aux.trim();
		return aux;
	}
	
	public boolean getEstadoJogador(String state, int jogador) {
		this.myInt = Integer.valueOf(jogador);
		this.aux2 = myInt.toString();
		aux = state.substring(state.indexOf("preso" + aux2 +" ") + 7, state.indexOf("preso" + aux2 +" ") + 13);
		aux.trim();
		return Boolean.valueOf(aux);
	}
	
	public int[] getDados(String state) {
		int[] dados = {0,0};
		aux = state.substring(state.indexOf("dados ") + 6, state.indexOf("dados ") + 9);
		aux.trim();
		dados[0] = Integer.parseInt(aux.substring(0,1));
		dados[1] = Integer.parseInt(aux.substring(1));
		return dados;
	}
	
	public int getDuplasNoDado(String state) {
		aux = state.substring(state.indexOf("qtdDuplasNoDado ") + 16, state.indexOf("qtdDuplasNoDado ") + 18);
		aux.trim();
		return Integer.parseInt(aux);
	}
	
	public int getTotalDados(String state) {
		aux = state.substring(state.indexOf("total_dados ") + 12, state.indexOf("total_dados ") + 15);
		aux.trim();
		return Integer.parseInt(aux);
	}
	
	public int getCarta(String state) {
		aux = state.substring(state.indexOf("carta ") + 6, state.indexOf("carta ") + 9);
		aux.trim();
		return Integer.parseInt(aux);
	}
	
	public int getBanco(String state) {
		aux = state.substring(state.indexOf("banco ") + 6, state.indexOf("endOfbanco"));
		aux.trim();
		return Integer.parseInt(aux);
	}
	
	public String getRanking(String state) {
		aux = state.substring(state.indexOf("ranking ") + 8);
		aux.trim();
		return aux;
	}
	
	
}
