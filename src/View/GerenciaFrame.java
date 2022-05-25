package View;

// Utilizar singleton pattern para gerenciar os frames

public class GerenciaFrame {
	
    static JanelaInicial frame1;
    static Tabuleiro frame2;

    public static JanelaInicial getFrame1()
    {
        if(frame1 == null)
            frame1 = new JanelaInicial();
        return frame1;
    }

    public static Tabuleiro getFrame2()
    {
        if(frame2 == null)
			try {
				frame2 = new Tabuleiro();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return frame2;
    }


}
