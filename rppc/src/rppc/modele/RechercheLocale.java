package rppc.modele;

import java.util.ArrayList;
import java.util.Collections;

public class RechercheLocale extends Algorithme {
	private Solution solution;
	private Codage codage;
	private int hauteurLocale;
	private Probleme probleme;

	ArrayList<Integer> lesI;
	ArrayList<Integer> lesJ;

	public RechercheLocale(Probleme probleme, boolean aleatoire) {
		this.solution = new Solution(probleme);
		this.solution.solutionRealisable(aleatoire);
		this.codage = new Codage(solution);
		this.hauteurLocale = solution.getHauteur();
		this.probleme = probleme;
		// solution.affichageSolution();

		init_shuffle();
	}

	public RechercheLocale(Probleme p, Solution s) {
		this.probleme = p;
		this.hauteurLocale = s.getHauteur();
		codage = new Codage(s);
		solution = new Decodage(codage, p).decoder();
		// On cherche une solution réalisable proche du codage c
		init_shuffle();

		for (int i : lesI) {
			for (int j : lesJ)
				if (i != j) {
					Codage c2 = new Codage(s);
					c2.shiftNeighborhood(i, j);
					Decodage d = new Decodage(c2, probleme);
					Solution s2 = d.decoder();

					if (s2.getHauteur() <= hauteurLocale && s2.valideSolution()) {
						hauteurLocale = s2.getHauteur();
						solution = s2;
						codage = c2;
						return;
					}

				}
		}
	}

	private void init_shuffle() {
		lesI = new ArrayList<>(probleme.getTaille());
		lesJ = new ArrayList<>(probleme.getTaille());

		for (int i = 0; i < probleme.getTaille(); i++) {
			lesI.add(i);
			lesJ.add(i);
		}

		Collections.shuffle(lesI);
		Collections.shuffle(lesJ);
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

	@Override
	public void run() {

		boolean aMeilleur = true;

		while (aMeilleur) {

			aMeilleur = false;
			for (int i : lesI)
				for (int j : lesJ)
					if (i != j) {
						Codage c2 = new Codage(codage);
						c2.shiftNeighborhood(i, j);
						Decodage d = new Decodage(c2, probleme);
						Solution s2 = d.decoder();

						if (s2.getHauteur() < hauteurLocale && s2.valideSolution()) {
							// s2.affichageSolution();
							hauteurLocale = s2.getHauteur();
							solution = s2;
							codage = c2;
							aMeilleur = true;
							setChanged();
							notifyObservers();
							break;
						}

					}

			Collections.shuffle(lesI);
			Collections.shuffle(lesJ);
		}

	}

}
