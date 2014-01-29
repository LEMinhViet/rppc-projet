package rppc.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution implements Comparable<Solution> {

	private int x[];
	private int y[];
	private int hauteur;
	private Probleme probleme;

	public Solution(Probleme probleme) {
		this.x = new int[probleme.getTaille()];
		this.y = new int[probleme.getTaille()];
		this.probleme = probleme;
	}

	public void setX(int i, int valeur) {
		this.x[i] = valeur;
	}

	public void setY(int i, int valeur) {
		this.y[i] = valeur;
	}

	public int getX(int indice) {
		return x[indice];
	}

	public int getY(int indice) {
		return y[indice];
	}

	public Probleme getProbleme() {
		return probleme;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void affichageSolution() {
		System.out.println("H: " + hauteur + " " + valideSolution());
		for (int i = 0; i < x.length; i++) {
			System.out.println(probleme.getObjets().get(i).getIndice() + " (" + +x[i] + "," + y[i] + ")" + "\t"
					+ probleme.getObjets().get(i).getLargeur() + " " + probleme.getObjets().get(i).getHauteur());
		}
	}

	public void calculeHauteur() {
		hauteur = y[0] + probleme.getObjets().get(0).getHauteur();
		for (int i = 1; i < y.length; i++) {
			if (y[i] + probleme.getObjets().get(i).getHauteur() > hauteur)
				hauteur = y[i] + probleme.getObjets().get(i).getHauteur();
		}
	}

	public boolean valideSolution() {
		for (int i = 0; i < x.length; i++) {
			if (x[i] + probleme.getObjets().get(i).getLargeur() > probleme.getLargeur())
				return false;
		}
		return true;
	}

	public int maxX(List<Integer> J) {
		int max = x[J.get(0)] + probleme.getObjets().get(J.get(0)).getLargeur();
		for (int j : J) {
			if (x[j] + probleme.getObjets().get(j).getLargeur() > max)
				max = x[j] + probleme.getObjets().get(j).getLargeur();
		}
		return max;
	}

	public int maxY(List<Integer> K) {
		int max = y[K.get(0)] + probleme.getObjets().get(K.get(0)).getHauteur();
		for (int k : K) {
			if (y[k] + probleme.getObjets().get(k).getHauteur() > max)
				max = y[k] + probleme.getObjets().get(k).getHauteur();
		}
		return max;
	}

	public void solutionRealisable(boolean aleatoire) {
		List<ObjetRectangulaire> r = new ArrayList<>(probleme.getTaille());
		r.addAll(probleme.getObjets());

		if (!aleatoire)
			Collections.sort(r, Collections.reverseOrder());
		else
			Collections.shuffle(r);

		setX(r.get(0).getIndice(), 0);
		setY(r.get(0).getIndice(), 0);

		for (int i = 1; i < x.length; i++) {
			setX(r.get(i).getIndice(), 0);
			setY(r.get(i).getIndice(), r.get(i - 1).getHauteur() + getY(r.get(i - 1).getIndice()));
		}
		calculeHauteur();
	}

	@Override
	public int compareTo(Solution o) {
		return Integer.compare(getHauteur(), o.getHauteur());
	}

}
