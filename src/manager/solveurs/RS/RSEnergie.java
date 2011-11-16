package manager.solveurs.RS;

import java.util.ArrayList;
import java.util.Random;

import data.Data;
import data.solution.Solution;
import data.solution.SolutionEnergieBinaire;

/**
 * Implémentation spécialisée du recuit simulé pour le problème de management de la production d'énergie.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class RSEnergie extends RecuitSimule
{
	/** Les données du problème */
	private Data donnees;
	/** Le nombre d'itérations par palier de température */
	private int nbIterationsParPalier;
	/** Le nombre d'itérations courant pour le palier de température courant.*/
	private int iterationCourante;
	/** La température à atteindre pour arrêter le recuit. */
	private double temperatureFinale;
	
	/**
	 * Construit un recuit simulé spécialisé pour le problème de la p-médiane.
	 * @param facteurDecroissance le facteur de décroissance de la température du recuit.
	 * @param temperatureFinale la température à atteindre pour arrêter le recuit.
	 */
	public RSEnergie(double facteurDecroissance, Data donnees, double temperatureFinale, int nbIterationsParPalier, double tauxAcceptation)
	{
		super(facteurDecroissance, tauxAcceptation);
		solutionCourante = new SolutionEnergieBinaire(donnees);
		meilleureSolution = new SolutionEnergieBinaire(donnees);
		this.temperatureFinale = temperatureFinale;
		this.donnees = donnees;
		this.nbIterationsParPalier = nbIterationsParPalier;
		iterationCourante = 0;
	}

	/**
	 * Teste si le recuit est arrivé la température finale demandée à la création.
	 * @return true si le recuit peut passer au palier de température suivant, false sinon.
	 */
	protected boolean testerCondition1()
	{
		if(temperature > temperatureFinale)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Teste si le recuit a encore des itérations à faire pour un palier de température.
	 * @return true si le recuit doit continuer à ce palier de température, false sinon.
	 */
	protected boolean testerCondition2()
	{
		iterationCourante++;
		
		if(iterationCourante < nbIterationsParPalier)
		{
			return true;
		}
		else
		{
			iterationCourante = 0;
			return false;
		}
	}

	/**
	 * Sélectionne une solution dans le voisinage de la solution courante en modifiant un centre.
	 * @return une solution voisine de la solution courante.
	 */
	protected Solution voisin()
	{
		SolutionEnergieBinaire solution = new SolutionEnergieBinaire(donnees);
		
		
		return solution;
	}
}
