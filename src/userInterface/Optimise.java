package userInterface;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import manager.solveurs.Solveur;
import manager.solveurs.PL.CplexEnergieBinaireRelaxe;
import manager.solveurs.PL.CplexEnergieRecours;
import data.DataBinaire;
import data.DataRecours;
import data.solution.Solution;

public class Optimise extends AbstractAction {
	/**
         * 
         */
	private static final long serialVersionUID = 1L;
	private MainFenetre fenetre;
	private Solveur solveur;
	private Solution solution;
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
					"Choix des paramètres", true);
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
				DataBinaire data = new DataBinaire(0, null, null, null, null,
						null, null, null, null);
				solveur = new CplexEnergieBinaireRelaxe(data);
			}
			System.out.println("début des calculs");
			solveur.lancer();
			System.out.println("fin des calculs");
			fenetre.setSolution(solveur.getSolution());
			fenetre.setEtat(MainFenetre.State.RESULTAT_CALCULE);
		}

		fenetre.updateVisibility();

	}

	public Solveur getSolveur() {
		return solveur;
	}

	public void setSolveur(Solveur solveur) {
		this.solveur = solveur;
	}

}
