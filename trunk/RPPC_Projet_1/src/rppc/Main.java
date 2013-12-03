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
    
    // Contient tous les objets 
    private static ArrayList<Objet> liste_init = new ArrayList<Objet>();
    private static Objet obj;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	Sac sac;
    	
    	// GENERER les objets al�atoires 
        genererObjets(50, 200);        
        
        // Algorithme glouton - 2.3
        System.out.println("2.3 - Algorithme glouton ");
        sac = new Sac (poidsMax);
        sac.getObjs_Glouton(liste_init);
        System.out.println("-------------------------------------------------------------");
        
        // Methode arboresente - 2.4 
        System.out.println("2.4 - Methode arboresente ");
        sac = new Sac(poidsMax);
        sac.getObjs_Arboresente(liste_init);
        System.out.println("-------------------------------------------------------------");
        
        // OU 
        
    	// UTILISER LE FICHIER D'ENTR�E - 2.5
//        System.out.println("2.5 - Branch-and-Greed ");
//    	try {
//			lireDonnee();
//      	liste_init = trierObjets(liste_init);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        // M�thode arboresente - Branch-and-greed
//        sac = new Sac(poidsMax);
//        sac.getObjs_Branch_and_greed(liste_init);
//        System.out.println("-------------------------------------------------------------");
    }
    
    /**
     * G�n�rer les noeuds al�atoires et les trier
     */
    public static void genererObjets(int nombre, int poids) {
    	// Generer
    	nombreDeObjet = nombre;
    	poidsMax = poids;
    	
        for (int i = 0; i < nombreDeObjet; i++) {
            liste_init.add(new Objet((int)(Math.random() * 1000 % 50 + 1), (int)(Math.random() * 1000 % 50 + 1)));
        }
        
        // Afficher les objets g�n�r�s
//        for (int i = 0; i < nombreDeObjet; i++) {
//            System.out.println("Obj " + i + " poids " + liste_init.get(i).getPoids() + " valeur " + liste_init.get(i).getValeur());
//        }
    }
    
    /**
     * Trier une liste et retourner cette liste tri�e
     * @param liste 
     * @return : cette liste tri�e
     */
    public static ArrayList<Objet> trierObjets(ArrayList<Objet> liste) {
        // Classifier
        for (int i = 0; i < liste.size(); i++) {
            for (int j = i; j < liste.size(); j++) {
                if (liste.get(j).getValeurDivPoids() > liste.get(i).getValeurDivPoids()) {
                	// �changer 2 noeuds
                    obj = liste.get(j);
                    
                    liste.set(j, liste.get(i));
                    liste.set(i, obj);
                }
            }
        }
        
        return liste;
    }
    
    /**
     * Lire le fichier d'entr�e
     * @throws IOException 
     */
    public static void lireDonnee() throws IOException {
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
			liste_init.add(new Objet(Integer.parseInt(ligne.split(" ")[1]), 
									 Integer.parseInt(ligne.split(" ")[2])));
		}
		
		// La derniere ligne
		poidsMax = Integer.parseInt(br.readLine()); 
		
		
		br.close();
    }
}
