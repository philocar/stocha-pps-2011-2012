package data.solution;

import data.DataBinaire;

/**
 * Une solution du problème de management de la production d'énergie en binaire mais aussi pour sa relaxation.
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class SolutionEnergieBinaire extends Solution {

	/** La matrice de décision pour chaque periode, une centrale choisie un palier de production */
	private int[][] y;
	/** Le vecteur d'activation des scénarios */
	private boolean[] z;
	/** La trajectoires hydrauliques choisie */
	private int trajectoire;
	/** Les données du problèmes */
	private DataBinaire donnees;
	
	/**
	 * Crée une nouvelle solution spécifique au problème de management de la production d'énergie sous sa forme binaire et sa relaxation
	 * @param nbScenarios le nombre de scénarios
	 * @param nbCentralesThermiques le nombre de centrales
	 * @param nbPeriodes le nombre de périodes
	 * @param nbTrajectoires le nombre de trajectoires
	 */
	public SolutionEnergieBinaire(DataBinaire donnees)
	{
		this.donnees = donnees;
		y = new int[donnees.nbPeriodes][donnees.nbCentrales];
		z = new boolean[donnees.nbScenarios];
	}
	
	@Override
	public Solution clone() {
		SolutionEnergieBinaire res = new SolutionEnergieBinaire(donnees);
		
		for(int i=0; i<z.length; i++)
			res.getZ()[i] = z[i];
		for(int i=0; i<y.length; i++)
		{
			for(int j=0;j<y[i].length; j++)
				res.setDecisionPeriodeCentrale(i, j, y[i][j]);
		}
		res.setTrajectoire(trajectoire);
		return res;
	}

	@Override
	public double deltaF(Solution solution) {
		
		return solution.fonctionObjectif() - this.fonctionObjectif();
	}

	@Override
	public double fonctionObjectif() {
		double val = 0;
		for(int p=0; p<donnees.nbPeriodes; p++)
		{
			for(int c=0; c<donnees.nbCentrales; c++)
				val += donnees.getPalier(c, y[p][c]) * donnees.getCoutCentrale(c);
		
			val += (donnees.getTrajectoire(trajectoire) / donnees.getTurbinage()) * donnees.getCoutCentrale(4);
		}
		return val;
	}

	@Override
	public void solutionInitiale() {
		
	}
	
	public boolean[] getZ()
	{
		return z;
	}
	
	public int[][] getY()
	{
		return y;
	}
	
	public boolean isActived(int s)
	{
		return z[s];
	}
	
	public int getDecisionPeriodeCentrale(int periode, int centrale)
	{
		return y[periode][centrale];
	}

	public int getTrajectoire() 
	{
		return trajectoire;
	}
	
	public void setTrajectoire(int trajectoire) 
	{
		this.trajectoire = trajectoire;
	}

	public void active(int indiceScenario, boolean activation) {
		z[indiceScenario] = activation;
	}

	public void setDecisionPeriodeCentrale(int periode, int centrale, int palier) {
		y[periode][centrale] = palier;
	}
}
