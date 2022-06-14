package view;

import controller.GameController;

class ViewController {//extends Observable{

	private static ViewController instance = new ViewController();
	private GameController controller = GameController.getInstance();
	
	private ViewController(){
	}
	
	public static ViewController getInstance() {
		return instance;
	}
	
	String rodaDados() {
		int resultados[] = new int[]{0,0};
		resultados = controller.rodaDados(2);
		Integer myInt1 = Integer.valueOf(resultados[0]);
		String s1 = myInt1.toString();
		Integer myInt2 = Integer.valueOf(resultados[1]);
		String s2 = myInt2.toString();
		String image1 = "die_face_" + s1 + ".png";
		String image2 = "die_face_" + s2 + ".png";
		return image1+image2;
//		setChanged();
//		notifyObservers(image1+image2);
	}
	
	String getCartaSorte() {
		int carta = controller.getCartaSorte();
		Integer myInt1 = Integer.valueOf(carta);
		String s1 = myInt1.toString();
		String image = "chance" + s1 + ".png";
		return image;
//		setChanged();
//		notifyObservers(image1+image2);
	}

}
