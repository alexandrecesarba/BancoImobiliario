package view;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Tabuleiro extends JFrame{
	
	private BufferedImage image;
	
	public Tabuleiro() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("Imagens-01/tabuleiro.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}

		this.setSize(image.getWidth()+25, image.getHeight()+35);
//		this.setSize(1200,700);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,10,35,image.getWidth(), image.getHeight(),null);
	}

}
