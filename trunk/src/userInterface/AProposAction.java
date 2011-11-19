package userInterface;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class AProposAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFenetre fenetre;
	
	public AProposAction(MainFenetre fenetre, String texte){
		super(texte);
		
		this.fenetre = fenetre;
	}
	
	public void actionPerformed(ActionEvent e) { 
		JOptionPane.showMessageDialog(fenetre, "Ce logiciel a été conçu dans le cadre d'un projet\n" +
				" de programmation stochastique \n en 5e année de Polytech'Paris-Sud." +
				"\n Concepteurs du logiciel : \nFabien BINI, Nathanaël MASRI et Nicolas Poirier" +
				"\n Responsable du projet : Abdel LISSER");
	} 
}