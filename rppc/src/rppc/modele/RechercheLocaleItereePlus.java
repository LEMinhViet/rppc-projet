package rppc.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RechercheLocaleItereePlus extends Algorithme {

	private Probleme p;
	private List<Solution> s;
	private Random generateur;

	public RechercheLocaleItereePlus(Probleme p) {
		this.p = p;
		s = new ArrayList<>(30);
		for (int i = 0; i < 30; i++) {
			Solution ss = new Solution(p);
			ss.solutionRealisable(true);
			s.add(ss);
		}
		generateur = new Random();
	}

	@Override
	public Solution getSolution() {
		return Collections.min(s);
	}

	@Override
	public void run() {

		for (int i = 0; i < 10000; i++) {
			int j = generateur.nextInt(s.size());
			RechercheLocale r = new RechercheLocale(p, s.get(j));
			r.run();
			Solution s2 = r.getSolution();
			if (s.get(j).getHauteur() > s2.getHauteur()) {
				s.set(j, s2);
				i = -1;
				setChanged();
				notifyObservers();
			}

		}
	}
}
