package rppc.vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import rppc.modele.MonModele;
import rppc.modele.Probleme;
import rppc.modele.Solution;

public class VueGrille extends JPanel implements Observer {

	/**
	 * 
	 */
	private JLabel[][] labels;
	private static final long serialVersionUID = -4460922817084173565L;

	public VueGrille(final MonModele modele) {
		Probleme p = modele.getProbleme();
//		
//		Solution pire_s = new Solution(p);
//		pire_s.solutionRealisable(false);
//		int pireHauteur = pire_s.getHauteur();
//		int largeur = p.getLargeur();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		for(int i=0;i< p.getTaille();i++){
			JLabel j = new JLabel(String.valueOf(p.getObjets().get(i).getIndice()), SwingConstants.CENTER);
			j.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
			c.gridx = modele.getCurrentSolution().getX(i);
			c.gridy = modele.getCurrentSolution().getY(i);
			c.gridwidth = p.getObjets().get(i).getLargeur();
			c.gridheight = p.getObjets().get(i).getHauteur();
			
			add(j);
		}
		
//
//		labels = new JLabel[modele.getTaille()][modele.getTaille()];
//		for (int j = 0; j < modele.getTaille(); j++) {
//			for (int i = 0; i < modele.getTaille(); i++) {
//				labels[i][j] = new JLabel("", SwingConstants.CENTER);
//				labels[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
//				add(labels[i][j]);
//			}
//		}

		update(modele, null);
	}

	@Override
	public void update(Observable o, Object arg) {
//		MonModele m = (MonModele) o;
//		for (int j = 0; j < m.getTaille(); j++)
//			for (int i = 0; i < m.getTaille(); i++)
//				labels[i][j].setText("");
//
//		if (m.isVieMonstre())
//			labels[m.getMonstre().x][m.getMonstre().y].setText("Monstre");
//		else
//			labels[m.getMonstre().x][m.getMonstre().y].setText("");
//		labels[m.getTresor().x][m.getTresor().y].setText("Tresor");
//
//		if (m.isFleche())
//			labels[m.getPersonnage().x][m.getPersonnage().y].setText("Personnage (1)");
//		else
//			labels[m.getPersonnage().x][m.getPersonnage().y].setText("Personnage (0)");
//
//		for (Point p : m.getPiege()) {
//			labels[p.x][p.y].setText("Piege");
//		}
//
//		try {
//			Thread.sleep(300);
//		} catch (InterruptedException e) {
//		}

	}

}
