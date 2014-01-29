package rppc.modele;

public class RechercheLocaleIteree extends Algorithme {

	private Probleme p;
	private Solution s;

	public RechercheLocaleIteree(Probleme p) {
		this.p = p;
		s = new Solution(p);
		s.solutionRealisable(true);
	}


	@Override
	public Solution getSolution() {
		return s;
	}

	@Override
	public void run() {

		Solution s2;
		for (int i = 0; i < 10000; i++) {
			RechercheLocale r = new RechercheLocale(p, true);
			r.run();
			s2 = r.getSolution();
			if (s.getHauteur() > s2.getHauteur()) {
				s = s2;	
				i = -1;
				setChanged();
				notifyObservers();
			}

		}
	}

}
