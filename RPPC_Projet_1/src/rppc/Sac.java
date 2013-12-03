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
    public int poids_max;
    
    // Liste des objets
    public ArrayList<Objet> liste;
    
    // Initialiser un sac avec sa poids maximales
    public Sac(int poids) {
        liste = new ArrayList<Objet>();
        this.poids_max = poids;
    }
    
    /**
     * Question 2.3
     * Cette fonction met les objets dans le sac comme l'algorithmme glouton dans la question 2 
     * x = [0, 1]
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs_Glouton(ArrayList<Objet> allObjs) {
    	ArrayList<Objet> objs = copierArray(allObjs);
    	objs = Main.trierObjets(objs);
    	
        int i = 0;
        int poids = 0;
        float[] selectionneObjets = new float[objs.size()];
        
        if (objs.size() == 0) {
        	return;
        }
        
        // Selectionner les objets de 0 à r - 1 avec x = 1
        while (poids + objs.get(i).getPoids() <= Main.poidsMax) {
        	selectionneObjets[i] = 1;
            poids += objs.get(i).getPoids();
            i++;      
            
            if (i >= objs.size()) {
        		break;
        	}        	
        }
        
     // Selectionner l'objet r avec 0 < x < 1
        if (i < objs.size()) {
        	selectionneObjets[i] = (float)(Main.poidsMax - poids) / objs.get(i).getPoids();
        }        
        
        // Ajouter les objets sélectionnés au sac
    	for (i = 0; i < selectionneObjets.length; i++) {
    		if (selectionneObjets[i] != 0) {
    			liste.add(new Objet(objs.get(i).getPoids(), objs.get(i).getValeur(), selectionneObjets[i]));
    		}
    	}
    	
        // Afficher le résultat
        showSac();
    }     
    
    /**
     * Question 2.4
     * Cette fonction met les objets dans le sac par la méthode arboresente proposée
     * x = [0, 1]
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs_Arboresente(ArrayList<Objet> allObjs) {
    	// objs est pour stocker le sous-arbre qu'on va examiner dans chaque étape
    	ArrayList<Objet> objs = copierArray(allObjs);
    	// objsTrie est comme objs mais il est trié pour la fonction "evaluer"
    	ArrayList<Objet> objsTrie;
    	
    	int i = 0;
    	int poids = 0;
    	int valeur = 0;
    	    	
    	// Stocker les résultat : selectionneObjets[i] = true ssi on selectionne l'objet i
    	float[] selectionneObjets = new float[allObjs.size()];
    	
    	while (i < allObjs.size()) {    
    		// Si le poids est supérieur que la capacité du sac, on ne selectionne pas l'objet courant
    		if (poids + allObjs.get(i).getPoids() > Main.poidsMax) {
    			objs.remove(0);
    			objsTrie = copierArray(objs);
    	    	
    	    	if (valeur + allObjs.get(i).getValeur() * (float)(Main.poidsMax - poids) / allObjs.get(i).getPoids() 
					>= 
					evaluer(poids, valeur, objsTrie)) {
				
					selectionneObjets[i] = (float)(Main.poidsMax - poids) / allObjs.get(i).getPoids();
					poids = Main.poidsMax;
					valeur += allObjs.get(i).getValeur() * selectionneObjets[i];	    			
				}    	    	
    		} else {
    			objs.remove(0);
    			objsTrie = new ArrayList<Objet>();
    			
    	    	for (int j = 0; j < objs.size(); j++) {
    	    		objsTrie.add(objs.get(j));
    	    	}

    	    	// Comparer 2 cas : prendre ou ne pas prendre l'objet i, qui est meiux  
    	    	// Si c'est mieux qu'on le prendre, modifier les variables
    	    	if (evaluer(poids + allObjs.get(i).getPoids(), 
    						valeur + allObjs.get(i).getValeur(), 
    						objsTrie)
    				>= 
					evaluer(poids, valeur, objsTrie)) {
    				
    				poids += allObjs.get(i).getPoids();
    				valeur += allObjs.get(i).getValeur();
	    			selectionneObjets[i] = 1;
    			}
    		}
    		
    		// Si le poids est égal à la capacité du sac, on peut finir
    		if (poids == Main.poidsMax) {
    			break;
    		}     
    		
    		i++;
    	}
    	
    	// Ajouter les objets sélectionnés au sac
    	for (i = 0; i < selectionneObjets.length; i++) {
    		if (selectionneObjets[i] != 0) {
    			liste.add(new Objet(allObjs.get(i).getPoids(), allObjs.get(i).getValeur(), selectionneObjets[i]));
    		}
    	}
    	
    	// Afficher le résultat
    	showSac();
    }    
    
    /**
     * Evaluation - Trier les objets restes et les prendre comme le méthodes dans la question 2.2
     * x = [0, 1]
     * @param poidsCourant 
     * @param valeurCourant
     * @param liste
     * @return 
     */
    public int evaluer(int poidsCourant, int valeurCourant, ArrayList<Objet> liste) {
    	int poids = poidsCourant;
    	int valeur = valeurCourant;
    	int i = 0;
    	liste = Main.trierObjets(liste);
    	
    	 while (liste.size() != 0 && poids + liste.get(i).getPoids() <= Main.poidsMax) {
         	 valeur += liste.get(i).getValeur();
    		 poids += liste.get(i).getPoids();
             i++;      
             
             if (i >= liste.size()) {
         		break;
         	}        	
         }
    	 
    	 if (i < liste.size()) {
    		 valeur += liste.get(i).getValeur() * (float)(Main.poidsMax - poids) / liste.get(i).getPoids();
         }  
    	 
    	 return valeur;
    }
    
    /**
     * Question 2.5
     * Cette fonction met les objets dans le sac comme l'algorithmme Branch-and-Greed
     * x = {0, 1}
     * @param allObjs : Liste de tous les objets on peut mettre dans le sac
     */
    public void getObjs_Branch_and_greed(ArrayList<Objet> allObjs) {
    	int i = 0;
    	int poids = 0;
    	
    	// Stocker les résultat : selectionneObjets[i] = true ssi on selectionne l'objet i
    	float[] selectionneObjets = new float[allObjs.size()];
    	
    	while (i < allObjs.size()) {    
    		// Si le poids est supérieur que la capacité du sac, on ne selectionne pas l'objet courant
    		if (poids + allObjs.get(i).getPoids() > Main.poidsMax) {
    			
    		} else {
    			// Si le meilleur score si on selectionne l'objet courant est mieux, on le selectionne et vice versa
    			if (getMeilleurScore(allObjs, i, true, poids) >= getMeilleurScore(allObjs, i, false, poids)) {
	    			poids += allObjs.get(i).getPoids();
	    			selectionneObjets[i] = 1;
	    		}
    		}
    		
    		// Si le poids est égal à la capacité du sac, on peut finir
    		if (poids == Main.poidsMax) {
    			break;
    		}     		
    		i++;
    	}
    	
    	// Ajouter les objets sélectionnés au sac
    	for (i = 0; i < selectionneObjets.length; i++) {
    		if (selectionneObjets[i] != 0) {
    			liste.add(new Objet(allObjs.get(i).getPoids(), allObjs.get(i).getValeur(), selectionneObjets[i]));
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
    		if (poids + allObjs.get(posCourant).getPoids() < Main.poidsMax) {
    			score += allObjs.get(posCourant).getValeur();
        		poids += allObjs.get(posCourant).getPoids();
    		} else if (poids + allObjs.get(posCourant).getPoids() == Main.poidsMax) {
    			return allObjs.get(posCourant).getValeur();
    		} else { 
    			return 0;
    		}    		
    	}
    	
    	// Selectionne les objets comme l'algorithme glouton
    	for (int i = posCourant + 1; i < allObjs.size(); i++) {
    		if (poids + allObjs.get(i).getPoids() < Main.poidsMax) {
    			score += allObjs.get(i).getValeur();
        		poids += allObjs.get(i).getPoids();
    		} else if (poids + allObjs.get(i).getPoids() == Main.poidsMax) {
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
            System.out.println("objet no " + (i + 1) + 
            				   " avec le Poids :  " + liste.get(i).getPoids() + 
            				   " et la valeur : " + liste.get(i).getValeur() + 
            				   " x : " + liste.get(i).getPartie());
            cur_poids += liste.get(i).getPoids() * liste.get(i).getPartie();
	        cur_valeur += liste.get(i).getValeur() * liste.get(i).getPartie();
        }
        
        System.out.println("Total " + liste.size() + " Poids : " + cur_poids + " Valeur : " + cur_valeur);
    }
    
    /**
     * Copier un ArrayList<Objet> à autre
     */
    public ArrayList<Objet> copierArray(ArrayList<Objet> listeCopie) {
    	ArrayList<Objet> newListe = new ArrayList<Objet>();
    	for (int i = 0; i < listeCopie.size(); i++) {
    		newListe.add(listeCopie.get(i));
    	}
    	
    	return newListe;
    }
}
