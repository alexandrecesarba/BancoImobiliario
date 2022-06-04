package view;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Peao{
	private BufferedImage[] peoes;
	private String imgPath;
	
	void setImage() throws IOException {
		for(int i = 0 ; i < 6 ; i++) {
			this.imgPath = "Imagens-01/pin" + i + ".png";
			peoes[i] = ImageIO.read(getClass().getResourceAsStream(imgPath));
		}
	}
}
