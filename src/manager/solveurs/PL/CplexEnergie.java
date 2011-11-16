package manager.solveurs.PL;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.cplex.IloCplex;
import data.Data;

/**
 * Cette classe permet de résoudre une instance du problème de management de la production d'énergie à l'aide de CPLEX
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class CplexEnergie extends PLEnergie 
{
	/**
	 * Crée un nouveau CPlexEnergie
	 * 
	 * @param donnees les données du problème
	 */
	public CplexEnergie(Data donnees)
	{
		super(donnees);
	}

	/**
	 * Lance la résolution du programme linéaire.
	 * Crée le programme à partir des tableaux de coefficients.
	 */
	public void lancer()
	{
		
	}
}
