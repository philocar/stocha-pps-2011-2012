package manager.solveurs.PL;

import manager.solveurs.Solveur;

import data.DataBinaire;
import data.solution.SolutionEnergieBinaire;

/**
 * Cette classe permet d'écrire un programme linéaire à partir de données pour le problème de management de la production d'énergie.
 * Ce PL est utilisé par un solveur de programmes linéaires.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 */
public abstract class PLEnergieBinaireRelaxe implements Solveur {

	/** Variable contenant les données du problème */
	protected DataBinaire donnees;
	/** Le vecteur de solution du problème */
	protected SolutionEnergieBinaire solution;
	/** Le vecteur de coûts */
	protected double[] couts;
	/** La matrice pour l'unicité du palier de production thermique par centrale */
	protected int[][] h;
	/** Le vecteur pour l'unicité de la trajectoire d'utilisation de l'eau */
	protected int[] u;
	/** Les probabilités des scénarios */
	protected double[] probabilites;
	/** La matrice qui pour chaque scénario et chaque période associe la liste des paliers de production et les trajectoires */
	protected double[][][] productions;
	
	/**
	 * Crée un nouveau PLEnergie.
	 * @param donnees les données du problème
	 */
	public PLEnergieBinaireRelaxe(DataBinaire donnees)
	{
		genererPL();
	}

	/**
	 * Génère le programme linéaire de la p-mediane à partir des données.
	 * Remplit les tableaux du problème.
	 */
	private void genererPL()
	{
		
	}
	
	/**
	 * Renvoie la solution calculée.
	 * @return la solution calculée
	 */
	public SolutionEnergieBinaire getSolution() {
		return solution;
	}
	
	public abstract void lancer();
}
