package rppc.vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import rppc.modele.MonModele;
import rppc.modele.Probleme;

public class Fenetre extends JFrame implements Observer {


	private MonModele modele;
	
	private final static int TINIT = 60;
	private final static int TAILLE = 25;
	
	private static final long serialVersionUID = 4042713508717400450L;

	public Fenetre(MonModele modele) {
		this.modele = modele;
		modele.addObserver(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		setSize(400, 400);
		repaint();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Font font = new Font("Arial", Font.PLAIN, 14);	    
	    g.setFont(font);
	    g.drawString("qualite (hauteur) : "+ modele.getCurrentSolution().getHauteur(), TINIT, TINIT - 10);
		
		Probleme p = modele.getProbleme();
		
		int max = modele.getCurrentSolution().getHauteur();
		
//		
//		modele.getCurrentSolution().affichageSolution();
		for(int i=0;i< p.getTaille();i++){
			int x = modele.getCurrentSolution().getX(i);
			
			int width = p.getObjets().get(i).getLargeur();
			int height = p.getObjets().get(i).getHauteur();
			int y = max - modele.getCurrentSolution().getY(i)- height;
			
//			System.out.println(i+" "+y+" "+modele.getCurrentSolution().getY(i));
			g.setColor(Color.BLUE);
			g.drawRect(TINIT+x*TAILLE, TINIT+y*TAILLE , width*TAILLE, height*TAILLE);
			g.drawString(String.valueOf(i), TINIT+x*TAILLE+4, TINIT+y*TAILLE + 15);
			
		}
		
		g.setColor(Color.RED);
		g.drawLine(TINIT -2, TINIT, TINIT -2, max *TAILLE + TINIT);
		g.drawLine(TINIT + p.getLargeur()*TAILLE +2, TINIT, TINIT + p.getLargeur()*TAILLE + 2, max *TAILLE + TINIT);

	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
}
