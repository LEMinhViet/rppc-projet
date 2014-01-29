package rppc.modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Probleme {

	private final int largeur;
	private final List<ObjetRectangulaire> objets;

	public Probleme(int largeur, List<ObjetRectangulaire> objets) {
		this.largeur = largeur;
		this.objets = objets;
	}
	
	public Probleme(String filename){
		
			try {
				Scanner scanner = new Scanner(new File(filename));

				largeur = scanner.nextInt();

				// Lire les cout d'execution
				for (int i = 0; i < nombreDeAgent; i++) {
					for (int j = 0; j < nombreDeTache; j++) {
						if (i == 0) {
							taches.add(new Tache(nombreDeAgent));
						}

						taches.get(j).addCout(i, scanner.nextInt());
					}
				}

				// Lire les ressources requis pour l'execution
				for (int i = 0; i < nombreDeAgent; i++) {
					for (int j = 0; j < nombreDeTache; j++) {
						taches.get(j).addRessource(i, scanner.nextInt());
					}
				}

				// Lire les capacites des agents
				for (int i = 0; i < nombreDeAgent; i++) {
					capacite[i] = scanner.nextInt();
				}

				scanner.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
