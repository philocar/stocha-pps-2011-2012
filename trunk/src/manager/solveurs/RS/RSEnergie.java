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
	 * @param donnees les données du problème
	 * @param facteurDecroissance le facteur de décroissance de la température du recuit.
	 * @param temperatureFinale la température à atteindre pour arrêter le recuit.
	 * @param nbIterationsParPalier le nombre d'itérations par palier
	 * @param tauxAcceptation le taux d'acceptation de solutions coûteuses acceptées par le recuit à la température initiale
	 * @param nbTransformationsParScenarios le nombre de modifications pour lesquelles on garde le même scénarios
	 * @param nbTestsTransformations le nombre de transformations imposibles avant de changer de scénarios
	 */
	public RSEnergie(DataBinaire donnees, double facteurDecroissance, double temperatureFinale, int nbIterationsParPalier, double tauxAcceptation, int nbTransformationsParScenarios, int nbTestsTransformations)
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
		// La solution voisine est dans un premier temps une copie de la solution courante
		SolutionEnergieBinaire solution = (SolutionEnergieBinaire) solutionCourante.clone();
		
		Random rand = new Random();
		int nbTests; // Le nombre de test de solution pour un jeu de scénarios
		boolean recherche;
		nbTransformationsParScenariosCourant++;
		
		do
		{
			// Active ou desactive un scénario si c'est le moment
			if(nbTransformationsParScenariosCourant >= nbTransformationsParScenarios)
			{
				nbTransformationsParScenariosCourant = 0;
				int indiceScenarioChange;
				// On essaie des modifications tant que ça ne répond pas à la probabilité voulue
				do
				{
					recherche = false;
					indiceScenarioChange = rand.nextInt(solution.getZ().length);
					solution.active(indiceScenarioChange, !solution.isActived(indiceScenarioChange));
					
					// Repasse dans l'état initial si ça ne répond pas à la probabilité voulue
					if(solution.probabiliteScenario() < donnees.getProbabilite())
					{
						solution.active(indiceScenarioChange, !solution.isActived(indiceScenarioChange));
						recherche = true;
					}
					
				} while(recherche);
			}
			
			// Essaie de modifier les décisions tant que ça ne répond pas à la demande
			nbTests = 0;
			do
			{
				recherche = false;
				nbTests++;
				
				// On modifie le palier d'une centrale pour une période
				// Il faut choisir entre modifier le choix d'un palier thermique ou bien la trajectoire
				// Cela se décide en fonction du nombre de paliers et du nombre de trajectoires
				double proba = (donnees.nbCentrales * donnees.nbPeriodes) / (double) (donnees.nbCentrales * donnees.nbPeriodes + donnees.nbTrajectoires);
				
				// On modifie un choix de palier
				if(rand.nextDouble() < proba)
				{
					int periodeChange;
					int centraleChange;
					int palierChange;
					// Choix de la période à modifier
					periodeChange = rand.nextInt(donnees.nbPeriodes);
					// choix de la centrale thermique à modifier
					centraleChange = rand.nextInt(donnees.nbCentrales);
					// L'ancienne valeur est sauvegardée
					int ancienPalier = solution.getDecisionPeriodeCentrale(periodeChange, centraleChange);
					// On tire le niveau choix de palier, il doit être différent de l'ancien
					do
					{
						palierChange = rand.nextInt(donnees.nbPaliers[centraleChange]);
					} while(palierChange == ancienPalier);
					
					// On modifie la solution
					solution.setDecisionPeriodeCentrale(periodeChange, centraleChange, palierChange);
					
					// Si la solution ne repond pas à la demande, on repasse dans l'état initial et on refait une modification
					if(!solution.respecteContrainteDemandePeriode(periodeChange))
					{
						recherche = true;
						solution.setDecisionPeriodeCentrale(periodeChange, centraleChange, ancienPalier);
					}
				}
				// On modifie la trajectoire hydraulique
				else
				{			
					int trajectoireChange;
					// On sauvegarde l'ancienne trajectoire
					int ancienneTrajectoire = solution.getTrajectoire();
					// On tire une trajectoire, elle doit être différente de l'ancienne
					do
					{
						trajectoireChange = rand.nextInt(donnees.nbTrajectoires);
					} while(trajectoireChange == solution.getTrajectoire());
					
					// Modifie la trajectoire
					solution.setTrajectoire(trajectoireChange);
					
					// Si ça ne répond pas à la demande, on repasse dans l'état initial et on refait une modification
					if(!solution.respecteContrainteDemande())
					{
						recherche = true;
						solution.setTrajectoire(ancienneTrajectoire);
					}
				}
			} while(recherche && nbTests < nbTestsTransformations);
			// On refait une modification si ça ne répond pas à la demande et que le nombre maximum de modifications par modification de scénarios n'est pas atteint
		} while(recherche && nbTests == nbTestsTransformations); // On modifie les scenarios si aucune solution n'a été trouvée
		
		return solution;
	}
	
	public static void main(String[] args)
	{
		DataBinaire data = new DataBinaire(0.98, "Data/Données_Recuit_demandes.csv", "Data/Données_Recuit_paliers1.csv", "Data/Données_Recuit_paliers2.csv", "Data/Données_Recuit_paliers3.csv", "Data/Données_Recuit_paliers4.csv", "Data/Données_Recuit_trajectoire_hydro.csv", "Data/Données_Recuit_parametres_hydro.csv", "Data/Données_Recuit_capacité.csv");
		RSEnergie rs = new RSEnergie(data, 0.9, 0.01, 16384, 0.8, 10, 100);
		rs.lancer();
		SolutionEnergieBinaire solution = (SolutionEnergieBinaire) rs.getSolution();
		System.out.println(solution);
	}
}
