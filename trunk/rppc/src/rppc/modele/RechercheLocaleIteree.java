package rppc.modele;

public class RechercheLocaleIteree extends Algorithme {

	private Probleme p;
	private Solution s;

	public RechercheLocaleIteree(Probleme p) {
		this.p = p;
	}

	@Override
	public Solution getSolution() {
		return s;
	}

	@Override
	public void run() {

		Solution s2;
		s = new Solution(p);
		s.solutionRealisable(true);
		for (int i = 0; i < 1000; i++) {
			RechercheLocale r = new RechercheLocale(p, true);
			r.run();
			s2 = r.getSolution();
			if (s.getHauteur() > s2.getHauteur()) {
				s2.affichageSolution();
				s = s2;	
				i = -1;
			}

		}
	}

}
