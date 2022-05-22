package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaInicial extends JFrame{
	JPanel janelaPanel = new JPanel();
	JLabel janelaLabel = new JLabel();
	JTextField inputQtdJogadores = new JTextField(15);
	JButton botaoSubmit = new JButton("Enter");
	private int qtdJogadores;
	
	public JanelaInicial() {
		setTitle("Janela Incial");
		setVisible(true);
		setSize(1200,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		janelaPanel.add(inputQtdJogadores);
		
		janelaPanel.add(botaoSubmit);
		botaoSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtdJogadores = Integer.parseInt(inputQtdJogadores.getText());
				if(qtdJogadores < 3 || qtdJogadores > 6) {
					System.out.printf("%s\n", "Num de jogadores nao permitido (3 a 6 jogadores)");
					qtdJogadores = 0;
				}else {
					System.out.printf("%d\n", qtdJogadores);
				}
			}
		});
		janelaPanel.add(janelaLabel);
		add(janelaPanel);
	}
}
