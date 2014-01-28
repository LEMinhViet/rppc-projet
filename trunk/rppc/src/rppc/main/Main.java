package rppc.main;

import rppc.modele.Codage;
import rppc.modele.Decodage;
import rppc.modele.Probleme;
import rppc.modele.Solution;

public class Main {

	public static void main(String[] args) {
		Probleme p = Probleme.problemeEnonce();
		Codage c = Codage.permutationEnonce();
		Decodage d = new Decodage(c, p);
		d.decoder().affichageSolution();
		Codage c2 = new Codage(d.decoder());
		c2.afficher();
		Solution s = new Solution(p);
		s.solutionRealisable();
		s.affichageSolution();
		Codage c3 = new Codage(s);
		c3.afficher();
		Decodage d2 = new Decodage(c3, p);
		d2.decoder().affichageSolution();
	}
}
