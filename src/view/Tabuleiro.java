package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.util.Observable;
//import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Tabuleiro extends JFrame implements ActionListener{
	
	private BufferedImage image;
	private String image1;
	private String image2;
	private BufferedImage dadoImagem1;
	private BufferedImage dadoImagem2;
	private JButton cartaSorte;
	private JButton salvarJogo = new JButton("SALVAR");
	private JButton carregarJogo = new JButton("CARREGAR");
	private JButton encerrarJogo = new JButton("ENCERRAR PARTIDA");
	private JButton rodaDados = new JButton("ROLL");
	private JButton baralhoSorte = new JButton("BARALHO");
	private ViewController view = ViewController.getInstance();
	String pathDados = "Imagens-01/dados/";
	String pathSorte = "Imagens-01/sorteReves/";
	{
		try {
			image = ImageIO.read(getClass().getResourceAsStream("Imagens-01/tabuleiro.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Tabuleiro() {
		setLayout(null);
		setImagesDado(0);
		salvarJogo.setBounds(20,20,100,45);
		carregarJogo.setBounds(120,20,100,45);
		encerrarJogo.setBounds(220,20,200,45);
		rodaDados.setBounds(320,550,75,45);
		baralhoSorte.setBounds(120,250,100,150);
		
		
		baralhoSorte.setBackground(Color.orange);
		rodaDados.setBackground(Color.red);
		
		add(salvarJogo);
		add(carregarJogo);
		add(encerrarJogo);
		add(baralhoSorte);
		add(rodaDados);
		
		salvarJogo.addActionListener(this);
		carregarJogo.addActionListener(this);
		encerrarJogo.addActionListener(this);
		baralhoSorte.addActionListener(this);
		rodaDados.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(image.getWidth()+20, image.getHeight()+115);
	}
	
	void setImagesDado() {
		String images = view.rodaDados();
		this.image1 = images.substring(0, 14);
		this.image2 = images.substring(14);
		try {
			dadoImagem1 = ImageIO.read(getClass().getResourceAsStream(pathDados + image1));
		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
			dadoImagem2 = ImageIO.read(getClass().getResourceAsStream(pathDados + image2));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void setImagesDado(int beginning) {
		try {
			dadoImagem1 = ImageIO.read(getClass().getResourceAsStream(pathDados + "die_face_1.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
			dadoImagem2 = ImageIO.read(getClass().getResourceAsStream(pathDados + "die_face_1.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void setImagesBaralho() {
		String imageSorte = view.getCartaSorte();
		ImageIcon cartaImagem = new ImageIcon(pathSorte + imageSorte);
		cartaSorte = new JButton(cartaImagem);
		cartaSorte.setBounds(230,250,100,150);
		cartaSorte.addActionListener(this);
		add(cartaSorte);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,10,105,image.getWidth(), image.getHeight(),null);
		g2D.drawImage(dadoImagem1,280,490,70,70,null);
		g2D.drawImage(dadoImagem2,380,490,70,70,null);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == rodaDados) {
			this.setImagesDado();
			this.repaint();
		}
		else if(e.getSource() == baralhoSorte) {
			setImagesBaralho();
		}
		else if(e.getSource() == encerrarJogo) {
			// to be implemented
		}
		else if(e.getSource() == cartaSorte) {
			this.remove(cartaSorte);
			this.repaint();
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
////		DadosView dado = (DadosView)o;
//		String images = String.valueOf(arg1);
//		String image1 = images.substring(0, 10);
//		String image2 = images.substring(10);
//		setImages(image1,image2);
//		this.repaint();    
//		}
		

}
