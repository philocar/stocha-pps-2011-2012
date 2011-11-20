/*
 * Dessine dans une JFrame une courbe quadratique (setFonction) et un nuage de points (setNuage)
 * Alienor L.
 * octobre 2011
 */
package userInterface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class DessinReservoir extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double[] nuage;
	double[] fonction;
	private double vmax;
	private double vmin;

	public DessinReservoir() {
		super();
	}
	
	public DessinReservoir(boolean display) {
		super();

		if(display){
			JFrame frame = new JFrame("réservoir hydraulique");
			frame.getContentPane().add(this);
			frame.setSize(400, 400);
			frame.setVisible(true);
		}
	}
	
	
	
	public void setNuage(double[] nua, double volumeMax, double volumeMin) {
		if (nua.length < 1 ) {
			return;
		}
		nuage = nua;
		vmax = volumeMax;
		vmin = volumeMin;
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// compute min & max from nuage
		double xmin = 0, xmax = 10, ymin = -10;
		double ymax = vmax;
		double y = 0;
		if (nuage != null) {
			xmin = 0;
			xmax = nuage.length;
			ymin = 0;

			// quelques marges
			double offset = (xmax - xmin) / 10;
			xmin -= offset;
			xmax += offset;
			offset = (ymax - ymin) / 10;
			ymin -= offset;
			ymax += offset;
		}
		int ydr, idr;

		
		
		// axis
		g.setColor(new Color(0, 129, 21));
		idr = (int) (-xmin * getWidth() / (xmax - xmin));
		ydr = (int) (-ymin * getHeight() / (ymax - ymin));
		g.drawLine(idr, getHeight(), idr, 0);
		int hauteurAxe = (getHeight() - 5) - ydr;
		g.drawLine(0, hauteurAxe, getWidth(),hauteurAxe);

		// volume max
		int hauteurMax =  getHeight() - 5 - (int) ((vmax - ymin) * getHeight() / (ymax - ymin));
		g.setColor(new Color(180, 0, 0));
		g.drawLine(0, hauteurMax, getWidth(),  hauteurMax );
		char[] text = ("volume max (m³) : "+vmax).toCharArray();
		g.drawChars(text, 0, text.length, 10, hauteurMax+15);
		
		// volume min
		int hauteurMin =  getHeight() - 5 - (int) ((vmin - ymin) * getHeight() / (ymax - ymin));
		g.drawLine(0, hauteurMin, getWidth(),  hauteurMin );
		text = ("volume min : "+vmin).toCharArray();
		g.drawChars(text, 0, text.length, 10, hauteurMin+15);

		if (nuage != null) {
			int delta = (getHeight()+getWidth())/100;
			for (int i=0; i<nuage.length; i++) {

				y = nuage[i];
				idr = (int) ((i - xmin) * getWidth() / (xmax - xmin));
				ydr = (int) ((y - ymin) * getHeight() / (ymax - ymin));
				//jours sur l'axe
				g.setColor(new Color(0, 129, 21));
				g.drawLine(idr, hauteurAxe-delta , idr, hauteurAxe + delta );
				text = (""+i).toCharArray();
				g.drawChars(text, 0, text.length, idr, hauteurAxe+delta+10);
				
				
				// dessin d'une petite croix
				g.setColor(Color.WHITE);
				g.drawLine(idr + delta, getHeight() - 5 - ydr, idr - delta, getHeight()
						- 5 - ydr);
				g.drawLine(idr,getHeight() - 5 - ydr+delta, idr, getHeight() - 5 - ydr-delta);
				
			}
			//nom de l'abscisse
			g.setColor(new Color(0, 129, 21));
			text = "jours".toCharArray();
			g.drawChars(text, 0, text.length, idr/nuage.length*(nuage.length+1), hauteurAxe+delta+10);
		}

	}


	public static void main(String[] arg) {
	

		// points y Ã  dessiner autour de la courbe, x allant de 0 à max.
		double[] nua = {  2 , 1 , 4 , 1 , 2 , 3 , 1, 10 };
		double pmax = 10;
		double pmin = 1;
		DessinReservoir lf = new DessinReservoir();
		JFrame frame = new JFrame("réservoir");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(lf);
		frame.setSize(400, 400);
		frame.setVisible(true);
		lf.setNuage(nua, pmax, pmin);	
	}

}
