package manager.solveurs.PL;

import manager.solveurs.Solveur;

import data.DataBinaire;
import data.DataRecours;
import data.solution.SolutionEnergieRecours;

/**
 * Cette classe permet d'écrire un programme linéaire à partir de données pour le problème de management de la production d'énergie.
 * Ce PL est utilisé par un solveur de programmes linéaires.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 */
public abstract class PLEnergieRecours implements Solveur {

	/** Variable contenant les données du problème */
	protected DataRecours donnees;
	/** La solution */
	protected SolutionEnergieRecours solution;
	/** Pour chaque scénario il y a un vecteur, toujours le même, pour la partie droite des contraintes sur les productions */
	protected double[] vecteurContraintesMembreDroit;
	/** Pour chaque scénario il y a une matrice, toujours la même, pour la partie gauche des contraintes sur les productions */
	protected int[][] matriceContraintesMembreGauche;
	
	/**
	 * Crée un nouveau PLEnergie.
	 * @param donnees les données du problème
	 */
	public PLEnergieRecours(DataBinaire donnees)
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
	public SolutionEnergieRecours getSolution() {
		return null;
	}
	
	public abstract void lancer();
}
