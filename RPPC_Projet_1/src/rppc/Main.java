/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 */
public class Main {
    public static int nombreDeObjet;
    public static int poidsMax;
    
    private static long debut;
    
    // Contient tous les objets 
    private static ArrayList<Objet> liste_init = new ArrayList<Objet>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	Sac sac;
    	
    	// GENERER les objets aleatoires 
        genererObjets(50, 200);        
        
        // Algorithme glouton - 2.3
        debut = System.currentTimeMillis();
        System.out.println("2.3 - Algorithme glouton ");
        sac = new Sac (poidsMax);
        sac.getObjs_Glouton(liste_init);
        System.out.println("Temps d'execution " + (System.currentTimeMillis() - debut));
        System.out.println("-------------------------------------------------------------");
        
        // Methode arboresente - 2.4 
        debut = System.currentTimeMillis();
        System.out.println("2.4 - Methode Branch and Bound ");
        sac.getObjs_BranchAndBound(liste_init);
        System.out.println("Temps d'execution " + (System.currentTimeMillis() - debut));
        System.out.println("-------------------------------------------------------------");
        
        // UTILISER LE FICHIER D'ENTREE - 2.5
        System.out.println("2.5 - Branch and Greed ");
    	lireDonnee();
		// Methode arboresente - Branch-and-greed
    	debut = System.currentTimeMillis();
        sac = new Sac(poidsMax);
        sac.getObjs_BranchAndGreed(liste_init);
        System.out.println("Temps d'execution " + (System.currentTimeMillis() - debut));
        System.out.println("-------------------------------------------------------------");
    }
    
    /**
     * Trier une liste et retourner cette liste triee (Utiliser l'algorithme Merge Sort)
     * methode dichotomique
     * @param liste 
     * @return : cette liste triee
     */
    public static ArrayList<Objet> trierObjets_Merge(ArrayList<Objet> liste) {
    	if (liste.size() == 1) {
    		return liste;
    	}
    	
    	ArrayList<Objet> resultat = new ArrayList<Objet>();
        ArrayList<Objet> gauche = new ArrayList<Objet>();
        ArrayList<Objet> droit = new ArrayList<Objet>();
        
        for (int i = 0; i < liste.size(); i++) {
        	if (i < liste.size() / 2) 	gauche.add(liste.get(i));
        	else droit.add(liste.get(i));
        }
        
        gauche = trierObjets_Merge(gauche);
        droit = trierObjets_Merge(droit);
        
        for (int i = 0; i < liste.size(); i++) {
        	if (gauche.size() == 0) {
        		resultat.add(droit.get(0));
        		droit.remove(0);
        	} else if (droit.size() == 0) {
        		resultat.add(gauche.get(0));
        		gauche.remove(0);
        	} else {
	        	if (gauche.get(0).getValeurDivPoids() > droit.get(0).getValeurDivPoids()) {
	        		resultat.add(gauche.get(0));
	        		gauche.remove(0);
	        	} else {
	        		resultat.add(droit.get(0));
	        		droit.remove(0);
	        	}
        	}
        }
        
        return resultat;
    }
    
    /**
     * Generer les noeuds aleatoires et les trier
     */
    public static void genererObjets(int nombre, int poids) {
    	// Generer
    	nombreDeObjet = nombre;
    	poidsMax = poids;
    	
        for (int i = 0; i < nombreDeObjet; i++) {
            liste_init.add(new Objet(i, (int)(Math.random() * 1000 % 50 + 1), (int)(Math.random() * 1000 % 50 + 1)));
        }
    }
        
    /**
     * Lire le fichier d'entree
     * @throws IOException 
     */
    public static void lireDonnee() {
    	try {
			InputStream ips = new FileInputStream("data//test.in");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			String ligne = br.readLine();
			// La premiere ligne
			if (ligne != null) {
				nombreDeObjet = Integer.parseInt(ligne);
			}

			for (int i = 0; i < nombreDeObjet; i++) {
				ligne = br.readLine();
				liste_init.add(new Objet(i,
										Integer.parseInt(ligne.split(" ")[1]), 
										Integer.parseInt(ligne.split(" ")[2])));
			}
			
			// La derniere ligne
			poidsMax = Integer.parseInt(br.readLine()); 
			
			
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
}
