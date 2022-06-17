package view;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.util.Observable;
//import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


public class Tabuleiro extends JFrame implements ActionListener{
	
	private BufferedImage image;
	private String image1;
	private String image2;
	private BufferedImage dadoImagem1;
	private BufferedImage dadoImagem2;
	private BufferedImage cartaImagem;
	private JButton cartaSorte = new JButton("REMOVER CARTA");
	private JButton salvarJogo = new JButton("SALVAR");
	private JButton carregarJogo = new JButton("CARREGAR");
	private JButton encerrarJogo = new JButton("ENCERRAR PARTIDA");
	private JButton rodaDados = new JButton("ROLL");
	private JButton setaDados = new JButton("SET");
	private JButton baralhoSorte = new JButton("BARALHO");
	private BufferedImage[] jogadores;

	// botoes dado
	private JButton dado1 = new JButton("1");
	private JButton dado2 = new JButton("2");
	private JButton dado3 = new JButton("3");
	private JButton dado4 = new JButton("4");
	private JButton dado5 = new JButton("5");
	private JButton dado6 = new JButton("6");
	
	
	private JButton rdado1 = new JButton("1");
	private JButton rdado2 = new JButton("2");
	private JButton rdado3 = new JButton("3");
	private JButton rdado4 = new JButton("4");
	private JButton rdado5 = new JButton("5");
	private JButton rdado6 = new JButton("6");
	
	private int valorDado1 = 1;
	private int valorDado2 = 1;
	private int n_jogadores;
	
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
	
	Tabuleiro(int n) {
//		this.getContentPane()
		this.getContentPane().setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		setImagesDado(valorDado1,valorDado2);
		salvarJogo.setBounds(20,20,100,45);
		carregarJogo.setBounds(120,20,100,45);
		encerrarJogo.setBounds(220,20,200,45);
		this.n_jogadores = n;
		view.initJogadores(n_jogadores);
		
		//posicao dos dados
		dado1.setBounds(210,550,45,45);
		dado2.setBounds(260,550,45,45);
		dado3.setBounds(310,550,45,45);
		dado4.setBounds(210,600,45,45);
		dado5.setBounds(260,600,45,45);
		dado6.setBounds(310,600,45,45);
		
		rdado1.setBounds(410,550,45,45);
		rdado2.setBounds(460,550,45,45);
		rdado3.setBounds(510,550,45,45);
		rdado4.setBounds(410,600,45,45);
		rdado5.setBounds(460,600,45,45);
		rdado6.setBounds(510,600,45,45);
		
//		this.getContentPane().setComponentZOrder(baralhoSorte, -3); doesnt work
		baralhoSorte.setBounds(140,250,100,150);
		
		
		//cores dos dados
		baralhoSorte.setBackground(Color.orange);
		dado1.setBackground(Color.gray);
		dado2.setBackground(Color.gray);
		dado3.setBackground(Color.gray);
		dado4.setBackground(Color.gray);
		dado5.setBackground(Color.gray);
		dado6.setBackground(Color.gray);
		
		rdado1.setBackground(Color.lightGray);
		rdado2.setBackground(Color.lightGray);
		rdado3.setBackground(Color.lightGray);
		rdado4.setBackground(Color.lightGray);
		rdado5.setBackground(Color.lightGray);
		rdado6.setBackground(Color.lightGray);
		
		
		add(salvarJogo);
		add(carregarJogo);
		add(encerrarJogo);
		add(baralhoSorte);
		
		add(dado1);
		add(dado2);
		add(dado3);
		add(dado4);
		add(dado5);
		add(dado6);
		
		add(rdado1);
		add(rdado2);
		add(rdado3);
		add(rdado4);
		add(rdado5);
		add(rdado6);
		
		rodaDados.setBounds(350,460,67,40);
		rodaDados.setBackground(Color.red);
		add(rodaDados);
		rodaDados.addActionListener(this);
		
		setaDados.setBounds(350,500,67,40);
		setaDados.setBackground(Color.gray);
		add(setaDados);
		setaDados.addActionListener(this);
		
		
		salvarJogo.addActionListener(this);
		carregarJogo.addActionListener(this);
		encerrarJogo.addActionListener(this);
		baralhoSorte.addActionListener(this);
		
		dado1.addActionListener(this);
		dado2.addActionListener(this);
		dado3.addActionListener(this);
		dado4.addActionListener(this);
		dado5.addActionListener(this);
		dado6.addActionListener(this);

		
		rdado1.addActionListener(this);
		rdado2.addActionListener(this);
		rdado3.addActionListener(this);
		rdado4.addActionListener(this);
		rdado5.addActionListener(this);
		rdado6.addActionListener(this);
		
		this.setSize(image.getWidth()+20, image.getHeight()+115);
	}
	
	void setImagesDado() {
		String images = view.rodaDados();
		this.image1 = images.substring(0, 14);
		this.image2 = images.substring(14);
		try {
			dadoImagem1 = ImageIO.read(getClass().getResourceAsStream(pathDados + image1));
		} catch(IOException e) {
			System.out.println("dadoImagem1 falhou");
			e.printStackTrace();
		}
		try {
			dadoImagem2 = ImageIO.read(getClass().getResourceAsStream(pathDados + image2));
		} catch(IOException e) {
			System.out.println("dadoImagem2 falhou");
			e.printStackTrace();
		}
	}
	
//	void setImagesJogador(int jogador, int posX, int posY) {
//		
//	}
	
	
	void setImagesDado(int s1, int s2) {
		String image1 = "die_face_" + s1 + ".png";
		String image2 = "die_face_" + s2 + ".png";
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
	
	void setImagesBaralho() {
		String imageSorte = view.getCartaSorte();
		try {
			cartaImagem = ImageIO.read(getClass().getResourceAsStream(pathSorte + imageSorte));
		} catch(IOException e) {
			e.printStackTrace();
		}
//		cartaSorte.setBounds(230,250,100,150);
		cartaSorte.setBounds(120,405,140,40);
		cartaSorte.addActionListener(this);
		add(cartaSorte);
		this.repaint();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponents(g2D);
		g2D.drawImage(image,10,105,image.getWidth(), image.getHeight(),null);
		g2D.drawImage(dadoImagem1,260,490,70,70,null);
		g2D.drawImage(dadoImagem2,460,490,70,70,null);
		if(cartaImagem != null) {
			g2D.drawImage(cartaImagem,280,230,180,230,null);
		}
		for(int i=0; i< this.n_jogadores; i++) {
			g2D.drawImage(view.getImageJogador(i), 
					view.getPosJogador(i)[0] + (view.multiplicadorPosX*i), 
					view.getPosJogador(i)[1] + (view.multiplicadorPosY*i), 
					view.widthJogador, view.heightJogador,null);
		}
		
	}
	
	void mostraDado(int dado1,int dado2) {
		valorDado1 = dado1;
		valorDado2 = dado2;
		this.setImagesDado(dado1, dado2);
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dado1) {
			valorDado1 = 1;
		}
		else if (e.getSource() == dado2) {
			valorDado1 = 2;
		}
		
		else if (e.getSource() == dado3) {
			valorDado1 = 3;
		}
		
		else if (e.getSource() == dado4) {
			valorDado1 = 4;
		}
		
		else if (e.getSource() == dado5) {
			valorDado1 = 5;
		}
		
		else if (e.getSource() == dado6) {
			valorDado1 = 6;
		}
		
		else if (e.getSource() == rdado1) {
			valorDado2 = 1;
		}
		
		else if (e.getSource() == rdado2) {
			valorDado2 = 2;
		}
		
		else if (e.getSource() == rdado3) {
			valorDado2 = 3;
			System.out.print(valorDado2);
		}
		
		else if (e.getSource() == rdado4) {
			valorDado2 = 4;
		}
		
		else if (e.getSource() == rdado5) {
			valorDado2 = 5;
		}
		
		else if (e.getSource() == rdado6) {
			valorDado2 = 6;
		}
		
		else if (e.getSource() == rodaDados) {
			this.setImagesDado();
			this.repaint();
		}
		
		else if (e.getSource() == setaDados) {
			this.setImagesDado(valorDado1, valorDado2);
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
			cartaImagem = null;
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
