package data.solution;

/**
 * Une solution du problème de management de la production d'énergie sous forme de recours avec scénarios.
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class SolutionEnergieRecours extends Solution {

	/** Le vecteur de productions */
	private double[] x;
	/** La matrice contenant l'énergie achetée. Pour chaque période et chaque scénario il y a de l'énergie achetée */
	private double[][] yAchat;
	/** La matrice contenant l'énergie vendue. Pour chaque période et chaque scénario il y a de l'énergie vendue */
	private double[][] yVente;
	
	/**
	 * Crée une nouvelle solution spécifique au problème de management de la production d'énergie avec recours
	 */
	public SolutionEnergieRecours()
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
