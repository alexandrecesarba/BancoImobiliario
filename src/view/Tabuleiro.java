package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Tabuleiro extends JFrame implements ActionListener{
	
	private BufferedImage image;
	private JButton salvarJogo = new JButton("SALVAR");
	private JButton carregarJogo = new JButton("CARREGAR");
	private JButton rodaDados = new JButton("ROLL");
	private JButton baralhoSorte = new JButton("BARALHO");
	
	
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

		this.setSize(image.getWidth()+20, image.getHeight()+115);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,10,105,image.getWidth(), image.getHeight(),null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == rodaDados) {
			// to be implemented
		}
		else if(e.getSource() == carregarJogo) {
			// to be implemented
		}
		else{
			//to be implemented
		}
		
		
	}

}
