package view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.GameManager;

@SuppressWarnings("serial")
public class JanelaInicial extends JFrame implements ActionListener{
	JPanel janelaPanel = new JPanel();
	JLabel instrucao1 = new JLabel("Coloque o número de jogadores:");
	JTextField inputQtdJogadores = new JTextField(15);
	JButton botaoSubmit = new JButton("NOVO JOGO");
	private int qtdJogadores;
	private JButton carregarJogo = new JButton("CARREGAR");
	private ViewController view = ViewController.getInstance();
	
	public JanelaInicial() {
		setTitle("Janela Incial");
		setVisible(true);
		setSize(500,700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		carregarJogo.setBounds(150,60,120,50);
		add(carregarJogo);
		carregarJogo.addActionListener(this);
//		instrucao1.
		janelaPanel.add(instrucao1);
		janelaPanel.add(inputQtdJogadores);
		janelaPanel.add(botaoSubmit);
		botaoSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtdJogadores = Integer.parseInt(inputQtdJogadores.getText());
				try {
					if(qtdJogadores >= 3 && qtdJogadores <= 6) {
						Tabuleiro frame = new Tabuleiro(qtdJogadores, true);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
					}
					else {
						qtdJogadores = 0;
						throw new Exception("Quantidade de jogadores invalida");
					}
				}catch(Exception e1) {
					System.out.println(e1);
					JOptionPane.showMessageDialog(null, "Quantidade de jogadores invalida (Min: 3 , Max: 6)");
				}
			}
		});
		add(janelaPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == carregarJogo) {
			view.carregaJogo();
			Tabuleiro frame = new Tabuleiro(qtdJogadores,false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
	}
}
