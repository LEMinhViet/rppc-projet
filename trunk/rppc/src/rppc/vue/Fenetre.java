package rppc.vue;

import java.awt.Container;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 4042713508717400450L;

	public Fenetre(MonModele modele) {
		modele.addObserver(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container co =getContentPane();
		
		Probleme p = modele.getProbleme();
		
		co.setLayout(new GridBagLayout());
		
		
		for(int i=0;i< p.getTaille();i++){
			JLabel j = new JLabel(String.valueOf(p.getObjets().get(i).getIndice()), SwingConstants.CENTER);
			j.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
			GridBagConstraints c = new GridBagConstraints();
			//c.fill = GridBagConstraints.;
			c.gridx = modele.getCurrentSolution().getX(i);
			c.gridy = modele.getCurrentSolution().getY(i);
			c.gridwidth = p.getObjets().get(i).getLargeur();
			System.out.println(c.gridwidth);
			c.gridheight = p.getObjets().get(i).getHauteur();
			
			co.add(j, c);
		}
		
		pack();
		setVisible(true);
		setSize(800, 600);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
