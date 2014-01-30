package rppc.modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Probleme {

	private int largeur;
	private List<ObjetRectangulaire> objets;

	public Probleme(int largeur, List<ObjetRectangulaire> objets) {
		this.largeur = largeur;
		this.objets = objets;
	}

	public Probleme(String filename) {

		try {
			Scanner scanner = new Scanner(new File(filename));

			int taille = scanner.nextInt();
			// System.out.println(taille);
			this.largeur = scanner.nextInt();
			this.objets = new ArrayList<>(taille);

			for (int i = 0; i < taille; i++) {
				int indice = scanner.nextInt();
				int h = scanner.nextInt();
				int l = scanner.nextInt();
				this.objets.add(new ObjetRectangulaire(l, h, indice - 1));

			}

			scanner.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public int getLargeur() {
		return largeur;
	}

	public List<ObjetRectangulaire> getObjets() {
		return objets;
	}

	public int getTaille() {
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
