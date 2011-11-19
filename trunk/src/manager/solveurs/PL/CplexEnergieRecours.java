package manager.solveurs.PL;

import data.DataRecours;

/**
 * Cette classe permet de résoudre une instance du problème de management de la production d'énergie à l'aide de CPLEX
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class CplexEnergieRecours extends PLEnergieRecours 
{
	/**
	 * Crée un nouveau CPlexEnergie
	 * 
	 * @param donnees les données du problème
	 */
	public CplexEnergieRecours(DataRecours donnees)
	{
		super(donnees);
	}

	/**
	 * Lance la résolution du programme linéaire.
	 * Crée le programme à partir des tableaux de coefficients.
	 */
	public void lancer()
	{
		// min couts * x + probabilitesPrix * y
		// une contrainte par scénario et par période pour l'offre et la demande
	}
}
