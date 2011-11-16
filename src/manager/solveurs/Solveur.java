package manager.solveurs;

import data.solution.Solution;


/**
 * Interface correspondant à un algorithme de résolution.
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 */
public interface  Solveur {

	/**
	 * Lance la résolution du problème
	 */
	public void lancer();

	/**
	 * Retourne la solution
	 * @return la solution trouvée
	 */
	public Solution getSolution();
}
