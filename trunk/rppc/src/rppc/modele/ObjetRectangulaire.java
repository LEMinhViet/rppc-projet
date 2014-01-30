package rppc.modele;

public class ObjetRectangulaire implements Comparable<ObjetRectangulaire> {

	private final int indice;
	private final int largeur;
	private final int hauteur;

	public ObjetRectangulaire(int largeur, int hauteur,int indice) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.indice=indice;
	}

	public int getIndice() {
		return indice;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	@Override
	public int compareTo(ObjetRectangulaire o) {
		return Integer.compare(largeur, o.largeur);
	}

	public float aire() {
		return largeur*hauteur;
	}

}
