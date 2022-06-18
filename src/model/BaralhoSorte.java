package model;

import java.util.ArrayList;
import java.util.Collections;

class BaralhoSorte {
	private static ArrayList<Integer> baralho = new ArrayList<Integer>();
	private static BaralhoSorte instance = new BaralhoSorte();
	
	static BaralhoSorte getInstance() {
		return instance;
	}
	
	private BaralhoSorte(){
		for(int i=0; i < 30; i++) {
			baralho.add(i);
		}
		Collections.shuffle(baralho);

		System.out.println(baralho);
	}
	
	int getCarta() {
		int aux = baralho.get(0);
		baralho.add(aux);
		baralho.remove(baralho.get(0));
		return aux;
	}
	
	void removeCartaLivrePrisao() {
		baralho.remove(baralho.get(9));
	}
	
	void adicionaCartaLivrePrisao() {
		baralho.add((baralho.size()-1),9);
	}
	

}
