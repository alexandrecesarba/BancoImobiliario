package Model;
import java.util.Random;

class Dado {
	Random ran = new Random();
	int valor = 0;
	private String image = "";
	int rodaDado() {
		valor = ran.nextInt(6)+1;
		Integer myInt = Integer.valueOf(valor);
		String s = myInt.toString();
		image = "die_face_" + s;
		return valor;
	}
	
	String getImage() {
		return image;
	}
	
	

}
