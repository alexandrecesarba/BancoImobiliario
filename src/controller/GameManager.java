package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.FacadeModel;
import view.Observer;

public class GameManager extends Observable{
	//rodada
	//jogador
	//roda dado
	//caiu dupla? sim, não
	//não-> anda
	//sim-> roda de novo, caiu dupla? não-> anda, sim? tira de novo, se tiraer uma terceira dupla, vai para a prisão;
	//caiu num terreno-> terreno companhia ou avenida pode comprar, ou puxa carta, ou faz nada.
	// caso o dono do terreno seja ele, pode construir - caso já tenha casa, pode construir um hotel.
	// encerra jogo;
	
	private FacadeModel model;
	private static GameManager instance = new GameManager();
	public GameState gameState = GameState.getInstance();
	private int jogadores;
	private boolean tentativaSairDaPrisao = false;
	private int[] dados = {0,0};
	private int jogadorDaVez = 0;
	private boolean[] jogadoresPresos;
	private boolean[] temHotelPropriedades;
	private int[] qtdCasasPropriedade;
	private int[] posJogadores; 
	private int[] dinheiroJogadores;
	private String nomeTerrenoAtual;
	private int tipoTerrenoAtual = 2;
	private ArrayList<String>[] propriedadesJogadores;
	private int qtdDuplasNoDado = 0;
	private int cartaSaidaLivreDaPrisao = -1; // nenhum jogador a possui
	private int cartaSorte = -1;
	private	int banco;
	private String rank = "";
	private String feedback = "Bem-vindo ao meu Banco Imobiliario!";
	private String state = "";
	private int[] ordemRodada;
	private boolean podeJogar = true;
	private boolean jogadorIndeciso = false;


	private GameManager() {
	}
	
	public static GameManager getInstance() {
		return instance;
	}
	
	public static void setManager(GameManager gm,Observer o,int n_jogadores) {
		gm.attach(o);
		gm.jogadores = n_jogadores;
		gm.initGame();
	}
	
	public void initGame() { // NEEDS TO CHANGE CALLING FOR WHEN LOADING GAME
		this.ordemRodada = new int[jogadores];
		for(int i=0; i < jogadores; i++) {
			this.ordemRodada[i] = i;
		}
		this.jogadorDaVez = ordemRodada[0];
		this.model = new FacadeModel(jogadores);
		this.banco = 200000;
		notifyObservers();
		
	}
	
	public void loadGame() {
		this.model = new FacadeModel(jogadores);
		this.jogadores = gameState.getNJogadores(state);
		this.ordemRodada = new int[jogadores];
		for(int i=0; i < jogadores; i++) {
			this.ordemRodada[i] = i;
			this.posJogadores[i] = gameState.getPosJogador(state, i);
			this.dinheiroJogadores[i] = gameState.getDinheiroJogador(state,i);
			this.propriedadesJogadores[i].add(gameState.getPropriedadesJogadores(state, i));
			this.jogadoresPresos[i] = gameState.getEstadoPresoJogador(state, i);
		}
		for(int i=0; i < model.getTabuleiroLength(); i++) {
			this.qtdCasasPropriedade[i] = gameState.getQntdCasasPropriedades(state, i);
			this.temHotelPropriedades[i] = gameState.getHotelPropriedades(state, i);
		}
		this.jogadorDaVez = gameState.getJogadorDaVez(state);
		this.dados = gameState.getDados(state);
		this.nomeTerrenoAtual = gameState.getNomeTerreno(state);
		this.tipoTerrenoAtual = gameState.getTipoTerreno(state);
		this.qtdDuplasNoDado = gameState.getDuplasNoDado(state);
		this.cartaSaidaLivreDaPrisao = gameState.getJogadorComCartaSaiaLivre(state); // nenhum jogador a possui
		this.cartaSorte = gameState.getCarta(state);
		this.banco = gameState.getBanco(state);
		this.rank = gameState.getRanking(state);
		this.feedback = gameState.getFeedback(state);
		this.banco = gameState.getBanco(state);
		this.model.loadGameInfo(this.dinheiroJogadores,this.posJogadores,this.propriedadesJogadores,this.jogadoresPresos,this.qtdCasasPropriedade, this.temHotelPropriedades,this.cartaSaidaLivreDaPrisao);
		notifyObservers();
		
	}
	
	public void jogadorMoveu(int casas) {
		int pos = model.jogadorAndou(jogadorDaVez, casas);
		this.tipoTerrenoAtual = model.getTipoTerreno(pos);
		if(this.tipoTerrenoAtual != 2) {
			System.out.println("ENTROU NO IF");
			this.nomeTerrenoAtual = model.getNomeTerreno(pos);
			System.out.println(this.nomeTerrenoAtual);
		}
		else {
			this.nomeTerrenoAtual = "neutro";
		}
		notifyObservers();
		if(podeJogar) {
			if(this.tipoTerrenoAtual == 0) {
				entrouAvenida(pos);
			}
			else if(this.tipoTerrenoAtual == 1) {
				entrouCompanhia(pos);
			}
			else {
				entrouNeutro(pos);
			}
		}
	}
	
	public void comprarTerreno() {
		this.jogadorIndeciso = false;
		int pos = this.posJogadores[jogadorDaVez];
		int dono = model.getDonoTerreno(pos); 
		if(dono == -2) { // nao e compravel
			this.feedback = completeFeedback(jogadorDaVez, " calma la magnata! Esse terreno voce nao pode comprar.");
		}
		else if(dono == -1) { // nao tem dono
			this.banco += model.jogadorComprou(jogadorDaVez,pos);
			model.checaEstadoJogador(jogadorDaVez);
			model.setDonoTerreno(jogadorDaVez, pos);
			this.dinheiroJogadores = model.atualizaDinheiroJogadores();
			this.propriedadesJogadores = model.atualizaPropriedadesJogadores();
			this.feedback = completeFeedback(jogadorDaVez, ", você comprou " + model.getNomeTerreno(pos));
			
		}else if(dono == jogadorDaVez) {
			this.feedback = completeFeedback(jogadorDaVez, " calma la! Esse terreno ja e seu! Se quiser construir, aperte o botao CONSTRUIR.");
		}else {
			this.feedback = completeFeedback(jogadorDaVez, " sinto muito mas esse terreno ja pertence a " + model.getNomeJogador(dono));
		}
		notifyObservers();
		encerraRodada();
	}

	public void naoComprarTerreno() {
		this.jogadorIndeciso = false;
		this.feedback = completeFeedback(jogadorDaVez, " nao quis. Ok entao...");
//		notifyObservers();
		encerraRodada();
	}
	
	
	public void construirNoTerreno(int tipoConstrucao) {
		int pos = this.posJogadores[jogadorDaVez];
//		int tipoTerreno = model.getTipoTerreno(pos); para talvez feedback melhores, mas desnecessario
		int dono = model.getDonoTerreno(pos); 
		if(dono != jogadorDaVez) {
			this.feedback = completeFeedback(jogadorDaVez, " esse terreno nao e seu, seu doido." );
			notifyObservers();
		}
		else {
			int qntdCasas = model.terrenoPossuiCasa(pos);
			if(tipoConstrucao == 0) {
				//construa casa
				this.feedback = completeFeedback(jogadorDaVez, " partiu construir uma casa em " + model.getNomeTerreno(pos) +"!");
				model.construirNoTerreno(pos, tipoConstrucao);
				model.transacaoJogador(jogadorDaVez, -model.getTerrenoConstrucaoPreco(pos));
				this.banco += model.getTerrenoConstrucaoPreco(pos);
				this.dinheiroJogadores = model.atualizaDinheiroJogadores();
				encerraRodada();
			}else {
				if(qntdCasas > 0 && !model.terrenoPossuiHotel(pos)) {
					this.feedback = completeFeedback(jogadorDaVez, " partiu construir um hotel em " + model.getNomeTerreno(pos) +"!");
					model.construirNoTerreno(pos, tipoConstrucao);
					model.transacaoJogador(jogadorDaVez, -model.getTerrenoConstrucaoPreco(pos));
					this.banco += model.getTerrenoConstrucaoPreco(pos);
					this.dinheiroJogadores = model.atualizaDinheiroJogadores();
					encerraRodada();
				}
				else {
					this.feedback = completeFeedback(jogadorDaVez, " primeiro deve construir uma casa em" + model.getNomeTerreno(pos) +"!");
					notifyObservers();
				}
			}
		}
		
	}
	
	
	void entrouAvenida(int pos) {
		int dono = model.getDonoTerreno(pos); 
		if(dono == -1) { // não tem dono
			Integer myInt = Integer.valueOf(model.getPrecoTerreno(pos));
			String aux = myInt.toString();
			this.feedback = completeFeedback(jogadorDaVez, ", a rua "+ model.getNomeTerreno(pos) +" ainda não tem dono! Ela custa "+aux+". Aperte COMPRAR ou NAO COMPRAR no tabuleiro.");
			this.jogadorIndeciso = true;
			notifyObservers();
		}else {// tem dono
			if(dono == jogadorDaVez) { //dono e o jogadorDaVez
				Integer myInt = Integer.valueOf(model.terrenoPossuiCasa(pos));
				String aux = myInt.toString();
				myInt = Integer.valueOf(model.getTerrenoConstrucaoPreco(pos));
				String aux2 = myInt.toString();
				if(model.terrenoPossuiHotel(pos)) {
					this.feedback = completeFeedback(jogadorDaVez,", "+model.getNomeTerreno(pos) + " ja e sua! Se deseja construir algo, aperte CONSTRUIR CASA ou CONSTRUIR HOTEL. Ela possui "+aux+" casas e ja possui hotel. O preco para construir e de "+aux2+".");
				}else {
					this.feedback = completeFeedback(jogadorDaVez,", "+model.getNomeTerreno(pos) + " ja e sua! Se deseja construir algo, aperte CONSTRUIR CASA ou CONSTRUIR HOTEL. Ela possui "+aux+" casas e não possui hotel. O preco para construir e de "+aux2+".");
				}
				notifyObservers();
			}
			else {
				this.feedback = completeFeedback(jogadorDaVez,", "+model.getNomeTerreno(pos) + " pertence a " + model.getNomeJogador(dono) + ". Vamos descontar o aluguel, ok?");
				rodaDados(1);
				model.transacaoJogador(jogadorDaVez, -model.getAluguelTerreno(pos)*this.dados[0]);
				model.transacaoJogador(dono, model.getAluguelTerreno(pos)*this.dados[0]); 
				this.dinheiroJogadores = model.atualizaDinheiroJogadores();
				model.checaEstadoJogador(jogadorDaVez);// se o jogador falir com essa operacao, este caso nao esta sendo tratado
				encerraRodada();
			}
		}
	}
	
	void entrouCompanhia(int pos) {
		int dono = model.getDonoTerreno(pos); 
		if(dono == -1) { // não tem dono
			Integer myInt = Integer.valueOf(model.getPrecoTerreno(pos));
			String aux = myInt.toString();
			this.feedback = completeFeedback(jogadorDaVez, ", "+model.getNomeTerreno(pos) + "  ainda não tem dono! Ela custa "+aux+". Aperte COMPRAR ou NAO COMPRAR no tabuleiro.");
			this.jogadorIndeciso = true;
			notifyObservers();
		}else {// tem dono
			if(dono == jogadorDaVez) { //dono e o jogadorDaVez
				this.feedback = completeFeedback(jogadorDaVez, " essa companhia ja e sua!");
				encerraRodada();
			}
			else {
				this.feedback = completeFeedback(jogadorDaVez, " essa companhia pertence a " + model.getNomeJogador(dono) + ". Vamos descontar o aluguel multiplicado pelo resultado do dado da esquerda, ok?");
				rodaDados(1);
				model.transacaoJogador(jogadorDaVez, -model.getAluguelTerreno(pos)*this.dados[0]);
				model.transacaoJogador(dono, model.getAluguelTerreno(pos)*this.dados[0]); 
				this.dinheiroJogadores = model.atualizaDinheiroJogadores();
				model.checaEstadoJogador(jogadorDaVez);// se o jogador falir com essa operacao, este caso nao esta sendo tratado
				encerraRodada();
			}
		}
//		// tem dono? sim - não
//			//sim tem dono - é o jogadorDaVez? sim- não
//			//não tem dono - jogadorDaVez quer comprar? sim-não
//		// jogadorDaVez não é o dono - paga aluguel - rodaDado pra ver o valor;
//			//tem dinheiro? não - vai à falencia - sim - desconta
	}
	
	void entrouNeutro(int pos) {
		int efeito = model.getAluguelTerreno(pos);
		if(efeito == -1) { // prisão
			model.mudaPrisãoJogador(jogadorDaVez);
			this.jogadoresPresos = model.atualizaJogadoresPresos();
			this.feedback = completeFeedback(jogadorDaVez," - ORA ORA E UM SONEGADOR DE IMPOSTOS, TEJE PRESO!");
			model.resetPosicaoJogador(jogadorDaVez, 10); //10 = pos no tabuleiro da prisao
			this.posJogadores = model.atualizaPosJogadores();
			notifyObservers();
			encerraRodada();
		}
		else if(efeito == -2) { // carta sorteOuReves
			this.feedback = completeFeedback(jogadorDaVez,", retire uma carta do baralho! Sorte... ou reves?");
			this.jogadorIndeciso = true;
			notifyObservers();
		}
		else {
			this.banco -= efeito;
			model.transacaoJogador(jogadorDaVez, efeito);
			this.dinheiroJogadores = model.atualizaDinheiroJogadores();
			model.checaEstadoJogador(jogadorDaVez);
			this.feedback = completeFeedback(jogadorDaVez," " + model.getNomeTerreno(pos));
			notifyObservers();
			encerraRodada();
			
		}
		// tem efeito? sim - não
			// é ir pra prisão? jogador preso
			// é início? jogador ganha dinheiro
			// é carta de sorte? chama função carta
	}
	
	public void getCartaSorte() {
		// 0 -> saída livre da prisão
		// 1 -> receba 50 de cada jogador
		//-1 -> vá para a prisão
		this.cartaSorte = model.getCartaSorteReves();
		int efeitoCarta = model.getEfeitoCarta(this.cartaSorte);
		if(efeitoCarta == -1) {
			this.feedback = completeFeedback(jogadorDaVez, " DANCOU! TEJE PRESO!");
			model.mudaPrisãoJogador(jogadorDaVez);
			model.resetPosicaoJogador(jogadorDaVez, 10);
			this.jogadoresPresos = model.atualizaJogadoresPresos();
			this.posJogadores = model.atualizaPosJogadores();
		}
		else if(efeitoCarta == 0) {
			this.feedback = completeFeedback(jogadorDaVez," conseguiu a carta de saida livre da prisao! Guarde-a para emergencias");
			model.mudaEstadoCartaLivreDaPrisão(jogadorDaVez);
		}
		else if(efeitoCarta == 1) {
			this.feedback = completeFeedback(jogadorDaVez, " descolou 50 de cada jogador, podem ir passando.");
			for(int i=0; i < jogadores; i++) {
				if(!model.jogadorFalido(i)) {
					model.transacaoJogador(i, -50);
					model.transacaoJogador(jogadorDaVez, 50);
					this.dinheiroJogadores = model.atualizaDinheiroJogadores();
					model.checaEstadoJogador(i);
				}
			}
		}
		else {
			this.feedback = completeFeedback(jogadorDaVez, ", transacao feita!");
			if(cartaSorte == 10) {
				model.resetPosicaoJogador(jogadorDaVez, 0);
				this.feedback = completeFeedback(jogadorDaVez, " voltando pro inicio!");
				this.posJogadores = model.atualizaPosJogadores();
			}
			this.banco -= efeitoCarta;
			model.transacaoJogador(jogadorDaVez, efeitoCarta);
			this.dinheiroJogadores = model.atualizaDinheiroJogadores();
			model.checaEstadoJogador(jogadorDaVez);
		}
		this.jogadorIndeciso = false;
		notifyObservers();
		encerraRodada();
	}
	

//	public void setaDados(int dado1, int dado2) {
//		//nada
//	}
//	
	
	public void setaDados(int dado1, int dado2) {
		this.dados = new int[]{dado1,dado2};
		if(tentativaSairDaPrisao) {
			if(model.temCartaLivreDaPrisão(jogadorDaVez)) {
				this.feedback = model.getNomeJogador(jogadorDaVez);
				this.feedback = this.feedback.concat(" esta preso, mas usou sua carta de saida!");
				model.mudaEstadoCartaLivreDaPrisão(jogadorDaVez);
				model.mudaPrisãoJogador(jogadorDaVez);
				model.atualizaJogadoresPresos();
				encerraRodada();
			}
			else {
				this.feedback= completeFeedback(jogadorDaVez," esta preso! Tente tirar uma dupla no dado."); 
				notifyObservers();
				if(this.dados[0]==this.dados[1]) {
					this.qtdDuplasNoDado++;
					this.feedback = completeFeedback(jogadorDaVez, " tirou uma dupla! Esta livre!");
					model.mudaPrisãoJogador(jogadorDaVez);
					this.jogadoresPresos = model.atualizaJogadoresPresos();
					encerraRodada();
				}
				else {
					this.feedback = completeFeedback(jogadorDaVez, " continua preso, mais sorte da proxima vez!");
					encerraRodada();
				}
			}
		}
		if(jogadorIndeciso) {
			this.feedback = completeFeedback(jogadorDaVez, " ainda nao terminou de jogar!!");
			notifyObservers();
			return;	
		}
		if(this.dados[0]==this.dados[1]) {
			this.qtdDuplasNoDado++;
			this.feedback = completeFeedback(jogadorDaVez, " tirou uma dupla! Rode o dado novamente.");
			this.podeJogar = false;
			notifyObservers();
			if(this.qtdDuplasNoDado == 2) {
				this.feedback = completeFeedback(jogadorDaVez, " tirou OUTRA DUPLA! Rode o dado DE NOVO.");
				notifyObservers();
			}
			if(this.qtdDuplasNoDado == 3) {
				this.feedback = completeFeedback(jogadorDaVez, " tirou TRES DUPLAS SEGUIDAS! Com certeza ta roubando, TEJE PRESO!");
				model.mudaPrisãoJogador(jogadorDaVez);
				model.resetPosicaoJogador(jogadorDaVez, 10);
				this.jogadoresPresos = model.atualizaJogadoresPresos();
				this.posJogadores = model.atualizaPosJogadores();
				this.podeJogar = true;
				notifyObservers();
			}
		}
		else { this.podeJogar = true;}
			jogadorMoveu(this.dados[0]+this.dados[1]);
	}

	
	
	
	
	
	
	
	public void rodaDados(int x) {
		if(tentativaSairDaPrisao) {
			if(model.temCartaLivreDaPrisão(jogadorDaVez)) {
				// se der tempo, tornar este uso opcional
				this.feedback = model.getNomeJogador(jogadorDaVez);
				this.feedback = this.feedback.concat(" esta preso, mas usou sua carta de saida!");
				model.mudaEstadoCartaLivreDaPrisão(jogadorDaVez);
				model.mudaPrisãoJogador(jogadorDaVez);
				model.atualizaJogadoresPresos();
				encerraRodada();
			}
			else {
				this.feedback= completeFeedback(jogadorDaVez," esta preso! Tente tirar uma dupla no dado."); 
				notifyObservers();
				this.dados = model.getDados();
				if(this.dados[0]==this.dados[1]) {
					this.qtdDuplasNoDado++;
					this.feedback = completeFeedback(jogadorDaVez, " tirou uma dupla! Esta livre!");
					model.mudaPrisãoJogador(jogadorDaVez);
					this.jogadoresPresos = model.atualizaJogadoresPresos();
					encerraRodada();
				}
				else {
					this.feedback = completeFeedback(jogadorDaVez, " continua preso, mais sorte da proxima vez!");
					encerraRodada();
				}
			}
		}
		if(x==2) {
			if(jogadorIndeciso) {
//			Integer myInt = Integer.valueOf(model.getPrecoTerreno(this.posJogadores[jogadorDaVez]));
//			String aux = myInt.toString();					
//			this.feedback = completeFeedback(jogadorDaVez, " ainda nao se decidiu se quer ou nao comprar " + this.model.getNomeTerreno(this.posJogadores[jogadorDaVez])+". O custo é de "+aux+".");
				this.feedback = completeFeedback(jogadorDaVez, " ainda nao terminou de jogar!!");
				notifyObservers();
				return;	
			}
			this.dados = model.getDados();
			if(this.dados[0]==this.dados[1]) {
				this.qtdDuplasNoDado++;
				this.feedback = completeFeedback(jogadorDaVez, " tirou uma dupla! Rode o dado novamente.");
				this.podeJogar = false;
				if(this.qtdDuplasNoDado == 2) {
					this.feedback = completeFeedback(jogadorDaVez, " tirou OUTRA DUPLA! Rode o dado DE NOVO.");
				}
				if(this.qtdDuplasNoDado == 3) {
					this.feedback = completeFeedback(jogadorDaVez, " tirou TRES DUPLAS SEGUIDAS! Com certeza ta roubando, TEJE PRESO!");
					model.mudaPrisãoJogador(jogadorDaVez);
					model.resetPosicaoJogador(jogadorDaVez, 10);
					this.jogadoresPresos = model.atualizaJogadoresPresos();
					this.posJogadores = model.atualizaPosJogadores();
					this.podeJogar = true;
					notifyObservers();
				}
			}
			else { this.podeJogar = true;}
				jogadorMoveu(this.dados[0]+this.dados[1]);
		}
		if(x == 1) {
			this.dados = model.getDados(); // para evitar bug
		}
	}
	
	
	
	
	
	
	void encerraRodada() {
		if(model.jogoContinua()) {
			encerraJogo();
		}
		System.out.println("JOGADOR ANTERIOR");
		System.out.println(jogadorDaVez);
		this.qtdDuplasNoDado = 0;
		if(this.jogadorDaVez == (ordemRodada.length-1)) { // jogadorDaVez = 0 a 5  -  jogadores.lenght = 6 (max)
			System.out.println("ordemRodada.length");
			System.out.println(ordemRodada.length-1);
			this.jogadorDaVez = ordemRodada[0];
		}
		else {
			System.out.println("ordemRodada[jogadorDaVez++]");
			System.out.println(ordemRodada[jogadorDaVez++]);
			this.jogadorDaVez = ordemRodada[jogadorDaVez++];
			System.out.println(jogadorDaVez);
		}
		System.out.println("JOGADOR ATUAL");
		System.out.println(jogadorDaVez);
		if(model.jogadorFalido(jogadorDaVez)) {
			encerraRodada();
		}
		if(this.jogadoresPresos[jogadorDaVez]) {
			tentativaSairDaPrisao = true;
		}else {
			tentativaSairDaPrisao = false;
		}
//		if(tentativaSairDaPrisao) {
//			if(model.temCartaLivreDaPrisão(jogadorDaVez)) {
//				// se der tempo, tornar este uso opcional
//				this.feedback = model.getNomeJogador(jogadorDaVez);
//				this.feedback = this.feedback.concat(" esta preso, mas usou sua carta de saida!");
//				model.mudaEstadoCartaLivreDaPrisão(jogadorDaVez);
//				model.mudaPrisãoJogador(jogadorDaVez);
//				model.atualizaJogadoresPresos();
//				notifyObservers();
//				encerraRodada();
//			}else {
//				this.feedback= completeFeedback(jogadorDaVez," esta preso! Tente tirar uma dupla no dado."); 
//			}
//		}
		notifyObservers();
		salvaJogo();
	}
	
	public void encerraJogo() {
		this.rank = model.ranking();
		this.feedback = "Jogo encerrado! O ranking final: ";
		this.feedback = this.feedback.concat(gameState.getRanking(state));
		notifyObservers();	
	}
	
	public String completeFeedback(int jogador, String feedback) {
		this.feedback = model.getNomeJogador(jogadorDaVez);
		this.feedback = this.feedback.concat(feedback);
		this.feedback = this.feedback.concat("\nSUA COR É ");
		this.feedback = this.feedback.concat(model.getCorJogador(jogadorDaVez));
		this.feedback = this.feedback.concat("\nSEU DINHEIRO É ");
		Integer myInt = Integer.valueOf(this.dinheiroJogadores[jogadorDaVez]);
		String aux = myInt.toString();
		this.feedback = this.feedback.concat(aux);
		this.feedback = this.feedback.concat("\nSUAS PROPRIEDADES:");
		this.feedback = this.feedback.concat(this.propriedadesJogadores[jogadorDaVez].toString());
		this.feedback = this.feedback.concat("\nBANCO: ");
		myInt = Integer.valueOf(this.banco);
		aux = myInt.toString();
		this.feedback = this.feedback.concat(aux);
		return this.feedback;
	}
	
	public String getState() {
		setState();
		return this.state;
	}
	
	public void setState() {
		this.posJogadores = model.atualizaPosJogadores();
		this.dinheiroJogadores = model.atualizaDinheiroJogadores();
		this.jogadoresPresos = model.atualizaJogadoresPresos();
		this.propriedadesJogadores = model.atualizaPropriedadesJogadores();
		this.cartaSaidaLivreDaPrisao = model.quemTemCartaLivreDaPrisão();
		this.qtdCasasPropriedade = model.atualizaCasasPropriedades();
		this.temHotelPropriedades = model.atualizaHotelPropriedades();
		this.state = this.gameState.setState(this.feedback, this.jogadores, this.posJogadores, this.dinheiroJogadores, 
				this.propriedadesJogadores, this.jogadoresPresos,this.jogadorDaVez, this.nomeTerrenoAtual, this.tipoTerrenoAtual, this.dados, 
				this.qtdDuplasNoDado, this.cartaSorte, 	this.qtdCasasPropriedade,this.temHotelPropriedades,
				this.cartaSaidaLivreDaPrisao,this.banco, this.rank);
	}
	
	public void salvaJogo() {
        try {
            File saveFile = new File ("saveBI.txt");
            if(saveFile.createNewFile()) {
                System.out.println("Arquivo de save criado com sucesso");
            }
            }catch (IOException e) {
                System.out.println("Erro na criacao do arquivo de save");
                e.printStackTrace();
        }
        
        try {
            FileWriter escriba = new FileWriter("save.txt");
            //Escrever infos aqui:
            escriba.write(this.state);
            //--------------------
            escriba.close();
            System.out.println("Jogo salvo com sucesso!");
        }catch(IOException e) {
            System.out.println("Erro no salvamento do jogo");
            e.printStackTrace();
        }
    }
    
    public void carregaJogo() {
        try {
            File saveFile = new File("save.txt");
            Scanner leitor = new Scanner(saveFile);
            this.state = "";
            while(leitor.hasNextLine()) {
                this.state = state.concat(leitor.nextLine());
            }
            leitor.close();
        }catch(FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado.");
            e.printStackTrace();
        }
        loadGame();
    }

	
}





