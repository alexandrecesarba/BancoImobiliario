package controller;

import java.util.ArrayList;

import view.Observer;

public abstract class Observable {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void attach(Observer o) {
		this.observers.add(o);
	}
	
	public void detach(Observer o) {
		this.observers.remove(o);
	}
	
	public void notifyObservers() {
		for(Observer observer: observers) {
			observer.update();
		}
	}

}
