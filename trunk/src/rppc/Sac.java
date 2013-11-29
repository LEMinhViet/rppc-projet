/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

import java.util.ArrayList;

/**
 *
 * @author 3303340
 */
public class Sac {
    public int poids_max;
    public ArrayList<Objet> liste;
    
    public Sac(int poids) {
        liste = new ArrayList<Objet>();
        this.poids_max = poids;
    }
    
    public void ajouterObjet(Objet o) {
        liste.add(o);
    }
    
    public void getObjs(ArrayList<Objet> allObjs) {
        int i = 0;
        int cur_poids = 0;
        while ((poids_max - cur_poids) / allObjs.get(i).getPoids() > 0) {
            liste.add(allObjs.get(i));
            cur_poids += allObjs.get(i).getPoids();
            i++;            
        }
        
        showSac();
    }
    
    public void showSac() {
        int cur_poids = 0;
        int cur_valeur = 0;
        for (int i = 0; i < liste.size(); i++) {
            System.out.println("objet " + (i + 1) + " " + liste.get(i).getPoids() + " " + liste.get(i).getValeur());
            cur_poids += liste.get(i).getPoids();
            cur_valeur += liste.get(i).getValeur();
        }
        
        System.out.println("Total " + liste.size() + " Poids : " + cur_poids + " Valeur : " + cur_valeur);
    }
    
    public void getObjs_Arbo(ArrayList<Objet> allObjs) {
        
    }
    

}
