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
    
    // Stocker les selections des objets : true - selectionn� 
    private static boolean[] selectionneObjet;
    
    // Le node racine dans le cas on stocke tous les nodes et g�ne�rer l'arbre
    private static Node racine;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        selectionneObjet = new boolean[NUM_OBJ];
        
        // G�n�rer les objets al�atoires et les trier
        genererObjets();
        
        // Algorithme glouton
        Sac sac = new Sac (MAX_POIDS);
        sac.getObjs(liste_init);
        
        // M�thode arboresente - dans le cas on stocke les noeuds :
        // Dans ce cas, on g�n�rer un arbre pour stocker tous les possibilit�s de s�lection des objets    
        // Mais le nombre d'objet est limite � cause de la limite de la m�moire
//        genererArbre();
//        sac = new Sac (MAX_POIDS);
//        sac.getObjs_Arboresente(racine, liste_init);
        
        // M�thode arboresente - dans le cas on ne stocke pas les noeuds :
        // Bas�e sur la m�thode ci-dessus mais on ne g�n�re pas l'arbre
        sac = new Sac(MAX_POIDS);
        sac.getObjs_Arboresente_sansStockerNodes(liste_init);
    }
    
    /**
     * G�n�rer les noeuds al�atoires et les trier
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
                	// �changer 2 noeuds
                    obj = liste_init.get(j);
                    
                    liste_init.set(j, liste_init.get(i));
                    liste_init.set(i, obj);
                }
            }
        }
    }
    
    /**
     * G�n�rer l'arbre qui stocke tous les noeuds 
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
