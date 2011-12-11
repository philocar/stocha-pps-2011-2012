package data.solution;

import data.DataRecours;

/**
 * Une solution du problème de management de la production d'énergie sous forme de recours avec scénarios.
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class SolutionEnergieRecours extends Solution implements SolutionCentrale{

	/** Le vecteur de productions */
	private double[][] x;
	/** La matrice contenant l'énergie achetée. Pour chaque période et chaque scénario il y a de l'énergie achetée */
	private double[][] yAchat;
	/** La matrice contenant l'énergie vendue. Pour chaque période et chaque scénario il y a de l'énergie vendue */
	private double[][] yVente;
	/** Les données du problèmes */
	private DataRecours donnees;
	
	/**
	 * Crée une nouvelle solution spécifique au problème de management de la production d'énergie avec recours
	 */
	public SolutionEnergieRecours(DataRecours donnees)
	{
		this.donnees = donnees;
		x = new double[donnees.nbPeriodes][donnees.nbCentralesThermiques+1];
		yAchat = new double[donnees.nbPeriodes][donnees.nbScenarios];
		yVente = new double[donnees.nbPeriodes][donnees.nbScenarios];
	}
	
	@Override
	public Solution clone() {
		SolutionEnergieRecours res = new SolutionEnergieRecours(donnees);

		for (int i = 0; i < x.length; i++)
			for(int j=0; j<x[i].length; j++)
				res.x[i][j] = x[i][j];

		for (int i = 0; i < yAchat.length; i++)
			for(int j=0; j<yAchat[i].length; j++)
				res.yAchat[i][j] = yAchat[i][j];

		for (int i = 0; i < yVente.length; i++)
			for(int j=0; j<yVente[i].length; j++)
				res.yVente[i][j] = yVente[i][j];
	
		return res;
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

	/**
	 * Retourne le vecteur de productions.
	 */
	public double[][] getX() {
		return x;
	}
	
	/**
	 * Retourne la production pour la période et la centrale données.
	 */
	public double getX(int periode, int centrale) {
		return x[periode][centrale];
	}

	/**
	 * Modifie la production pour la période et la centrale données.
	 */
	public void setX(int periode, int centrale, double valeur) {
		x[periode][centrale] = valeur;
	}

	/**
	 * Retourne la matrice de l'énergie achetée.
	 */
	public double[][] getyAchat() {
		return yAchat;
	}
	
	/**
	 * Retourne la valeur de l'énergie achetée pour la période et le scénraio donnés.
	 */
	public double getyAchat(int periode, int scenario) {
		return yAchat[periode][scenario];
	}

	/**
	 * Modifie la valeur de l'énergie achetée pour la période et le scénraio donnés.
	 */
	public void setyAchat(int periode, int scenario, double valeur) {
		yAchat[periode][scenario] = valeur;
	}

	/**
	 * Retourne la matrice de l'énergie vendue.
	 */
	public double[][] getyVente() {
		return yVente;
	}
	
	/**
	 * Retourne la valeur de l'énergie vendue pour la période et le scénraio donnés.
	 */
	public double getyVente(int periode, int scenario) {
		return yVente[periode][scenario];
	}

	/**
	 * Modifie la valeur de l'énergie vendue pour la période et le scénraio donnés.
	 */
	public void setyVente(int periode, int scenario, double valeur) {
		yVente[periode][scenario] = valeur;
	}
	
	/**
	 * Retourne l'affichage de la solution.
	 */
	public String toString(){
		String string = "";
		for(int i = 0; i < donnees.nbPeriodes*(donnees.nbCentralesThermiques+1); i++)
		{
			string += "X"+((i/(donnees.nbCentralesThermiques+1))+1)+((i%(donnees.nbCentralesThermiques+1))+1)+" = "+getX(i/(donnees.nbCentralesThermiques+1), i%(donnees.nbCentralesThermiques+1))+"\n";
		}
		for(int i = 0; i < donnees.nbPeriodes*donnees.nbScenarios; i++)
		{
			string += "YP"+((i/donnees.nbScenarios)+1)+((i%donnees.nbScenarios)+1)+" = "+getyAchat(i/donnees.nbScenarios, i%donnees.nbScenarios)+"\n";
		}
		for(int i = 0; i < donnees.nbPeriodes*donnees.nbScenarios; i++)
		{
			string += "YM"+((i/donnees.nbScenarios)+1)+((i%donnees.nbScenarios)+1)+" = "+getyVente(i/donnees.nbScenarios, i%donnees.nbScenarios)+"\n";
		}
		return string;
	}

	@Override
	public SolutionEnergie genererSolutionEnergie() {
		SolutionEnergie e = new SolutionEnergie();
		e.setEnergies(x);
		e.setEnergiesMax(donnees.getProductionsMax());
		
		return e;
	}
}
