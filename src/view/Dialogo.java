package view;
import javax.swing.*;

@SuppressWarnings("serial")
public class Dialogo extends JFrame{
	JPanel janelaPanel = new JPanel();
	JLabel instrucao = new JLabel();
	
	public Dialogo(String feedback) {
		JOptionPane.showMessageDialog(null, feedback);
	}
	
	
}
