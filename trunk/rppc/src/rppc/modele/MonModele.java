package rppc.modele;

import java.util.Observable;
import java.util.Observer;

public class MonModele extends Observable implements Observer {

	private Probleme p;
	private Algorithme algo;

	public MonModele(Probleme p, Algorithme algo) {
		this.p = p;
		this.algo = algo;
		algo.addObserver(this);
	}

	public Probleme getProbleme() {
		return p;
	}

	public void setProbleme(Probleme p) {
		this.p = p;
	}

	public Solution getCurrentSolution() {
		return algo.getSolution();
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
}
