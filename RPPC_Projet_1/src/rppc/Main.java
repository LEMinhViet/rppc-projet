/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 *
 */
public class Main {
    public static final int NUM_OBJ = 30;
    public static final int MAX_POIDS = 200;
    
    // Contient tous les objets 
    private static ArrayList<Objet> liste_init = new ArrayList<Objet>(NUM_OBJ);
    private static Objet obj;
    
    // Stocker les selections des objets : true - selectionné 
    private static boolean[] selectionneObjet;
    
    // Le node racine dans le cas on stocke tous les nodes et géneérer l'arbre
    private static Node racine;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        selectionneObjet = new boolean[NUM_OBJ];
        
        // Générer les objets aléatoires et les trier
        genererObjets();
        
        // Algorithme glouton
        Sac sac = new Sac (MAX_POIDS);
        sac.getObjs(liste_init);
        
        // Méthode arboresente - dans le cas on stocke les noeuds :
        // Dans ce cas, on générer un arbre pour stocker tous les possibilités de sélection des objets    
        // Mais le nombre d'objet est limite à cause de la limite de la mémoire
//        genererArbre();
//        sac = new Sac (MAX_POIDS);
//        sac.getObjs_Arboresente(racine, liste_init);
        
        // Méthode arboresente - dans le cas on ne stocke pas les noeuds :
        // Basée sur la méthode ci-dessus mais on ne génère pas l'arbre
        sac = new Sac(MAX_POIDS);
        sac.getObjs_Arboresente_sansStockerNodes(liste_init);
    }
    
    /**
     * Générer les noeuds aléatoires et les trier
     */
    public static void genererObjets() {
        // Generer
        for (int i = 0; i < NUM_OBJ; i++) {
            liste_init.add(new Objet((int)(Math.random() * 1000 % 50 + 1), (int)(Math.random() * 1000 % 50 + 1)));
        }
                
        // Classifier
        for (int i = 0; i < liste_init.size(); i++) {
            for (int j = i; j < liste_init.size(); j++) {
                if (liste_init.get(j).getValeurDivPoids() > liste_init.get(i).getValeurDivPoids()) {
                	// Échanger 2 noeuds
                    obj = liste_init.get(j);
                    
                    liste_init.set(j, liste_init.get(i));
                    liste_init.set(i, obj);
                }
            }
        }
    }
    
    /**
     * Générer l'arbre qui stocke tous les noeuds 
     */
    public static void genererArbre() {
    	racine = new Node(selectionneObjet);   
    }
    
    /**
     * Obtenir le poids d'un objet
     * @param objPos : la position de l'objet
     * @return : le poids
     */
    public static int getPoids(int objPos) {
    	return liste_init.get(objPos).getPoids();
    }
    
    /**
     * Obtenir la valeur d'un objet
     * @param objPos : la position de l'objet
     * @return : la valeur
     */
    public static int getValeur(int objPos) {
    	return liste_init.get(objPos).getValeur();
    }
}
