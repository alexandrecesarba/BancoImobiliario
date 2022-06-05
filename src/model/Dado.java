package model;
import java.util.Random;

class Dado {
	static Random ran = new Random();;
	static int valor = 0;
//	private static String image = "";
	private static Dado instance = new Dado();

	private Dado(){	}
	
	static Dado getInstance() {
		return instance;
	}

	public int rodaDado() {
		valor = ran.nextInt(6)+1;
		return valor;
	}
}
