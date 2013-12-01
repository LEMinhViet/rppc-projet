/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

/**
 * Un objet a son poids et sa valeur.
 */
public class Objet {
    private int poids;
    private int valeur;
    
    public Objet(int oPoids, int oValeur) {
        this.poids = oPoids;
        this.valeur = oValeur;
    }
    
    public float getValeurDivPoids() {
        return (float)valeur / poids;
    }
    
    public int getPoids() {
        return poids;
    }
    
    public int getValeur() {
        return valeur;
    }
}
