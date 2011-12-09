package data.solution;

import data.DataBinaire;

/**
 * Une solution du problème de management de la production d'énergie en binaire mais aussi pour sa relaxation.
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class SolutionEnergieBinaire extends Solution {

	/** Le vecteur de décision */
	private double[] y;
	/** Le vecteur d'activation des scénarios */
	private double[] z;
	/** Les données du problème */
	private DataBinaire données;
	
	/**
	 * Crée une nouvelle solution spécifique au problème de management de la production d'énergie sous sa forme binaire et sa relaxation
	 * @param donnees les donnees du problème
	 */
	public SolutionEnergieBinaire(DataBinaire donnees)
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
	
	public double[] getZ()
	{
		return z;
	}
	
	public double[] getY()
	{
		return y;
	}
}
