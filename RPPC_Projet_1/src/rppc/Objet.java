/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

/**
 * Un objet a son poids et sa valeur.
 */
public class Objet {
	private int pos;
    private int poids;
    private int valeur;
    
    public Objet(int pos, int oPoids, int oValeur) {
    	this.pos = pos;
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

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
}
