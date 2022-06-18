package view;
import javax.swing.*;

@SuppressWarnings("serial")
public class Dialogo extends JFrame{
	JPanel janelaPanel = new JPanel();
	JLabel instrucao = new JLabel();
	
	public Dialogo(String feedback) {
		setTitle("JOGO");
		setVisible(true);
		setSize(500,300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		instrucao.setText(feedback);
		janelaPanel.add(instrucao);
		add(janelaPanel);
	}
	
	
}
