package rppc.main;

import rppc.modele.Algorithme;
import rppc.modele.Codage;
import rppc.modele.Decodage;
import rppc.modele.MonModele;
import rppc.modele.Probleme;
import rppc.modele.RechercheLocale;
import rppc.modele.Solution;
import rppc.vue.Fenetre;

public class Main {

	public static void main(String[] args) {
		Probleme p = Probleme.problemeEnonce();
		Algorithme a = new RechercheLocale(p, false);
		a.getSolution();
		
//		Codage c = Codage.permutationEnonce();
//		Decodage d = new Decodage(c, p);
//		d.decoder().affichageSolution();
//		Codage c2 = new Codage(d.decoder());
//		c2.afficher();
//		Solution s = new Solution(p);
//		s.solutionRealisable(false);
//		s.affichageSolution();
//		Codage c3 = new Codage(s);
//		c3.afficher();
//		Decodage d2 = new Decodage(c3, p);
//		d2.decoder().affichageSolution();
		
		IG();
	}
	
	public static void IG(){
		final Probleme p  = Probleme.problemeEnonce();
		MonModele m = new MonModele(p, new Algorithme() {
			
			@Override
			public Solution getSolution() {
				Solution s = new Solution(p);
				s.solutionRealisable(false);
				return s;
			}
		});
		new Fenetre(m);
	}
}
