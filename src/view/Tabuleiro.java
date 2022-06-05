package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Tabuleiro extends JFrame implements ActionListener{//,Observer{
	
	private BufferedImage image;
	private BufferedImage dadoImagem1;
	private BufferedImage dadoImagem2;
	private JButton salvarJogo = new JButton("SALVAR");
	private JButton carregarJogo = new JButton("CARREGAR");
	private JButton rodaDados = new JButton("ROLL");
	private JButton baralhoSorte = new JButton("BARALHO");
	private DadosView dado;
	String path = "Imagens-01/dados/";
	
	public Tabuleiro() {
		setLayout(null);
		salvarJogo.setBounds(20,20,100,45);
		carregarJogo.setBounds(120,20,100,45);
		rodaDados.setBounds(320,550,75,45);
		baralhoSorte.setBounds(120,250,100,150);
		
		baralhoSorte.setBackground(Color.orange);
		rodaDados.setBackground(Color.red);
		
		add(salvarJogo);
		add(carregarJogo);
		add(baralhoSorte);
		add(rodaDados);
		
		salvarJogo.addActionListener(this);
		carregarJogo.addActionListener(this);
		rodaDados.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("Imagens-01/tabuleiro.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
//		try {
//			dadoImagem1 = ImageIO.read(getClass().getResourceAsStream(path + "die_face_1.png"));
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			dadoImagem2 = ImageIO.read(getClass().getResourceAsStream(path + "die_face_1.png"));
//		} catch(IOException e) {
//			e.printStackTrace();
//		}

		this.setSize(image.getWidth()+20, image.getHeight()+115);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,10,105,image.getWidth(), image.getHeight(),null);
//		g2D.drawImage(dadoImagem1,590,550,dadoImagem1.getWidth(), dadoImagem1.getHeight(),null);
//		g2D.drawImage(dadoImagem2,590,580,dadoImagem2.getWidth(), dadoImagem2.getHeight(),null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == rodaDados) {
//			dado.rodaDados();
			this.dado = new DadosView();
		}
		else if(e.getSource() == carregarJogo) {
			// to be implemented
		}
		else{
			//to be implemented
		}
		
		
	}

//	@Override
//	public void update(Observable o, Object arg1) {
//		DadosView dado = (DadosView)o;
//		String images = String.valueOf(arg1);
//		String image1 = images.substring(0, 10);
//		String image2 = images.substring(10);
//		try {
//			dadoImagem1 = ImageIO.read(getClass().getResourceAsStream(path + image1));
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			dadoImagem2 = ImageIO.read(getClass().getResourceAsStream(path + image2));
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
		

}
