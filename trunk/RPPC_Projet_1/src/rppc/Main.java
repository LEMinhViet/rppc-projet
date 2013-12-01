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

/**
 *
 * @author 3303340
 */
public class Main {
    private static final int NUM_OBJ = 50;
    
    private static ArrayList<Objet> liste_init = new ArrayList<Objet>(NUM_OBJ);
    private static Objet obj;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sac sac = new Sac (200);
        
        genererObjets();
        
        sac.getObjs(liste_init);
        
    }
    
    public static void genererObjets() {
        // Generer
        for (int i = 0; i < NUM_OBJ; i++) {
            liste_init.add(new Objet((int)(Math.random() * 1000 % 50 + 1), (int)(Math.random() * 1000 % 50 + 1)));
        }
                
        // Classifier
        for (int i = 0; i < liste_init.size(); i++) {
            for (int j = i; j < liste_init.size(); j++) {
                if (liste_init.get(j).getValeurDivPoids() > liste_init.get(i).getValeurDivPoids()) {
                    obj = liste_init.get(j);
                    
                    liste_init.set(j, liste_init.get(i));
                    liste_init.set(i, obj);
                }
            }
        }
        
//        for (int i = 0; i < liste_init.size(); i++) { 
//            System.out.println(liste_init.get(i).getValeurDivPoids());
//        }    
    }
    
    public static void genererArbre() {
        Node racine = new Node();
        
        for (int i = 0; i < liste_init.size(); i++) {
            
        }
    }
}
