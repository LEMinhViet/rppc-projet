package rppc.modele;

import java.util.Observable;

public class MonModele extends Observable {

	private Probleme p;
	private Algorithme algo;

	public MonModele(Probleme p, Algorithme algo) {
		this.p = p;
		this.algo = algo;
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
}
