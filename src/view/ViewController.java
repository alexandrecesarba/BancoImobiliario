package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.GameManager;

public class ViewController extends Observer{

	private static ViewController instance = new ViewController();
	private GameManager manager = GameManager.getInstance();
	private String state;
	int carta;
	int resultadoDados[] = new int[]{0,0};
	int heightJogador;
	int widthJogador;
	int[] posXTabuleiro;
	int[] posYTabuleiro;
	int multiplicadorPosX=7;
	int multiplicadorPosY=13;
	
	private ViewController(){
		this.posXTabuleiro = new int[11];
		this.posYTabuleiro = new int[11];
			for(int i=0; i< 11; i++) {
				posXTabuleiro[i] = 610-55*i;
			}
			for(int i=0; i< 11; i++) {
				posYTabuleiro[i] = 690-55*i;
			}
	}
	
	public static ViewController getInstance() {
		return instance;
	}
	
	void initJogadores(int n) {
		GameManager.setManager(manager,this,n);
	}
	
//	void encerraJogo() {
//		manager.encerraJogo();
//	}
	
	void comprarTerreno() {
		manager.comprarTerreno();
	}
	
	void naoComprarTerreno() {
		manager.naoComprarTerreno();
	}
	
	void construirNoTerreno(int tipoConstrucao) {
//		manager.construirNoTerreno(tipoConstrucao);
	}
	
	String rodaDados() {
		manager.rodaDados(2);
		System.out.println("rodou");
		resultadoDados = manager.gameState.getDados(state);
		Integer myInt1 = Integer.valueOf(resultadoDados[0]);
		String s1 = myInt1.toString();
		Integer myInt2 = Integer.valueOf(resultadoDados[1]);
		String s2 = myInt2.toString();
		String image1 = "die_face_" + s1 + ".png";
		String image2 = "die_face_" + s2 + ".png";
		return image1+image2;
	}
	
	BufferedImage getImageJogador(int i) {
		String pathJogador = "Imagens-01/pinos/";
		BufferedImage image = null;
		String imageName = pathJogador + "pin" + i + ".png";
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName));
		} catch(IOException e) {
			System.out.println("getImageJogador falhou");
			e.printStackTrace();
		}
		this.heightJogador = image.getHeight();
		this.widthJogador = image.getWidth();
		return image;
	}

	String getImageTerreno() {
		String imagem ="";
		String pathCompanhia = "Imagens-01/companhias/";
		String pathAvenida = "Imagens-01/territorios/";
		int tipo = manager.gameState.getTipoTerreno(state);
		System.out.println("getImageTerreno");
		System.out.println(tipo);
		imagem = manager.gameState.getNomeTerreno(state);
		if(tipo == 2) {return null;}
		else if(tipo == 1) {
			return pathCompanhia + imagem;
			}
		else{	
			return pathAvenida + imagem;
		}
	}
	
	String getFeedback() {
		return manager.gameState.getFeedback(state);
	}
	
	int[] getPosJogador(int jogador) {
		int pos = manager.gameState.getPosJogador(state, jogador);
		int[] posJogador = {-1,-1};
		if(pos >= 0 && pos <= 10 ) { // y fixo x móvel mX = 7 e mY=13
			this.multiplicadorPosX = 7;
			this.multiplicadorPosY = 13;
			posJogador[0] = posXTabuleiro[pos];				
			posJogador[1] = posYTabuleiro[0];
		}
		else if(pos >= 10 && pos <= 20 ) {
			System.out.println("pos >= 10 && pos <= 20");
			System.out.println(pos-10);
			this.multiplicadorPosX = 13;
			this.multiplicadorPosY = 7;
			posJogador[0] = posXTabuleiro[10];			
			posJogador[1] = posYTabuleiro[pos-10];
		}
		else if(pos >= 20 && pos <= 30 ) {
			System.out.println("pos >= 20 && pos <= 30");
			System.out.println(20-pos);
			this.multiplicadorPosX = 13;
			this.multiplicadorPosY = 7;
			posJogador[0] = posXTabuleiro[10-(pos-20)];		
			posJogador[1] = posYTabuleiro[10];
		}
		else {
			this.multiplicadorPosX = 13;
			this.multiplicadorPosY = 7;
			System.out.println("pos >= 30 && pos <= 40");
			System.out.println(pos-30);
			posJogador[0] = posXTabuleiro[0];				
			posJogador[1] = posYTabuleiro[10-(pos-30)];
		}
		return posJogador;
	}
	
	String getCartaSorte() {
		manager.getCartaSorte();
		carta = manager.gameState.getCarta(state);
		Integer myInt1 = Integer.valueOf(carta+1);
		String s1 = myInt1.toString();
		String image = "chance" + s1 + ".png";
		return image;
	}
	
	public void update() {
		this.state = manager.getState();
	}

}
