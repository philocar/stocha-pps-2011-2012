package userInterface;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import manager.solveurs.Solveur;
import manager.solveurs.PL.CplexEnergieRecours;
import data.DataRecours;

public class Optimise extends AbstractAction {
	/**
         * 
         */
	private static final long serialVersionUID = 1L;
	private MainFenetre fenetre;
	private Solveur solveur;
	private String fileName;

	public Optimise(MainFenetre fenetre, String texte) {
		super(texte);
		this.fenetre = fenetre;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void actionPerformed(ActionEvent e) {

		fenetre.setEtat(MainFenetre.State.FICHIER_CHOISI);
		String methodeName = (String) fenetre.getChoixMethode()
				.getSelectedItem();

		if (methodeName.equals("modèle probabiliste")) {

			ZDialogParamDeter zd = new ZDialogParamDeter(fenetre,
					"Choix des paramètres", true, fileName);
			zd.setVisible(true);
		} else {
			// on normalise les répertoires
			if (fileName.charAt(fileName.length() - 1) != File.separatorChar)
				fileName += File.separator;

			if (methodeName.equals("recuit simulé")) {

				ZDialogParamRS zd = new ZDialogParamRS(fenetre,
						"Choix des paramètres", true, fileName);
				zd.setVisible(true);
				solveur = zd.getSolveur();
			} else if (methodeName.equals("modèle avec recours")) {
				DataRecours data = new DataRecours(
						fileName + "Données_Recours_parametres_hydraulique.csv",
						fileName + "Données_Recours_capacite_max.csv",
						fileName + "Données_Recours_scenarios_demande.csv",
						fileName
								+ "Données_Recours_scenarios_coeff_dispo_centrale1.csv",
						fileName
								+ "Données_Recours_scenarios_coeff_dispo_centrale2.csv",
						fileName
								+ "Données_Recours_scenarios_coeff_dispo_centrale3.csv",
						fileName
								+ "Données_Recours_scenarios_coeff_dispo_centrale4.csv");
				solveur = new CplexEnergieRecours(data);
			} else if (methodeName.equals("relaxation du binaire")) {

				ZDialogProbaBinaire proba = new ZDialogProbaBinaire(fenetre, fileName);
				solveur = proba.getSolveurRelaxe();
			}

			else if (methodeName.equals("modèle binaire")) {

				ZDialogProbaBinaire proba = new ZDialogProbaBinaire(fenetre, fileName);
				solveur = proba.getSolveurBinaire();
			}

			if (solveur != null) { // si l'utilisateur a annulé l'action
				System.out.println("début des calculs");
				fenetre.setDescription("calcul en cours");
				solveur.lancer();
				System.out.println("fin des calculs");
				fenetre.setSolution(solveur.getSolution());
				fenetre.setDescription("calcul terminé");
				fenetre.setEtat(MainFenetre.State.RESULTAT_CALCULE);
			}
		}
		fenetre.updateVisibility();
		// la relaxation du binaire ne peut pas afficher de solution
		 if (methodeName.equals("relaxation du binaire")) {
			 fenetre.cacherResultat();
		 }
		 

	}

	public Solveur getSolveur() {
		return solveur;
	}

	public void setSolveur(Solveur solveur) {
		this.solveur = solveur;
	}
}
