package View;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel {
	
	private BufferedImage image;
	
	public Tabuleiro() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tabuleiro.png"))
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, 1200, 700, image.getWidth(), image.getHeight(),null);
	}

}
