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
		this.donnees = donnees;
		solution = new SolutionEnergieRecours(donnees);
		genererPL();
	}

	/**
	 * Génère le programme linéaire à partir des données.
	 * Remplit les tableaux du problème.
	 */
	private void genererPL()
	{
		facteursDisponibilite = new double[100][7][5];
		for(int s = 0; s < 100; s++)
		{
			for(int p = 0; p < 7; p++)
			{
				for(int c = 0; c < 4; c++)
				{
					facteursDisponibilite[s][p][c] = donnees.getScenario(s).getPaliersPeriodeCentrale(p, c);
				}
			}
		}
		couts = new double[7*5];
		
		for(int p = 0; p < 7; p++)
		{
			for(int c = 0; c < 5; c++)
			{
				couts[p*5 + c] = donnees.getCoutCentrale(c);
			}
		}
		
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
