package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.FacadeModel;
import view.Observer;

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
	private int n_duplas = 0;
	private int jogadores;
	private int total_dados;
	private boolean tentativaSairDaPris�o = false;
//	private int[] dados = {0,0}; 
	int jogadorDaVez;
	private String state = "";
	public GameState gameState = GameState.getInstance();

	public GameController(Observer o,int n_jogadores) {
		attach(o);
		this.jogadores = n_jogadores;
		this.rodada = new Rodada(jogadores);
		this.model = new FacadeModel(jogadores);
	}
	
	
	public int jogadorMoveu(int casas) {
		model.jogadorAndou(jogadorDaVez, casas);
//		tipoTerreno(pos);//getFromObserver
		return casas; // change to Observer
	}
	
	public void tipoTerreno(int pos) { //receber da View o tipo de Terreno - ??? ta errado isso
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
			boolean compra = false;//send to View op��o de compra;
			if(compra) {
				model.jogadorComprou(jogadorDaVez,pos);
				if(!model.checaEstadoJogador(jogadorDaVez)) {
					encerraRodada();
				}
			}
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
			boolean compra = false;//send to View op��o de compra;
			if(compra) {
				model.jogadorComprou(jogadorDaVez,pos);
				if(!model.checaEstadoJogador(jogadorDaVez)) {
				}
			}
		}else {// tem dono
			if(dono != jogadorDaVez) { //dono n�o � o jogadorDaVez
				int[] valor = rodaDados(1); //deve-se rodar o dado 1 vez
				int preco = model.getAluguelTerreno(pos)*valor[0];
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
		// tem dono? sim - n�o
			//sim tem dono - � o jogadorDaVez? sim- n�o
			//n�o tem dono - jogadorDaVez quer comprar? sim-n�o
		// jogadorDaVez n�o � o dono - paga aluguel - rodaDado pra ver o valor;
			//tem dinheiro? n�o - vai � falencia - sim - desconta
	}
	
	void entrouNeutro(int pos) {
		int efeito = model.getAluguelTerreno(pos);
		if(efeito == -1) { // pris�o
			model.jogadorPreso(jogadorDaVez);
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
	
	public int getCartaSorte() {
		// 0 -> sa�da livre da pris�o
		// 1 -> receba 50 de cada jogador
		//-1 -> v� para a pris�o
		int carta = model.getCartaSorteReves();
		if(carta == 0) {
			model.mudaEstadoCartaLivreDaPris�o(jogadorDaVez);
			
		}
		if(carta == 1) {
			for(int i=0; i < jogadores; i++) {
				if(!model.jogadorFalido(i)) {
					model.transacaoJogador(i, -50);
					model.transacaoJogador(jogadorDaVez, 50);
					//send to view transacao
					if(!model.checaEstadoJogador(i)) {
						//send to view not�cia de fal�ncia
					}	
				}
			}
			
		}
		return carta;
	}
	
	public int[] rodaDados(int x) {
		int[] dados;
		if(x == 1) {
			dados = model.getDados(1);
			notifyObservers();
		}
		else if(x == 2 && tentativaSairDaPris�o) {
			if(model.temCartaLivreDaPris�o(jogadorDaVez)) {
				model.mudaEstadoCartaLivreDaPris�o(jogadorDaVez);
				model.mudaPris�oJogador(jogadorDaVez);
			}
			dados = model.getDados(2);
			notifyObservers();
			if(dados[0]==dados[1]) {
				model.mudaPris�oJogador(jogadorDaVez);
				//send to view
			}
		}
		else {
			dados = model.getDados(2);
			if(dados[0]==dados[1]) {
				total_dados += dados[0] + dados[1];
				n_duplas++;
				dadoDupla();
			}
			else {
				jogadorMoveu(total_dados);
			}
		}
		notifyObservers();
		return dados;
	}
	
	void dadoDupla() {
		if(n_duplas == 1 || n_duplas == 2) {
			//jogador tem direito a mais um roll
			//send info to view
		}
		else if(n_duplas == 3) {
			//jogador vai para a pris�o
			model.mudaPris�oJogador(jogadorDaVez);
			//send info to view
		}
		
	}
	
	void encerraRodada() {
		if(!model.jogoContinua()) {
			encerraJogo();
		}
		rodada.proximaRodada();
		if(model.jogadorFalido(rodada.jogadorDaVez)) {
			encerraRodada();
		}
		n_duplas = 0;
		this.jogadorDaVez = rodada.jogadorDaVez;
		if(model.jogadorPreso(jogadorDaVez)) {
			tentativaSairDaPris�o = true;
		}else {
			tentativaSairDaPris�o = false;
		}
		
	}
	
	public void encerraJogo() {
//		gameState.setStateRank(state,model.ranking());
		//change state
		
	}
//	
//	public void setState(String state) {
//		this.gameState = state;
//	}
//	
//	public String getState() {
//		return this.gameState;
//	}
//	
	public void notifyObservers() {
		
	}
	
	public void salvaJogo() {
		try {
			File saveFile = new File ("save.txt");
			if(saveFile.createNewFile()) {
				System.out.println("Arquivo de save criado com sucesso");
			}
			}catch (IOException e) {
				System.out.println("Erro na cria��o do arquivo de save");
				e.printStackTrace();
		}
		
		try {
			FileWriter escriba = new FileWriter("save.txt");
			//Escrever infos aqui:
			escriba.write("info");
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
			while(leitor.hasNextLine()) {
				System.out.println(leitor.nextLine()); //Isso so esta printando o arquivo, no lugar botar para pegar a info e jogar no gamestate (eu acho)
			}
		}catch(FileNotFoundException e) {
			System.out.println("Arquivo n�o encontrado.");
			e.printStackTrace();
		}
	}
}





