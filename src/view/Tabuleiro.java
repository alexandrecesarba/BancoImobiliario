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
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import javax.swing.ButtonGroup;


public class Tabuleiro extends JFrame implements ActionListener{
	
	private BufferedImage image;
	private String image1;
	private String image2;
	private BufferedImage dadoImagem1;
	private BufferedImage dadoImagem2;
	private BufferedImage cartaImagem;
	private BufferedImage terrenoImagem = null;
	private JButton cartaSorte = new JButton("REMOVER CARTA");
	private JButton salvarJogo = new JButton("SALVAR");
	private JButton comprarTerreno = new JButton("COMPRAR");
	private JButton naoComprarTerreno = new JButton("NAO COMPRAR");
	private JButton construirCasa = new JButton("CONSTRUIR CASA");
	private JButton construirHotel = new JButton("CONSTRUIR HOTEL");
	private JButton encerrarJogo = new JButton("ENCERRAR PARTIDA");
	private JButton rodaDados = new JButton("ROLL");
	private JButton setaDados = new JButton("SET");
	private JButton baralhoSorte = new JButton("BARALHO");
	private BufferedImage[] jogadores;
	private Dialogo dialogo;
	private String feedback = "";
	
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
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.n_jogadores = n;
		view.initJogadores(n_jogadores);
		
		//posicao dos dados
		dado1.setBounds(360,590,45,45);
		dado2.setBounds(400,590,45,45);
		dado3.setBounds(440,590,45,45);
		dado4.setBounds(360,630,45,45);
		dado5.setBounds(400,630,45,45);
		dado6.setBounds(440,630,45,45);
		
		rdado1.setBounds(480,590,45,45);
		rdado2.setBounds(520,590,45,45);
		rdado3.setBounds(560,590,45,45);
		rdado4.setBounds(480,630,45,45);
		rdado5.setBounds(520,630,45,45);
		rdado6.setBounds(560,630,45,45);
	
		
		baralhoSorte.setBounds(140,220,100,150);
		salvarJogo.setBounds(10,10,110,30);
		encerrarJogo.setBounds(330,40,260,30);
		comprarTerreno.setBounds(330,10,130,30);
		naoComprarTerreno.setBounds(460,10,130,30);
		construirCasa.setBounds(120,10,210,30);
		construirHotel.setBounds(120,40,210,30);
		
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
		add(comprarTerreno);
		add(naoComprarTerreno);
		add(construirCasa);
		add(construirHotel);
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
		
		rodaDados.setBounds(10,40,110,30);
		rodaDados.setBackground(Color.red);
		add(rodaDados);
		rodaDados.addActionListener(this);
		
		setaDados.setBounds(450,535,70,30);
		setaDados.setBackground(Color.gray);
		add(setaDados);
		setaDados.addActionListener(this);
		
		
		salvarJogo.addActionListener(this);
		comprarTerreno.addActionListener(this);
		naoComprarTerreno.addActionListener(this);
		construirCasa.addActionListener(this);
		construirHotel.addActionListener(this);
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
	
	void setImagesDado(int s1, int s2) {
		view.setaDados(s1, s2);
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
		cartaSorte.setBounds(120,370,140,40);
		cartaSorte.addActionListener(this);
		add(cartaSorte);
		this.repaint();
	}
	
	void setFeedback() {
		this.feedback = view.getFeedback();
		this.dialogo = new Dialogo(feedback);
	}
	
	void setImagesTerreno() {
		String imageTerreno = view.getImageTerreno();
		if(imageTerreno == null) {
			return;
		}
		else {
			imageTerreno += ".png";
			try {
				System.out.println(imageTerreno);
				terrenoImagem = ImageIO.read(getClass().getResourceAsStream(imageTerreno));
			} catch(IOException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponents(g2D);
		g2D.drawImage(image,10,105,image.getWidth(), image.getHeight(),null);
		g2D.drawImage(dadoImagem1,400,470,70,70,null);
		g2D.drawImage(dadoImagem2,510,470,70,70,null);
		if(cartaImagem != null) {
			g2D.drawImage(cartaImagem,380,220,180,230,null);
		}
		for(int i=0; i< this.n_jogadores; i++) {
			g2D.drawImage(view.getImageJogador(i), 
					view.getPosJogador(i)[0],// + (view.multiplicadorPosX*i), 
					view.getPosJogador(i)[1],// + (view.multiplicadorPosY*i), 
					view.widthJogador, view.heightJogador,null);
		}
		if(terrenoImagem != null) {
			g2D.drawImage(terrenoImagem,130,460,180,230,null);
		}
		setFeedback();
		
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
			this.setImagesTerreno();
			this.repaint();
		}
		
		else if (e.getSource() == setaDados) {
			this.setImagesDado(valorDado1, valorDado2);
			this.repaint();
		}
		
		else if(e.getSource() == baralhoSorte) {
			this.setImagesBaralho();
		}
		else if(e.getSource() == encerrarJogo) {
			// to be implemented
		}
		else if(e.getSource() == cartaSorte) {
			this.remove(cartaSorte);
			cartaImagem = null;
			this.repaint();
		}
		else if(e.getSource() == comprarTerreno) {
			view.comprarTerreno();
			setFeedback();
		}
		else if(e.getSource() == naoComprarTerreno) {
			view.naoComprarTerreno();
			setFeedback();
		}
		else if(e.getSource() == construirCasa) {
			view.construirNoTerreno(0);
			setFeedback();
		}
		else if(e.getSource() == construirHotel) {
			view.construirNoTerreno(1);
			setFeedback();
		}else if(e.getSource() == salvarJogo) {
			view.salvaJogo();
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
