package rppc.modele;

import java.util.List;

public class Solution {

	private int x[];
	private int y[];
	private int hauteur;
	private Probleme probleme;

	public Solution(int taille, Probleme probleme) {
		this.x = new int[taille];
		this.y = new int[taille];
		this.probleme = probleme;
	}

	public Solution(int[] x, int[] y, Probleme probleme) {
		this.x = x;
		this.y = y;
		this.probleme = probleme;
	}

	public void setX(int i, int valeur) {
		this.x[i] = valeur;
	}

	public void setY(int i, int valeur) {
		this.y[i] = valeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public void affichageSolution() {
		System.out.println(hauteur);
		for (int i = 0; i < x.length; i++) {
			System.out.println("(" + x[i] + "," + y[i] + ")");
		}
	}

	public void calculeHauteur() {
		hauteur = y[0] + probleme.getObjets().get(y[0]).getHauteur();
		for (int i = 0; i < y.length; i++) {
			if (y[i] + probleme.getObjets().get(y[i]).getHauteur() > hauteur)
				hauteur = y[i] + probleme.getObjets().get(y[i]).getHauteur();
		}
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

}
