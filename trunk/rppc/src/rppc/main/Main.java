package rppc.main;

import rppc.modele.Codage;
import rppc.modele.Decodage;
import rppc.modele.Probleme;

public class Main {

	public static void main(String[] args) {
		Probleme p = Probleme.problemeEnonce();
		Codage c = Codage.permutationEnonce();
		Decodage d = new Decodage(c, p);
		d.decoder().affichageSolution();
	}
}
