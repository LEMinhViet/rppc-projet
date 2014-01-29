package rppc.vue;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import rppc.modele.MonModele;
import rppc.modele.Probleme;

public class Fenetre extends JFrame implements Observer {


	private MonModele modele;
	
	private static final long serialVersionUID = 4042713508717400450L;

	public Fenetre(MonModele modele) {
		this.modele = modele;
		modele.addObserver(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container co = getContentPane();
		
		Probleme p = modele.getProbleme();
		
		co.setLayout(new GridBagLayout());
		
		pack();
		setVisible(true);
		setSize(800, 600);
		
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		Probleme p = modele.getProbleme();
		
		int max = modele.getCurrentSolution().getHauteur();
		
		for(int i=0;i< p.getTaille();i++){
			JLabel j = new JLabel(String.valueOf(p.getObjets().get(i).getIndice()), SwingConstants.CENTER);
//			JButton j = new JButton(String.valueOf(p.getObjets().get(i).getIndice()));
			j.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			
			GridBagConstraints c = new GridBagConstraints();
//			c.fill = GridBagConstraints.CENTER;
			c.gridx = modele.getCurrentSolution().getX(i);
			c.gridy = max - modele.getCurrentSolution().getY(i);
			c.gridwidth = p.getObjets().get(i).getLargeur();
			System.out.println(modele.getCurrentSolution().getY(i)+" "+(8 - modele.getCurrentSolution().getY(i)));
			c.gridheight = p.getObjets().get(i).getHauteur();
			c.ipadx = c.gridwidth * 20;
			c.ipady = c.gridheight * 20;
			
//			co.add(j, c);
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		Probleme p = modele.getProbleme();
		Container co = getContentPane();
		co.removeAll();
		
		int max = modele.getCurrentSolution().getHauteur();
		
		for(int i=0;i< p.getTaille();i++){
			JLabel j = new JLabel(String.valueOf(p.getObjets().get(i).getIndice()), SwingConstants.CENTER);
//			JButton j = new JButton(String.valueOf(p.getObjets().get(i).getIndice()));
			j.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			
			GridBagConstraints c = new GridBagConstraints();
//			c.fill = GridBagConstraints.CENTER;
			c.gridx = modele.getCurrentSolution().getX(i);
			c.gridy = max - modele.getCurrentSolution().getY(i);
			c.gridwidth = p.getObjets().get(i).getLargeur();
			System.out.println(modele.getCurrentSolution().getY(i)+" "+(8 - modele.getCurrentSolution().getY(i)));
			c.gridheight = p.getObjets().get(i).getHauteur();
			c.ipadx = c.gridwidth * 20;
			c.ipady = c.gridheight * 20;
			
			co.add(j, c);
		}
		
		pack();
		setSize(800, 600);
		repaint();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
}
