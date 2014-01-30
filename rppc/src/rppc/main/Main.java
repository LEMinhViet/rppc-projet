package rppc.main;

import rppc.modele.Algorithme;
import rppc.modele.Codage;
import rppc.modele.Decodage;
import rppc.modele.MonModele;
import rppc.modele.Probleme;
import rppc.modele.RechercheLocale;
import rppc.modele.RechercheLocaleIteree;
import rppc.modele.RechercheLocaleItereePlus;
import rppc.modele.Solution;
import rppc.vue.Fenetre;

public class Main {

	public static void main(String[] args) {

		moyenne("HT07.txt");

//		 IG();

	}

	private static void moyenne(String fichier) {
		
		Probleme p = new Probleme(fichier);
		int moyenne = 10;
		
		System.out.println("Taille : "+p.getTaille()+" Largeur : "+p.getLargeur());
		int mperf = 0;
		long mtime = 0;
		float meffi = 0;
		
		for (int n = 0; n < moyenne; n++) {
			
			long time = System.currentTimeMillis();
//			Algorithme a = new RechercheLocale(p, false);
//			Algorithme a = new RechercheLocaleIteree(p);
			Algorithme a = new RechercheLocaleItereePlus(p);
			a.run();
			
			
			int perf = a.getSolution().getHauteur();
			long time1 = System.currentTimeMillis() - time;
			float effi = a.getSolution().ratio();
			
			System.out.println(n+" "+perf+" "+time1/1000+" "+effi);
			mperf+= perf;
			mtime += time1;
			meffi+=effi;
		}
		mperf/=moyenne;
		mtime/=moyenne;
		meffi/=moyenne;
		
		System.out.println("Perf : "+mperf+" Temps : "+mtime/1000+" Efficacite : "+meffi);

	}

	public static void debug() {
		Probleme p = Probleme.problemeEnonce();
		// Probleme p = new Probleme("HT03.txt");
		Algorithme a = new RechercheLocaleItereePlus(p);
		// Algorithme a = new RechercheLocaleIteree(p);
		// Algorithme a = new RechercheLocale(p, false);
		a.run();
		a.getSolution().affichageSolution();

		Codage c = Codage.permutationEnonce();
		Decodage d = new Decodage(c, p);
		d.decoder().affichageSolution();
		Codage c2 = new Codage(d.decoder());
		c2.afficher();
		Solution s = new Solution(p);
		s.solutionRealisable(false);
		s.affichageSolution();
		Codage c3 = new Codage(s);
		c3.afficher();
		Decodage d2 = new Decodage(c3, p);
		d2.decoder().affichageSolution();
	}

	public static void IG() {
		long start = System.currentTimeMillis();
		// Probleme p = Probleme.problemeEnonce();
		Probleme p = new Probleme("HT01.txt");
		// Algorithme a = new RechercheLocaleIteree(p);
		Algorithme a = new RechercheLocaleItereePlus(p);
		MonModele m = new MonModele(p, a);
		new Fenetre(m);
		a.run();
		System.out.println(System.currentTimeMillis() - start);
	}
}
