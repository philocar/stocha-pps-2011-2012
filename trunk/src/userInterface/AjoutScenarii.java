package userInterface;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AjoutScenarii extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFenetre fenetre;
	private int nbJours;
	private JPanel panel;
	private JButton scenariiCentrales;
	private JButton scenariiReservoir;
	
	public AjoutScenarii(MainFenetre fenetre, String texte){
		super(texte);
		
		this.fenetre = fenetre;
	}
	
	public void actionPerformed(ActionEvent e) { 
		// récupération des données
	//	nbJours = Integer.parseInt(fenetre.getJours().getText());//On convertit cette valeur en un nombre
	
		// si tout va bien, on efface les données de la fenêtre
		fenetre.effaceOption1();
		panel = fenetre.getPanel();
		fenetre.getScenariiCentrales().setVisible(true);
		fenetre.getScenariiReservoir().setVisible(true);
		fenetre.repaint();
	
	} 
}