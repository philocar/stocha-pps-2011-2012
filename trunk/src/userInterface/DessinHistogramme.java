/*
 * Dessine dans une JFrame une courbe quadratique (setFonction) et un nuage de points (setNuage)
 * Alienor L.
 * octobre 2011
 */
package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class DessinHistogramme extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double[][] nuage;
	private int nbJours;
	private static Color[] couleurs = {
		new Color(157, 164, 244), 
		new Color(253, 200, 0), 
		new Color(255, 255, 0), 
		new Color(170, 85, 0),
		new Color(26, 40, 223)
	};

	public DessinHistogramme() {
		super();
	}

	public DessinHistogramme(boolean display) {
		super();

		if (display) {
			JFrame frame = new JFrame("centrales");
			frame.getContentPane().add(this);
			frame.setSize(400, 400);
			frame.setVisible(true);
		}
	}

	public void setNuage(double[][] points) {
		if (points.length != 5) {
			return;
		}
		nuage = points;
		nbJours = nuage[0].length;
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// compute min & max from nuage
		double pmax = 0; // la puissance maximale atteinte
		double puissJ; // la puissance du jour

		for (int j = 0; j < nbJours; j++) {
			puissJ = 0;
			for (int i = 0; i < nuage.length; i++) {
				puissJ += nuage[i][j];
			}
			if (puissJ > pmax) {
				pmax = puissJ;
			}
		}

		// *
		double xmin = 0, xmax = 10, ymin = -10;
		double ymax = pmax*1.3;
		xmax = nbJours;
		ymin = 0;

		// quelques marges
		double offset = (xmax - xmin) / 10;
		xmin -= offset;
		xmax += offset;
		offset = (ymax - ymin) / 10;
		ymin -= offset;
		ymax += offset;
		int ydr, idr;
		int delta = (getHeight() + getWidth()) / 100;
		int delta_x = getWidth() / 50;
		int offset_x = (int) (xmin * getWidth() / ( xmin - xmax));
		int delta_y = getHeight() / 40;
		
		// axis
		g.setColor(new Color(0, 129, 21));
		idr = (int) (-xmin * getWidth() / (xmax - xmin));
		ydr = (int) (-ymin * getHeight() / (ymax - ymin));
		g.drawLine(idr, getHeight(), idr, 0);
		int hauteurAxe = (getHeight() - 5) - ydr;
		g.drawLine(0, hauteurAxe, getWidth(), hauteurAxe);
		

		g.setColor(couleurs[0]);
		g.fillRect(idr,  delta_y, 4*delta_x, (int) (1.5*delta_y));
		g.drawString("centrale 1", delta_x*10, (int) (2*delta_y+2));
		
		g.setColor(couleurs[1]);
		g.fillRect(idr,  4*delta_y, 4*delta_x, (int) (1.5*delta_y));
		g.drawString("centrale 2", delta_x*10, (int) (5*delta_y+2));
		
		g.setColor(couleurs[2]);
		g.fillRect(idr,  7*delta_y, 4*delta_x, (int) (1.5*delta_y));
		g.drawString("centrale 3", delta_x*10, (int) (8*delta_y+2));


		g.setColor(couleurs[3]);
		g.fillRect(idr+20*delta_x,  delta_y, 4*delta_x, (int) (1.5*delta_y));
		g.drawString("centrale 4", delta_x*30, (int) (2*delta_y+2));
		
		g.setColor(couleurs[4]);
		g.fillRect(idr+20*delta_x,  4*delta_y, 4*delta_x, (int) (1.5*delta_y));
		g.drawString("réservoir hydraulique", delta_x*30, (int) (5*delta_y+2));


		// puissance max
		int hauteurMax = getHeight() - 5
				- (int) ((pmax - ymin) * getHeight() / (ymax - ymin));
		g.setColor(new Color(180, 0, 0));
		g.drawLine(offset_x, hauteurMax, getWidth(), hauteurMax);
		g.drawString("puissance max : " + (int)pmax+" MW",  delta_x*24, hauteurMax - 5);

		double puissance = hauteurAxe;
		int ydrPrec;

		for (int numJour = 0; numJour < nbJours; numJour++) {
			idr = (int) ((numJour - xmin) * getWidth() / (xmax - xmin));
			puissance = 0;
			ydr = (int) ((puissance - ymin) * getHeight() / (ymax - ymin));
			
			for (int idCentrale = 0; idCentrale < nuage.length; idCentrale++) {
				g.setColor(couleurs[idCentrale]);
				puissance += nuage[idCentrale][numJour];
				ydrPrec = ydr;
				ydr = (int) ((puissance - ymin) * getHeight() / (ymax - ymin));
					// dessin d'un rectangle représentant la puissance
				g.fillRect(idr, getHeight() - 5 - ydr, 4*delta_x, ydr - ydrPrec);
			}
		}
		g.setColor(new Color(0, 129, 21));
		idr = (int) ((0 - xmin) * getWidth() / (xmax - xmin));
		for (int i = 0; i < nbJours; i++) {
			// jours sur l'axe
			idr = (int) ((i - xmin) * getWidth() / (xmax - xmin));
			g.drawLine(idr, hauteurAxe, idr, hauteurAxe + delta);
			g.drawString(""+i, idr, hauteurAxe + delta + 10);

		}
		// nom de l'abscisse
		g.setColor(new Color(0, 129, 21));
		g.drawString("jours", idr / nuage[0].length
				* (nuage[0].length + 1), hauteurAxe + delta + 10);

	}

	public static void main(String[] arg) {

		// points y Ã  dessiner autour de la courbe, x allant de 0 à max.
		/*	ArrayList<double[]> points = new ArrayList<double[]>();
		double[] n = { 2, 1, 4, 0, 2, 3, 1, 10 };
		double[] nu = { 2, 1, 4, 0, 2, 3, 1, 10 };
		double[] nua = { 2, 1, 4, 0, 2, 3, 1, 10 };
		double[] nuag = { 2, 1, 4, 0, 2, 3, 1, 10 };
		double[] nuage = { 2, 1, 4, 0, 2, 3, 1, 10 };*/
		double[][] nuages = { { 2, 1, 3, 0, 2, 3, 1, 1 },
				{ 2, 1, 1, 0, 2, 3, 0, 1 }, { 2, 1, 0, 2, 2, 3, 1, 1 },
				{ 2, 1, 1, 0, 2, 3, 1, 1 }, { 2, 1, 4, 0, 2, 3, 1, 1 } };
		/*
		 * points.add(n); points.add(nu); points.add(nua); points.add(nuag);
		 * points.add(nuage);
		 */
		DessinHistogramme lf = new DessinHistogramme(true);
		/*
		 * JFrame frame = new JFrame("centrale");
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.getContentPane().add(lf); frame.setSize(400, 400);
		 * frame.setVisible(true);
		 */
		lf.setNuage(nuages);
	}

}
