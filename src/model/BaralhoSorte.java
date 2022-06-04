package model;

import java.util.ArrayList;
import java.util.Collections;

class BaralhoSorte {
	private ArrayList<Integer> baralho = new ArrayList<Integer>();
	private BaralhoSorte instance = new BaralhoSorte();
	
	BaralhoSorte getInstance() {
		return this.instance;
	}
	
	private BaralhoSorte(){
		for(int i=0; i <= 30; i++) {
			baralho.add(i);
		}
		
		Collections.shuffle(baralho);
	}
	

}
