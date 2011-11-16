package manager.solveurs.RS;

import data.solution.Solution;
import manager.solveurs.Solveur;

/**
 * Implémentation générale du recuit simulé.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public abstract class RecuitSimule implements Solveur
{
	/** La solution courante du recuit simulé. */
	protected Solution solutionCourante;
	/** La meilleure solution trouvée pour le moment. */
	protected Solution meilleureSolution;
	/** Le facteur de décroissance de la température du recuit. */
	protected double facteurDecroissance;
	/** La température du recuit. */
	protected double temperature;
	/** La valeur de la meilleure solution trouvée. */
	protected double meilleureF;
	/** Le taux d'acceptation de solutions coûteuses acceptées par le recuit à la température initiale. */
	protected double tauxAcceptation;
	
	/**
	 * Construit un recuit simulé.
	 * @param facteurDecroissance le facteur de décroissance de la température.
	 */
	protected RecuitSimule(double facteurDecroissance, double tauxAcceptation)
	{
		this.facteurDecroissance = facteurDecroissance;
		this.tauxAcceptation = tauxAcceptation;
	}
	
	/**
	 * Démarre le recuit simulé.
	 * Implémente le coeur de l'algorithme du recuit simulé commun à tous les problèmes.
	 */
	public void lancer()
	{
		Solution solutionVoisine;
		double delta;
		
		// Initilaisation du recuit
		solutionCourante.solutionInitiale();
		initialiserTemperature();
		meilleureF = solutionCourante.fonctionObjectif();
		meilleureSolution = solutionCourante.clone();
		
		// Les paliers de température
		while(testerCondition1())
		{
			// Les itérations par palier
			while(testerCondition2())
			{
				// On cherche une solution dans le voisinage
				solutionVoisine = voisin();
				delta = solutionCourante.deltaF(solutionVoisine);
				
				// Si la solution du voisinage est meilleure que la solution courante
				if(delta <= 0)
				{
					// La solution voisine devient la solution courante
					solutionCourante = solutionVoisine.clone();
					
					// Si la solution voisine est meilleure que la meilleure trouvée pour le moment
					// Elle devient la meilleure solution
					if(solutionCourante.fonctionObjectif() < meilleureF)
					{
						meilleureF = solutionCourante.fonctionObjectif();
						meilleureSolution = solutionCourante.clone();
					}
				}
				// Si la solution voisine n'améliore pas la solution courante
				// Elle peut être accepté
				else
				{
					double p = Math.random();
					if(p <= Math.exp(-delta/temperature))
					{
						solutionCourante = solutionVoisine.clone();
					}
				}
			}
			decroitreTemperature();
		}
	}
	
	/**
	 * Initialise la température du recuit.
	 * La température initiale doit accepter un certain nombre de solutions coûteuses.
	 * Ce taux est fixé par l'utilisateur.
	 */
	private void initialiserTemperature()
	{
		Solution solutionVoisine;
		double taux = 0;
		temperature = 1000;
		
		// Tant que le taux d'acceptation n'est pas celui voulu
		// On multiplie la température par 2
		do
		{
			int nbCouteuses = 0;
			int nbCouteusesAcceptees = 0;
			
			// On fait 40 tirages de solutions voisines
			for(int i=0; i<40; i++)
			{
				solutionVoisine = voisin();
				
				// Si on a une solution coûteuse, on regarde si elle est acceptée
				if(solutionCourante.deltaF(solutionVoisine) > 0)
				{
					double p = Math.random();
					nbCouteuses++;
					
					if(p <= Math.exp(-solutionCourante.deltaF(solutionVoisine)/temperature))
					{
						nbCouteusesAcceptees++;
					}
				}
			}
			
			taux = (double) nbCouteusesAcceptees / (double) nbCouteuses;
			if(taux < tauxAcceptation)
			{
				temperature *= 2;
			}
		} while(taux < tauxAcceptation);
	}
	
	/**
	 * Décroit la température du recuit.
	 * La fonction utilisée est : f(t) = alpha x t avec alpha fixée par l'utilisateur.
	 */
	private void decroitreTemperature()
	{
		temperature *= facteurDecroissance;
	}
	
	/**
	 * Sélectionne une solution dans le voisinage de la solution courante.
	 * @return une solution voisine de la solution courante.
	 */
	protected abstract Solution voisin();
	
	/**
	 * Teste si le recuit est arrivé à sa température minimale.
	 * @return true si le recuit peut passer au palier de température suivant, false sinon.
	 */
	protected abstract boolean testerCondition1();
	
	/**
	 * Teste si le recuit doit continuer à un palier de température.
	 * @return true si le recuit doit continuer à ce palier de température, false sinon.
	 */
	protected abstract boolean testerCondition2();
	
	public Solution getSolution()
	{
		return meilleureSolution;
	}
}
