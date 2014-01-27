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
		List<ObjetRectangulaire> objets = new ArrayList<>(5);
		objets.add(new ObjetRectangulaire(1, 2));
		objets.add(new ObjetRectangulaire(3, 1));
		objets.add(new ObjetRectangulaire(2, 1));
		objets.add(new ObjetRectangulaire(2, 1));
		objets.add(new ObjetRectangulaire(1, 1));
		objets.add(new ObjetRectangulaire(2, 2));

		Probleme p = new Probleme(5, objets);

		return p;
	}

}
