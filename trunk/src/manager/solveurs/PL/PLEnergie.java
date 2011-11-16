package manager.solveurs.PL;

import manager.solveurs.Solveur;

import data.Data;
import data.solution.SolutionEnergieBinaire;

/**
 * Cette classe permet d'écrire un programme linéaire à partir de données pour le problème de management de la production d'énergie.
 * Ce PL est utilisé par un solveur de programmes linéaires.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 */
public abstract class PLEnergie implements Solveur {

	/** Variable contenant les données du problème */
	protected Data donnees;

	/** La solution du problème. */
	protected SolutionEnergieBinaire solution;
	
	/** Le tableau contenant les coefficients de la fonction objectif. */
	protected double[] coefFonctionObjectif;
	
	/**
	 * Crée un nouveau PLEnergie.
	 * @param donnees les données du problème
	 */
	public PLEnergie(Data donnees)
	{
		this.donnees = donnees;
		solution = new SolutionEnergieBinaire(donnees);
		genererPL();
	}

	/**
	 * Génère le programme linéaire de la p-mediane à partir des données.
	 * Remplit les tableaux du problème.
	 */
	private void genererPL()
	{
		// Remplit le tableau coefFonctionObjectif
		// A chaque paire i j on associe la distance les séparant
		coefFonctionObjectif = new double[donnees.getNbEntites()*donnees.getNbEntites()];
		for(int i=0; i<donnees.getNbEntites(); i++)
		{
			for(int j=0; j<donnees.getNbEntites(); j++)
			{
				coefFonctionObjectif[i*donnees.getNbEntites()+j] = donnees.getDistance(i, j);
			}
		}
		
		// Remplit le tableau coefNbCentres
		// A chaque paire j j on met un poid de 1 pour les activer dans la contrainte
		coefNbCentres = new int[donnees.getNbEntites()*donnees.getNbEntites()];
		for(int i=0; i<donnees.getNbEntites(); i++)
		{
			for(int j=0; j<donnees.getNbEntites(); j++)
			{
				if(i == j)
				{
					coefNbCentres[i*donnees.getNbEntites()+j] = 1;
				}
				else
				{
					coefNbCentres[i*donnees.getNbEntites()+j] = 0;
				}
			}
		}
		
		// Remplit le tableau coefEntiteRelieeUneEntite
		// Pour chaque contrainte on a la plage de variables à prendre en compte (pour éviter le débordement de mémoire)
		coefEntiteRelieeUneEntite = new int[donnees.getNbEntites()];
		for(int l=0; l<donnees.getNbEntites(); l++)
		{
			coefEntiteRelieeUneEntite[l] = (l + 1) * donnees.getNbEntites() - 1;
		}
		
		// Remplit le tableau coefGaucheEntiteRelieeUnCentre et coefDroiteEntiteRelieeUnCentre
		coefDroiteEntiteRelieeUnCentre = new int[donnees.getNbEntites()*donnees.getNbEntites()];
		coefGaucheEntiteRelieeUnCentre = new int[donnees.getNbEntites()*donnees.getNbEntites()];
		for(int i=0; i<donnees.getNbEntites(); i++)
		{
			for(int j=0; j<donnees.getNbEntites(); j++)
			{
				coefDroiteEntiteRelieeUnCentre[i*donnees.getNbEntites()+j] = j*donnees.getNbEntites()+j;
				coefGaucheEntiteRelieeUnCentre[i*donnees.getNbEntites()+j] = i*donnees.getNbEntites()+j;
			}
		}
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
