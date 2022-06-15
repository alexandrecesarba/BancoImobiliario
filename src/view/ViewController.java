package view;

import controller.GameController;

class ViewController extends Observer{

	private static ViewController instance = new ViewController();
	private GameController controller = GameController.getInstance();
	int carta;
	int resultados[] = new int[]{0,0};
	
	private ViewController(){
	}
	
	public static ViewController getInstance() {
		return instance;
	}
	
	String rodaDados() {
		Integer myInt1 = Integer.valueOf(resultados[0]);
		String s1 = myInt1.toString();
		Integer myInt2 = Integer.valueOf(resultados[1]);
		String s2 = myInt2.toString();
		String image1 = "die_face_" + s1 + ".png";
		String image2 = "die_face_" + s2 + ".png";
		return image1+image2;
	}
	
	String getCartaSorte() {
		Integer myInt1 = Integer.valueOf(carta);
		String s1 = myInt1.toString();
		String image = "chance" + s1 + ".png";
		return image;
	}
	
	public void update() {
		carta = controller.getCartaSorte();
		resultados = controller.rodaDados(2);
	}

}
