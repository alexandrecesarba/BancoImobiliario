package controller;

import java.util.ArrayList;

import model.FacadeModel;
import view.Observer;
import view.ViewController;

public class GameController extends Observable{
	//rodada
	//jogador
	//roda dado
	//caiu dupla? sim, n�o
	//n�o-> anda
	//sim-> roda de novo, caiu dupla? n�o-> anda, sim? tira de novo, se tiraer uma terceira dupla, vai para a pris�o;
	//caiu num terreno-> terreno companhia ou avenida pode comprar, ou puxa carta, ou faz nada.
	// caso o dono do terreno seja ele, pode construir - caso j� tenha casa, pode construir um hotel.
	// encerra jogo;
	
	private FacadeModel model;
	private Rodada rodada;
	public GameState gameState = GameState.getInstance();
	private int jogadores;
	private int total_dados;
	private boolean tentativaSairDaPrisao = false;
	private int[] dados = {0,0};
	private int jogadorDaVez = 0;
	private boolean[] jogadoresPresos;
	private int[] posJogadores; 
	private int[] dinheiroJogadores;
	private ArrayList<String>[] propriedadesJogadores;
	private int qtdDuplasNoDado = 0;
	private int cartaSorte = -1;
	private	int banco;
	private String rank = "";
	private String state;

	public GameController(Observer o,int n_jogadores) {
		attach(o);
		this.jogadores = n_jogadores;
		initGame();
	}
	
	public void initGame() {
		this.rodada = new Rodada(jogadores);
		this.model = new FacadeModel(jogadores);
		this.banco = 200000;
		notifyObservers();
		
	}
	
	public void jogadorMoveu(int casas) {
		int pos = model.jogadorAndou(jogadorDaVez, casas);
		tipoTerreno(pos);
		notifyObservers();
	}
	
	public void tipoTerreno(int pos) {
		int tipoTerreno = model.getTipoTerreno(pos);
		if(tipoTerreno == 0) {
			entrouAvenida(pos);
		}
		else if(tipoTerreno == 1) {
			entrouCompanhia(pos);
		}else {
			entrouNeutro(pos);
		}
	}
	
	void entrouAvenida(int pos) {
		int dono = model.getDonoTerreno(pos); 
		if(dono == -1) { // n�o tem dono
//			boolean compra = false;
//			compra = view.jogadorComprou();//send to View op��o de compra;
//			if(compra) {
//				model.jogadorComprou(jogadorDaVez,pos);
//				if(!model.checaEstadoJogador(jogadorDaVez)) {
//					encerraRodada();
//				}
//			}
		}else {// tem dono
			if(dono == jogadorDaVez) { //dono � o jogadorDaVez
				int qntdCasa = model.terrenoPossuiCasa(pos);
				if(qntdCasa > 0) {
					if(qntdCasa != 4) {
						if(!model.terrenoPossuiHotel(pos)) {
							//send to View op��o de construir casa ou hotel
						}
						//send to View op��o de construir casa
					}
				}else {
					//send to View op��o de construir casa
				}
				
				//send to View op��o de vender terreno
				
			}else {//dono n�o � o jogadorDaVez
				model.transacaoJogador(jogadorDaVez, -model.getAluguelTerreno(pos));
				if(model.checaEstadoJogador(jogadorDaVez)) {
					model.transacaoJogador(dono, model.getAluguelTerreno(pos));
				}
				//send to View 
			}
		}
	}
	
	void entrouCompanhia(int pos) {
		int dono = model.getDonoTerreno(pos); 
		if(dono == -1) { // n�o tem dono
//			boolean compra = false;//send to View op��o de compra;
//			if(compra) {
//				model.jogadorComprou(jogadorDaVez,pos);
//				if(!model.checaEstadoJogador(jogadorDaVez)) {
//				}
//			}
		}else {// tem dono
			if(dono != jogadorDaVez) { //dono n�o � o jogadorDaVez

				rodaDados(1); //deve-se rodar o dado 1 vez
				
				int preco = model.getAluguelTerreno(pos)*this.dados[0];
				
				//send to View resultado do dado + valor total
				
				model.transacaoJogador(jogadorDaVez, -preco);
				
				if(model.checaEstadoJogador(jogadorDaVez)) {
					model.transacaoJogador(dono, model.getAluguelTerreno(pos));
				}else {
					//send to view noticia de falencia
					encerraRodada();
				}
			}
		}
		notifyObservers();
		// tem dono? sim - n�o
			//sim tem dono - � o jogadorDaVez? sim- n�o
			//n�o tem dono - jogadorDaVez quer comprar? sim-n�o
		// jogadorDaVez n�o � o dono - paga aluguel - rodaDado pra ver o valor;
			//tem dinheiro? n�o - vai � falencia - sim - desconta
	}
	
	void entrouNeutro(int pos) {
		int efeito = model.getAluguelTerreno(pos);
		if(efeito == -1) { // pris�o
			this.jogadoresPresos[jogadorDaVez] = model.jogadorPreso(jogadorDaVez);
			notifyObservers();
			// send to view noticia da prisao
			encerraRodada();
		}
		else if(efeito == -2) { // carta sorteOuReves
			getCartaSorte();
		}
		else {
			model.transacaoJogador(jogadorDaVez, efeito);
			model.checaEstadoJogador(jogadorDaVez);
			//send to view efeito
			
		}
		// tem efeito? sim - n�o
			// � ir pra pris�o? jogador preso
			// � in�cio? jogador ganha dinheiro
			// � carta de sorte? chama fun��o carta
	}
	
	public void getCartaSorte() {
		// 0 -> sa�da livre da pris�o
		// 1 -> receba 50 de cada jogador
		//-1 -> v� para a pris�o
		int carta = model.getCartaSorteReves();
		int efeitoCarta = model.getEfeitoCarta(model.getCartaSorteReves());
		if(efeitoCarta == 0) {
			model.mudaEstadoCartaLivreDaPris�o(jogadorDaVez);
			
		}
		if(efeitoCarta == 1) {
			for(int i=0; i < jogadores; i++) {
				if(!model.jogadorFalido(i)) {
					model.transacaoJogador(i, -50);
					model.transacaoJogador(jogadorDaVez, 50);
					//this.dinheiroJogadores mudou
					if(!model.checaEstadoJogador(i)) {
						//send to view not�cia de fal�ncia
					}	
				}
			}
			
		}
		this.cartaSorte = carta;
		notifyObservers();
	}
	
	public void rodaDados(int x) {
		if(x == 1) {
			this.dados = model.getDados(1);
			setState();
			notifyObservers();
		}
		else if(x == 2 && tentativaSairDaPrisao) {
			if(model.temCartaLivreDaPris�o(jogadorDaVez)) {
				model.mudaEstadoCartaLivreDaPris�o(jogadorDaVez);
				model.mudaPris�oJogador(jogadorDaVez);
			}
			this.dados = model.getDados(2);
			notifyObservers();
			if(this.dados[0]==this.dados[1]) {
				this.jogadoresPresos[jogadorDaVez] = model.mudaPris�oJogador(jogadorDaVez);
				//send to view
			}
		}
		else {
			this.dados = model.getDados(2);
			total_dados += this.dados[0] + this.dados[1];
//			if(this.dados[0]==this.dados[1]) {
//				total_dados += this.dados[0] + this.dados[1];
//				this.qtdDuplasNoDado++;
//				notifyObservers();
//				dadoDupla();
//			}
//			else {
			jogadorMoveu(total_dados);
//			}
		}
	}
	
//	void dadoDupla() {
//		if(this.qtdDuplasNoDado == 1 || this.qtdDuplasNoDado == 2) {
//			//jogador tem direito a mais um roll
//			//send info to view
//		}
//		else if(qtdDuplasNoDado == 3) {
//			//jogador vai para a pris�o
//			model.mudaPris�oJogador(jogadorDaVez);
//			this.jogadoresPresos[jogadorDaVez] = true;
//			notifyObservers();
//		}
//		
//	}
	
	void encerraRodada() {
		if(!model.jogoContinua()) {
			encerraJogo();
		}
		rodada.proximaRodada();
		if(model.jogadorFalido(rodada.jogadorDaVez)) {
			encerraRodada();
		}
		this.qtdDuplasNoDado = 0;
		this.jogadorDaVez = rodada.jogadorDaVez;
		if(model.jogadorPreso(jogadorDaVez)) {
			tentativaSairDaPrisao = true;
		}else {
			tentativaSairDaPrisao = false;
		}
		notifyObservers();
		
	}
	
	public void encerraJogo() {
//		gameState.setStateRank(state,model.ranking());
		//change state
		
	}

	public void notifyObservers() {
		this.setState();
		for(Observer observer: observers) {
			observer.update();
		}
	}
	
	public String getState() {
		System.out.println(state);
		return this.state;
	}
	
	public void setState() {
		this.posJogadores = model.atualizaPosJogadores();
		this.dinheiroJogadores = model.atualizaDinheiroJogadores();
		this.jogadoresPresos = model.atualizaJogadoresPresos();
		this.propriedadesJogadores = model.atualizaPropriedadesJogadores();
		this.state = this.gameState.setState(this.jogadores, this.posJogadores, this.dinheiroJogadores, 
				this.propriedadesJogadores, this.jogadoresPresos,this.jogadorDaVez, this.dados, 
				this.qtdDuplasNoDado,this.total_dados, this.cartaSorte, this.banco, this.rank);
	}
	
//	
//	public String getState() {
//		return this.gameState;
//	}
//	

	
}





