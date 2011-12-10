package manager.solveurs.RS;

import java.util.Random;

import data.DataBinaire;
import data.solution.Solution;
import data.solution.SolutionEnergieBinaire;

/**
 * Implémentation spécialisée du recuit simulé pour le problème de management de la production d'énergie.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class RSEnergie extends RecuitSimule
{
	/** Les données du problème */
	private DataBinaire donnees;
	/** Le nombre d'itérations par palier de température */
	private int nbIterationsParPalier;
	/** Le nombre d'itérations courant pour le palier de température courant.*/
	private int iterationCourante;
	/** La température à atteindre pour arrêter le recuit. */
	private double temperatureFinale;
	/** Le nombre de modifications pour lesquelles on garde le même scénarios */
	private int nbTransformationsParScenarios;
	/** Le nombre de transformations imposibles avant de changer de scénarios */
	private int nbTestsTransformations;
	/** Le nombre courant de modifications pour lesquelles on garde le même scénarios */
	private int nbTransformationsParScenariosCourant;
	
	/**
	 * Construit un recuit simulé spécialisé pour le problème de la p-médiane.
	 * @param facteurDecroissance le facteur de décroissance de la température du recuit.
	 * @param temperatureFinale la température à atteindre pour arrêter le recuit.
	 */
	public RSEnergie(double facteurDecroissance, DataBinaire donnees, double temperatureFinale, int nbIterationsParPalier, double tauxAcceptation, int nbTransformationsParScenarios, int nbTestsTransformations)
	{
		super(facteurDecroissance, tauxAcceptation);
		solutionCourante = new SolutionEnergieBinaire(donnees);
		meilleureSolution = new SolutionEnergieBinaire(donnees);
		this.temperatureFinale = temperatureFinale;
		this.donnees = donnees;
		this.nbIterationsParPalier = nbIterationsParPalier;
		this.nbTestsTransformations = nbTestsTransformations;
		this.nbTransformationsParScenarios = nbTransformationsParScenarios;
		iterationCourante = 0;
		nbTransformationsParScenariosCourant = 0;
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
	 * Sélectionne une solution dans le voisinage de la solution courante en modifiant l'activation d'un scénario et une décision.
	 * Un scénario actif devient inactif et inversement. Il faut que la nouvelle configuration ait une probabilité supérieure à celle demandée.
	 * Une décision passe de 0 à 1 ou de 1 à 0. Il faut que la nouvelle configuration respecte l'offre et la demande et les contraintes d'unicitées.
	 * @return une solution voisine de la solution courante.
	 */
	protected Solution voisin()
	{
		SolutionEnergieBinaire solution = (SolutionEnergieBinaire) solutionCourante.clone();
		
		Random rand = new Random();
		int nbTests;
		nbTransformationsParScenariosCourant++;
		
		do
		{
			// Active ou desactive un scénario si c'est le moment
			if(nbTransformationsParScenariosCourant >= nbTransformationsParScenarios)
			{
				nbTransformationsParScenariosCourant = 0;
				int indiceScenarioChange;
				do
				{
					indiceScenarioChange = rand.nextInt(solution.getZ().length);
					solution.active(indiceScenarioChange, !solution.isActived(indiceScenarioChange));
				} while(solution.probabiliteScenario() < donnees.getProbabilite());
			}
			
			// Modifie les décisions
			nbTests = 0;
			do
			{
				nbTests++;
				
				// On modifie le palier d'une centrale pour une période
				if(rand.nextInt() % 5 != 0)
				{
					int periodeChange;
					int centraleChange;
					int palierChange;
					periodeChange = rand.nextInt(donnees.nbPeriodes);
					centraleChange = rand.nextInt(donnees.nbCentrales);
					
					do
					{
						palierChange = rand.nextInt(donnees.nbPaliers[centraleChange]);
					} while(palierChange == solution.getDecisionPeriodeCentrale(periodeChange, centraleChange));
					
					solution.setDecisionPeriodeCentrale(periodeChange, centraleChange, palierChange);
				}
				// Si la trajectoire hydrolique est modifiée
				else
				{					
					int trajectoireChange;
					do
					{
						trajectoireChange = rand.nextInt(donnees.nbTrajectoires);
					} while(trajectoireChange == solution.getTrajectoire());
					solution.setTrajectoire(trajectoireChange);
				}
			} while(nbTests < nbTestsTransformations);
			
		} while(!solution.respecteContrainteDemande() && nbTests == nbTestsTransformations);
		
		return solution;
	}
}
