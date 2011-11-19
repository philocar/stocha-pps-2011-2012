package data.solution;

/**
 * Classe contenant la solution d'un problème
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 */
public abstract class Solution {

	/**
	 * Initialise la solution.
	 */
	public abstract void solutionInitiale();
	
	/**
	 * Retourne la différence entre deux solutions.
	 * Une valeur négative si le paramètre est meilleur que la solution courante.
	 * 
	 * @param solution
	 *            la solution à comparer à la solution courante.
	 * @return la différence entre la solution courante et la solution en
	 *         argument : solution - solution courante
	 */
	public abstract double deltaF(Solution solution);

	/**
	 * Retourne la valeur de la fonction objectif du problème.
	 * 
	 * @return retourne la valeur de la solution avec la fonction objectif du
	 *         problème.
	 */
	public abstract double fonctionObjectif();
	
	/**
	 * Crée une copie de la solution courante.
	 * @return une copie de la solution courante
	 */
	public abstract Solution clone();
}
