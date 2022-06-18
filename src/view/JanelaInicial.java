package view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class JanelaInicial extends JFrame{
	JPanel janelaPanel = new JPanel();
	JLabel instrucao1 = new JLabel("Coloque o número de jogadores:");
	JTextField inputQtdJogadores = new JTextField(15);
	JButton botaoSubmit = new JButton("Enter");
	private int qtdJogadores;
	
	public JanelaInicial() {
		setTitle("Janela Incial");
		setVisible(true);
		setSize(1200,700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
//		instrucao1.
		janelaPanel.add(instrucao1);
		janelaPanel.add(inputQtdJogadores);
		janelaPanel.add(botaoSubmit);
		botaoSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtdJogadores = Integer.parseInt(inputQtdJogadores.getText());
				try {
					if(qtdJogadores >= 3 && qtdJogadores <= 6) {
						Tabuleiro frame = new Tabuleiro(qtdJogadores);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
					}
					else {
						qtdJogadores = 0;
						throw new Exception("Quantidade de jogadores invalida");
					}
				}catch(Exception e1) {
					System.out.println(e1);
//					JOptionPane.showMessageDialog(null, "Quantidade de jogadores invalida (Min: 3 , Max: 6)");
				}
			}
		});
		add(janelaPanel);
	}
}
