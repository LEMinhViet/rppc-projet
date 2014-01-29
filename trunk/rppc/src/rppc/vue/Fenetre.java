package rppc.vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import rppc.modele.MonModele;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4042713508717400450L;

	public Fenetre(MonModele modele) {
		VueGrille vue = new VueGrille(modele);
		modele.addObserver(vue);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(vue);
		pack();
		setVisible(true);
		setSize(800, 600);
	}
}
