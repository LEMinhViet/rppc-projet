/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

import java.security.AllPermission;
import java.util.ArrayList;

/**
 *
 */
public class Sac {
    public int poids_max;
    
    // Liste des objets
    public ArrayList<Objet> liste;
    
    // Initialiser un sac avec sa poids maximales
    public Sac(int poids) {
        liste = new ArrayList<Objet>();
        this.poids_max = poids;
    }
    
    /**
     * Cette fonction met les objets dans le sac comme l'algorithmme glouton dans la question 2 
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs(ArrayList<Objet> allObjs) {
        int i = 0;
        int cur_poids = 0;
       
        if (allObjs.size() == 0) {
        	return;
        }
        
        while ((poids_max - cur_poids) / allObjs.get(i).getPoids() > 0) {
        	liste.add(allObjs.get(i));
            cur_poids += allObjs.get(i).getPoids();
            i++;      
            
            if (i >= allObjs.size()) {
        		break;
        	}        	
        }
        
     // Afficher le résultat
        showSac();
    }    
    
    /**
     * Cette fonction met les objets dans le sac comme l'algorithmme Branch-and-Greed
     * (Générer un arbre - stocker tous les possibilités - manque de mémoire)
     * @param racine : le noeuds racine de l'arbre
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs_Arboresente(Node racine, ArrayList<Objet> allObjs) {
    	Node resultat = racine;
    	// Traverser les nodes dans l'arbre jusqu'à on trouve un feuille
    	while (!resultat.estFeuille()) {
    		if (resultat.getGauche() == null) {
    			resultat = resultat.getDroite();
    		}  else {
    			// Si le meilleur score à gauche est mieux, on passe à gauche et vice versa
    			if (resultat.getGauche().getMeilleurScore() >= resultat.getDroite().getMeilleurScore()) {
	    			resultat = resultat.getGauche();
	    		} else {
	    			resultat = resultat.getDroite();
	    		}
    		}
    	}
    	
    	// Ajouter les objets sélectionnés au sac
    	boolean[] selectionneObjets = resultat.getSelectionneObjets();
    	for (int i = 0; i < selectionneObjets.length; i++) {
    		if (selectionneObjets[i]) {
    			liste.add(allObjs.get(i));
    		}
    	}
    	
    	// Afficher le résultat
    	showSac();
    }    
    
    /**
     * Cette fonction met les objets dans le sac comme l'algorithmme Branch-and-Greed
     * (Ne pas Générer un arbre)
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs_Arboresente_sansStockerNodes(ArrayList<Objet> allObjs) {
    	int i = 0;
    	int poids = 0;
    	
    	// Stocker les résultat : selectionneObjets[i] = true ssi on selectionne l'objet i
    	boolean[] selectionneObjets = new boolean[allObjs.size()];
    	
    	while (i < allObjs.size()) {    
    		// Si le poids est supérieur que la capacité du sac, on ne selectionne pas l'objet courant
    		if (poids + allObjs.get(i).getPoids() > Main.MAX_POIDS) {
    			
    		} else {
    			// Si le meilleur score si on selectionne l'objet courant est mieux, on le selectionne et vice versa
    			if (getMeilleurScore(allObjs, i, true, poids) >= getMeilleurScore(allObjs, i, false, poids)) {
	    			poids += allObjs.get(i).getPoids();
	    			selectionneObjets[i] = true;
	    		}
    		}
    		
    		// Si le poids est égal à la capacité du sac, on peut finir
    		if (poids == Main.MAX_POIDS) {
    			break;
    		}     		
    		i++;
    	}
    	
    	// Ajouter les objets sélectionnés au sac
    	for (i = 0; i < selectionneObjets.length; i++) {
    		if (selectionneObjets[i]) {
    			liste.add(allObjs.get(i));
    		}
    	}
    	
    	// Afficher le résultat
    	showSac();
    }    
    
    /**
     * Obtenir le meuilleur score en utilisant l'algorithme glouton
     * @param allObjs : liste de tous les objets on peut mettre dans le sac 
     * @param posCourant : la position de l'objet qu'on est en train de calculer dans la liste allObjs
     * @param selectionneObjCourant : On selectionne l'objet de la position posCourant ou non
     * @param poidsCourant : le poids courant du sac
     * @return 
     */
    public int getMeilleurScore(ArrayList<Objet> allObjs, int posCourant, boolean selectionneObjCourant, int poidsCourant) {
    	int score = 0;
    	int poids = poidsCourant;
    	
    	// Si on selectionne l'objet courant
    	if (selectionneObjCourant) {
    		if (poids + allObjs.get(posCourant).getPoids() < Main.MAX_POIDS) {
    			score += allObjs.get(posCourant).getValeur();
        		poids += allObjs.get(posCourant).getPoids();
    		} else if (poids + allObjs.get(posCourant).getPoids() == Main.MAX_POIDS) {
    			return allObjs.get(posCourant).getValeur();
    		} else { 
    			return 0;
    		}    		
    	}
    	
    	// Selectionne les objets comme l'algorithme glouton
    	for (int i = posCourant + 1; i < allObjs.size(); i++) {
    		if (poids + allObjs.get(i).getPoids() < Main.MAX_POIDS) {
    			score += allObjs.get(i).getValeur();
        		poids += allObjs.get(i).getPoids();
    		} else if (poids + allObjs.get(i).getPoids() == Main.MAX_POIDS) {
    			return score + allObjs.get(i).getValeur();
    		} else { 
    			return score;
    		}  
    	}
    	
    	return score;
    }
    
    /**
     * Passer tous les éléments dans la liste et les afficher
     */
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
}
