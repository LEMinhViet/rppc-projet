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
		for (int i = 0; i < 1000; i++) {
			System.out.println("step " + i);

			s2 = new Solution(p);
			s2.perturbation(s);	
			RechercheLocale r = new RechercheLocale(p, s2);
			r.run();
			s2 = r.getSolution();
			if (s.getHauteur() > s2.getHauteur() && s2.valideSolution()) {
				s = s2;	
//				i = -1;
				setChanged();
				notifyObservers();
			}

		}
	}

}
