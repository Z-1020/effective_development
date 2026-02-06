package tp3.sudoku;

import java.util.LinkedList;

public class Observable {
	
	private LinkedList<Observer> observerList;
	
	public Observable() {
		this.observerList = new LinkedList<Observer>();
	}

	void notifyObservers() {
		for(Observer obs : observerList) {
			obs.update();
		}
	}
	
	void addObserver(Observer obs) {
		observerList.add(obs);
	}
	
	

}
