package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import model.FacadeModel;

//@SuppressWarnings("deprecation")
class DadosView extends JFrame{
	private int resultados[] = new int[]{0,0};
	private FacadeModel model = FacadeModel.getInstance();
	String image1 = null;
	String image2 = null;
	String path = "Imagens-01/dados/";
	private BufferedImage dadoImagem1;
	private BufferedImage dadoImagem2;
	
	DadosView(){
		rodaDados();
		setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.print(path+image1);
		try {
			dadoImagem1 = ImageIO.read(getClass().getResourceAsStream(path + image1));
		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
			dadoImagem2 = ImageIO.read(getClass().getResourceAsStream(path + image2));
		} catch(IOException e) {
			e.printStackTrace();
		}

		this.setSize((dadoImagem1.getWidth()*2 + 100), (dadoImagem1.getHeight() + 50));
	}
	
	void rodaDados() {
		resultados = model.getDados(2);
		Integer myInt1 = Integer.valueOf(resultados[0]);
		String s1 = myInt1.toString();
		Integer myInt2 = Integer.valueOf(resultados[1]);
		String s2 = myInt2.toString();
		image1 = "die_face_" + s1 + ".png";
		image2 = "die_face_" + s2 + ".png";
//		setChanged();
//		notifyObservers(image1+image2);
	}
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(dadoImagem1,20,35,dadoImagem1.getWidth(), dadoImagem1.getHeight(),null);
		g2D.drawImage(dadoImagem2,360,35,dadoImagem2.getWidth(), dadoImagem2.getHeight(),null);
	}

}
