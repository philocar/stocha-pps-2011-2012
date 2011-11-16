package data.solution;

import data.Data;

/**
 * Une solution du problème de management de la production d'énergie.
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class SolutionEnergieBinaire extends Solution {

	/** Le vecteur de décision */
	boolean[] y;
	/** Le vecteur d'activation des scénarios */
	boolean[] z;
	
	/**
	 * Crée une nouvelle solution spécifique au problème de management de la production d'énergie en fonction des données
	 * @param data
	 */
	public SolutionEnergieBinaire(Data data)
	{
		
	}
	
	@Override
	public Solution clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double deltaF(Solution solution) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double fonctionObjectif() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void solutionInitiale() {
		// TODO Auto-generated method stub
		
	}
}
