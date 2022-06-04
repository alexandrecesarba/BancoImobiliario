package model;
import java.util.Random;

class Dado {
	static Random ran = new Random();;
	static int valor = 0;
//	private static String image = "";
	private Dado instance = new Dado();

	private Dado(){	}
	
	Dado getInstance() {
		return this.instance;
	}

	public static int rodaDado() {
		valor = ran.nextInt(6)+1;
//		Integer myInt = Integer.valueOf(valor);
//		String s = myInt.toString();
//		image = "die_face_" + s;
		return valor;
	}
	
//	static String getImage() {
//		return image;
//	}
}
