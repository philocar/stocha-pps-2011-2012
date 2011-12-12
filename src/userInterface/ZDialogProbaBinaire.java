package userInterface;

import manager.solveurs.PL.CplexEnergieBinaire;
import manager.solveurs.PL.CplexEnergieBinaireRelaxe;
import data.DataBinaire;

/**
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 *         Fenêtre de dialogue demandant à l'utilisateur d'entrer les paramètres
 *         à utiliser pour le traitement par RS
 */
public class ZDialogProbaBinaire {

	private DataBinaire data;

	/**
	 * Constructeur
	 * 
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public ZDialogProbaBinaire(MainFenetre parent, String fileName) {

		data = new DataBinaire(fileName + "Données_Recuit_demandes.csv",
				fileName + "Données_Recuit_paliers1.csv", fileName
						+ "Données_Recuit_paliers2.csv", fileName
						+ "Données_Recuit_paliers3.csv", fileName
						+ "Données_Recuit_paliers4.csv", fileName
						+ "Données_Recuit_trajectoire_hydro.csv", fileName
						+ "Données_Recours_parametres_hydraulique.csv",
				fileName + "Données_Recours_capacite_max.csv");
		parent.setEtat(MainFenetre.State.FICHIER_CHOISI);
		parent.updateVisibility();
	}

	public CplexEnergieBinaireRelaxe getSolveurRelaxe() {
		return new CplexEnergieBinaireRelaxe(data);
	}

	public CplexEnergieBinaire getSolveurBinaire() {
		return new CplexEnergieBinaire(data);
	}

}
