package manager.solveurs.PL;

import manager.solveurs.Solveur;

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
	/** Le vecteur contenant les prix par période multiplié par la probabilité de chaque scénarios */
	protected double[] probabilitesPrix;
	/** Le vecteur de coûts par période et par centrale */
	protected double[] couts;
	/** La matrice qui pour chaque scénario et chaque période associe la liste des facteurs de disponibilité */
	protected double[][][] facteursDisponibilite;
	
	/**
	 * Crée un nouveau PLEnergie.
	 * @param donnees les données du problème
	 */
	public PLEnergieRecours(DataRecours donnees)
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
