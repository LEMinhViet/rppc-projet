/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

import java.util.ArrayList;

/**
 *
 */
public class Sac {
	// La solution qu'on est en train de calculer
	private int[] solutionCourant;
	// La meilleure solution qu'on a trouve 
    private int[] solutionMeilleure;
    
    // La valeur du sac dans le cas de la meilleure solution
    private float valeurMeilleure = -1;
    // La valeur de la solution courante
    private float valeurCourante;
    // Le poids de la solution courante
    private float poidsCourant;
    // La valeur de la borne superieure de la solution courante
    private float valeurBorne;
    // Le poids de la borne superieure de la solution courante
    private float poidsBorne;
    
    // La position de l'objet qu'on est en train de travailler = le niveau de l'arbre
    private int niveauCourant = -1;
    private int niveauBorne;
   
    private int tmp;
    
    // La capacité du sac 
    public int poids_max;
    
    
    // Initialiser un sac avec sa poids maximales
    public Sac(int poids) {
        this.poids_max = poids;
    }
    
    /**
     * Question 2.3
     * Cette fonction met les objets dans le sac comme l'algorithmme glouton dans la question 2 
     * - Trier les objets par l'ordre croissant 
     * - Mettre les objets jusqu'a ce qu'on ne peut pas
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs_Glouton(ArrayList<Objet> allObjs) {
    	// Trier les objets
    	ArrayList<Objet> objs = new ArrayList<Objet>();
    	objs.addAll(allObjs);    	
    	objs = Main.trierObjets_Merge(objs);
    	
    	solutionMeilleure = new int[objs.size()];
        
        int i = 0;
        int poids = 0;
       
        if (objs.size() == 0) 	return;
        
        while (poids + objs.get(i).getPoids() <= poids_max) {
        	solutionMeilleure[i] = 1;
        	poids += objs.get(i).getPoids();
            i++;      
            
            if (i >= objs.size()) 	break;
        }
        // Afficher le resultat
        showSac(objs);
    }     
    
    /**
     * Question 2.4
     * Cette fonction met les objets dans le sac par la methode Branch-and-Bound
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs_BranchAndBound(ArrayList<Objet> allObjs) {
    	// D'abord, on trie les objets
    	ArrayList<Objet> objs = new ArrayList<Objet>();
    	objs.addAll(allObjs);
    	objs = Main.trierObjets_Merge(objs);
    	
    	solutionCourant = new int[objs.size()];
        solutionMeilleure = new int[objs.size()];
    	
    	while (true) {
    		while (getBorne(objs) <= valeurMeilleure) {
    			// Quand la borne superieure est inferieure que la meilleure valeur courante, on fait la retour arriere
    			// Dans le premier fois, la meilleure valeur = -1 => le programme n'utilise pas ce boucle "while" avant qu'il 
    			// trouve le premier solution
    			while (niveauCourant != 0 && solutionCourant[niveauCourant] != 1) {
    				// Backtrack quand l'objet de "niveauCourant" n'est pas dans le sac
    				niveauCourant--;
    			}
    			
    			// Si on a retoune a la racine, on s'arrete et affiche le resultat
    			if (niveauCourant == 0) {
    				showSac(objs);
    				return;
    			}
    			
    			// Backtrack - Enlever un objet dans le sac et re-calculer la borne
    			solutionCourant[niveauCourant] = 0;
    			valeurCourante -= objs.get(niveauCourant).getValeur();
    			poidsCourant -= objs.get(niveauCourant).getPoids();
    		}
    		
    		// Si la borne est superieure que la meilleure solution courante, ...
    		valeurCourante = valeurBorne;
    		poidsCourant = poidsBorne;
    		niveauCourant = niveauBorne;
    		
    		// Si on trouve un feuille, et la borne est encore superieure que la meilleur valeur courante
    		// => on a une nouvelle meilleure solution => Changer les variables
    		// Si non, on continue 
    		if (niveauCourant == objs.size()) {
    			valeurMeilleure = valeurCourante;
    			System.arraycopy(solutionCourant, 0, solutionMeilleure, 0, objs.size());
    			niveauCourant--;
    		} else {
    			// Passer l'objet courant pour essayer de mettre les objets apres au sac
    			solutionCourant[niveauCourant] = 0;
    		}
    	}
    }    
    
    /**
     * Calculer la borne superieure
     * @param objs
     * @return
     */
    public float getBorne(ArrayList<Objet> objs) {
    	boolean fini = false;
    	float borneSup = -1;
    	
    	valeurBorne = valeurCourante;
    	poidsBorne = poidsCourant;
    	niveauBorne = niveauCourant + 1;
    	
    	// En utilisant l'algorithme glouton, on met les objets a partir de l'objet de "niveauBorne" 
    	while (niveauBorne < objs.size() && !fini) {
    		if (poidsBorne + objs.get(niveauBorne).getPoids() <= poids_max) {
    			valeurBorne += objs.get(niveauBorne).getValeur();
    			poidsBorne += objs.get(niveauBorne).getPoids();
    			solutionCourant[niveauBorne] = 1;
    		} else {
    	    	// on s'arrete quand on ne peut pas mettre l'objet suivant dans le sac.
    			// et calculer la borne superieure
    			borneSup = valeurBorne + (Main.poidsMax - poidsBorne) * objs.get(niveauBorne).getValeur() / objs.get(niveauBorne).getPoids();
    			fini = true;
    		}
    		niveauBorne++;
    	}
    	
    	 
    	if (fini) {
    		niveauBorne--; 		// Si on n'a pas encore parcouri tous les objets, retourne l'objet qui est totalement dans le sac
    		return borneSup;	// Retourne la borne superieure
    	} else {
    		// Si on a parcouri tous les objets, on a une solution => retourne la valeur de cette solution
    		return valeurBorne;
    	}
    }
    
    /**
     * Question 2.5
     * Cette fonction met les objets dans le sac comme l'algorithmme Branch-and-Greed
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs_BranchAndGreed(ArrayList<Objet> allObjs) {
    	// Trier les objets
    	ArrayList<Objet> objs = new ArrayList<Objet>();
    	objs.addAll(allObjs);
    	objs = Main.trierObjets_Merge(objs);
    	
    	solutionCourant = new int[objs.size()];
        solutionMeilleure = new int[objs.size()];
    	
        tmp = 0;
		while (true) {
			// Quand la borne superieure est inferieure que la meilleure valeur courante, on enleve le tmp-ieme objet
			// et re-calculer la borne
			while (getBorne(objs) <= valeurMeilleure) {
    			solutionCourant[tmp] = 0;
    			valeurCourante -= objs.get(tmp).getValeur();
    			poidsCourant -= objs.get(tmp).getPoids();
    			tmp++;
    			
    			// Si on a essaye d'enleve tous les objets, on s'arrete
    			if (tmp == objs.size()) {
    				showSac(objs);
    				return;
    			}    			
    		}
    		
			// Si la borne est superieure que la meilleure solution courante, ...
    		valeurCourante = valeurBorne;
    		poidsCourant = poidsBorne;
    		niveauCourant = niveauBorne;
    		
    		// Si on trouve un feuille, et la borne est encore superieure que la meilleur valeur courante
    		// => on a une nouvelle meilleure solution => Changer les variables
    		// Si non, on continue 
    		if (niveauCourant == objs.size()) {
    			valeurMeilleure = valeurCourante;
    			System.arraycopy(solutionCourant, 0, solutionMeilleure, 0, objs.size());
    			niveauCourant--;    			
    		} else {
    			// Passer l'objet courant pour essayer de mettre les objets apres au sac
    			solutionCourant[niveauCourant] = 0;
    		}
    	}
    } 
    
    /**
     * Passer tous les Elements dans la liste et les afficher
     */
    public void showSac(ArrayList<Objet> objs) {
        int cur_poids = 0;
        int cur_valeur = 0;
        
        for (int i = 0; i < solutionMeilleure.length; i++) {
//        	System.out.println("objet " + i + " " + objs.get(i).getPoids() + " " + 
//        											objs.get(i).getValeur() + " " + 
//        											solutionMeilleure[i]);
        	if (solutionMeilleure[i] == 1) {
	        	cur_poids += objs.get(i).getPoids();
			    cur_valeur += objs.get(i).getValeur();
        	}
        }        
        System.out.println("Total - Poids : " + cur_poids + " Valeur : " + cur_valeur);
    }
}
