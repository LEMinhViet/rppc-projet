package rppc.modele;

import java.util.ArrayList;
import java.util.List;

public class Probleme {

	private final int largeur;
	private final List<ObjetRectangulaire> objets;

	public Probleme(int largeur, List<ObjetRectangulaire> objets) {
		super();
		this.largeur = largeur;
		this.objets = objets;
	}

	public int getLargeur() {
		return largeur;
	}

	public List<ObjetRectangulaire> getObjets() {
		return objets;
	}

	public int getTaille()
	{
		return objets.size();
	}
	
	public static Probleme problemeEnonce() {
		List<ObjetRectangulaire> objets = new ArrayList<>(6);
		objets.add(new ObjetRectangulaire(1, 2, 0));
		objets.add(new ObjetRectangulaire(3, 1, 1));
		objets.add(new ObjetRectangulaire(2, 1, 2));
		objets.add(new ObjetRectangulaire(2, 1, 3));
		objets.add(new ObjetRectangulaire(1, 1, 4));
		objets.add(new ObjetRectangulaire(2, 2, 5));

		Probleme p = new Probleme(5, objets);

		return p;
	}

}
