package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.GameController;

class ViewController extends Observer{

	private static ViewController instance = new ViewController();
	private GameController controller;
	int carta;
	int resultadoDados[] = new int[]{0,0};
//	private int n_jogadores;
	
	private ViewController(){
	}
	
	public static ViewController getInstance() {
		return instance;
	}
	
	void initJogadores(int n) {
		this.controller= new GameController(n);
	}
	
	String rodaDados() {
		resultadoDados = controller.rodaDados(2);
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
			e.printStackTrace();
		}
		return image;
	}
	
	String getCartaSorte() {
		carta = controller.getCartaSorte();
		Integer myInt1 = Integer.valueOf(carta);
		String s1 = myInt1.toString();
		String image = "chance" + s1 + ".png";
		return image;
	}
	
	public void update() {
		carta = controller.getCartaSorte();
		resultadoDados = controller.rodaDados(2);
		
	}

}
