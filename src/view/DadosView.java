package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Observable;


import model.FacadeModel;

@SuppressWarnings("deprecation")
class DadosView {//extends Observable{
	private int resultados[] = new int[]{0,0};
	private FacadeModel model = FacadeModel.getInstance();
	String image1 = "die_face_1.png";
	String image2 = "die_face_1.png";
	String path = "Imagens-01/dados/";
//	Tabuleiro tabuleiro = new Tabuleiro();
	
	DadosView(){
//		addObserver(tabuleiro);
//		rodaDados();
	}
	
	String rodaDados() {
		resultados = model.getDados(2);
		Integer myInt1 = Integer.valueOf(resultados[0]);
		String s1 = myInt1.toString();
		Integer myInt2 = Integer.valueOf(resultados[1]);
		String s2 = myInt2.toString();
		image1 = "die_face_" + s1 + ".png";
		image2 = "die_face_" + s2 + ".png";
		return image1+image2;
//		setChanged();
//		notifyObservers(image1+image2);
	}

}
