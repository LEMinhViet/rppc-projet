/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

import org.ietf.jgss.Oid;

/**
 * Un noeuds dans l'arbre
 */
public class Node {
	// Un noeud a 2 fils : à gauche et à droite
    private Node gauche;
    private Node droite;
    
    // Contient la sélection des objets : true - selectionné 
    private boolean[] selectionneObjet;
    
    // Les valeurs totals courants 
    private int poidsCourant;
    private int valeurCourant;
    // Le longeur courant du noeud dans l'arbre
    private int longeurCourant;
    
    public Node(boolean[] selectionneObjet, int poidsCourant, int longeurCourant, boolean selectionerObjetCourant) {
        this.selectionneObjet = copyBooleanArray(selectionneObjet);
        this.selectionneObjet[longeurCourant] = selectionerObjetCourant;
        this.longeurCourant = longeurCourant;
        this.poidsCourant = poidsCourant;
        
        // Ajouter un objet si il est sélectionné
        if (selectionerObjetCourant) {
        	this.poidsCourant += Main.getPoids(longeurCourant);
        	this.valeurCourant += Main.getValeur(longeurCourant);
        }
        
        // Générer ses fils si possible
        if (this.poidsCourant < Main.MAX_POIDS) {
	        if (this.longeurCourant != selectionneObjet.length - 1) {
	        	if (this.poidsCourant + Main.getPoids(this.longeurCourant + 1) <= Main.MAX_POIDS) {
	        		gauche = new Node(this.selectionneObjet, this.poidsCourant, this.longeurCourant + 1, true);
	        	} 
	        	droite = new Node(this.selectionneObjet, this.poidsCourant, this.longeurCourant + 1, false);
	        }
        } else {
        	// poidsCourant == Main.MAX_POIDS : rien à faire
        }
    }
    
    /**
     * Cette construction est pour le node racine
     * @param selectionneObjet
     */
    public Node(boolean[] selectionneObjet) {
    	// selectionneObjet est statique dans la classe Main, donc il faut le copier pour modifier 
    	this.selectionneObjet = copyBooleanArray(selectionneObjet); 
    	    	
    	poidsCourant = 0;
    	valeurCourant = 0;
    	
    	// Générer ses fils
    	if (selectionneObjet.length != 0) {
    		if (poidsCourant + Main.getPoids(0) <= Main.MAX_POIDS) {
    			gauche = new Node(this.selectionneObjet, poidsCourant, 0, true);
    		}
        	droite = new Node(this.selectionneObjet, poidsCourant, 0, false);
        }
    }
    
    /**
     * Copier les contenues d'un rang à autre
     * @param booleanArray
     * @return
     */
    public boolean[] copyBooleanArray(boolean[] booleanArray) {
    	boolean[] copyArray = new boolean[booleanArray.length];
    	for (int i = 0; i < copyArray.length; i++) {
    		copyArray[i] = booleanArray[i];
    	}
    	
    	return copyArray;
    }
    
    /**
     * Obtenir le meilleur score comme la question 2
     * @return
     */
    public int getMeilleurScore() {
    	int poids = poidsCourant;
    	int valeur = valeurCourant;
    	
    	for (int i = longeurCourant + 1; i < selectionneObjet.length; i++) {
    		if (poids + Main.getPoids(i) > Main.MAX_POIDS) {
    			return valeur;
    		} else if (poids + Main.getPoids(i) == Main.MAX_POIDS) {
    			return valeur + Main.getValeur(i);
    		} else if (poids + Main.getPoids(i) < Main.MAX_POIDS) {
    			poids += Main.getPoids(i);
    			valeur += Main.getValeur(i);
    		}
    	}
    	
    	return valeur;
    }
    
    public boolean estFeuille() {
    	return gauche == null && droite == null;
    }
    
    public boolean[] getSelectionneObjets() {
    	return selectionneObjet;
    }
    
    public Node getGauche() {
    	return gauche;
    }
    
    public Node getDroite() {
    	return droite;
    }
} 
