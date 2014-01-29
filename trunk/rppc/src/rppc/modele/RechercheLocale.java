package rppc.modele;

public class RechercheLocale implements Algorithme{
	private Solution solution;
	private Probleme probleme;
	private Codage codage;
	private int hauteurLocale;

	public RechercheLocale(Probleme probleme, boolean aleatoire) {
		this.probleme = probleme;
		this.solution = new Solution(probleme);
		this.solution.solutionRealisable(aleatoire);
		this.codage = new Codage(solution);
		this.hauteurLocale = solution.getHauteur();

		for (int i = 0; i < probleme.getTaille(); i++) {
			for (int j = i + 1; j < probleme.getTaille(); j++) {
				Codage c2 = new Codage(codage);
				c2.shiftNeighborhood(i, j);
				Decodage d = new Decodage(c2, probleme);
				Solution s2 = d.decoder();

				if (s2.getHauteur() < hauteurLocale) {
					hauteurLocale = s2.getHauteur();
					solution = s2;
					codage = c2;
					i= -1;
					break;
				}

			}
		}

	}

	@Override
	public Solution getSolution() {
		return solution;
	}

}
