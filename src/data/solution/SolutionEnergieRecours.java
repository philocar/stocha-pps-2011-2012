package data.solution;

import data.DataRecours;

/**
 * Une solution du problème de management de la production d'énergie sous forme de recours avec scénarios.
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class SolutionEnergieRecours extends Solution implements SolutionCentrale{

	/** La matrice de productions */
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
		double val = 0;
		
		for(int p=0; p<donnees.nbPeriodes; p++)
		{
			// Calcule le cout de production
			for (int c = 0; c < donnees.nbCentralesThermiques+1; c++) {
				val += x[p][c] * donnees.getCoutCentrale(c);
			}

			// Calcule les gains des apports en eau
			val -= donnees.getApportsPeriode(p) * donnees.getCoutCentrale(donnees.nbCentralesThermiques);
			
			// Calcule les pertes d'achat et vente
			for(int s=0; s<donnees.nbScenarios; s++)
			{
				val += (yAchat[p][s] * 100 + yVente[p][s] * 200) * donnees.getScenario(s).getProbabilite();
			}
		}
		return val;
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
		string += "Fonction Objectif = " + fonctionObjectif() + "\n";
		for(int p = 0; p < donnees.nbPeriodes; p++)
		{
			string += "période "+(p+1)+"\n";
			for(int c = 0; c < donnees.nbCentralesThermiques; c++)
			{
				string += "\tcentrale "+(c+1)+" : "+getX(p, c)+" MW\n";
			}
			string += "\tcentrale "+(donnees.nbCentralesThermiques+1)+" : "+(getX(p, donnees.nbCentralesThermiques)*donnees.getTurbinage())+" MW\n";
			double sommeAchats = 0, sommeVentes = 0;
			for(int s = 0; s < donnees.nbScenarios; s++)
			{
				sommeAchats += getyAchat(p, s) / donnees.getScenario(s).getProbabilite();
				sommeVentes += getyVente(p, s) / donnees.getScenario(s).getProbabilite();
			}
			string += "\tAchat : "+sommeAchats+" €\n";
			string += "\tVente : "+sommeVentes+" €\n";
		}
		return string;
	}

	@Override
	public SolutionEnergie genererSolutionEnergie() {
		SolutionEnergie e = new SolutionEnergie();
		int size = x[0].length;
		double[][] values = new double[size][x.length];	// dans l'interface, les valeurs sont échangées
		
		for(int i = 0; i<size; i++)
			for(int j=0; j<x.length; j++)
				values[i][j] = x[j][i];		

		// il faut rajouter le turbinage pour passer en MW
		double[][] prodMax = donnees.getProductionsMax();
		for(int j=0; j<x.length; j++){
			values[values.length-1][j] *= donnees.getTurbinage();
			prodMax[values.length-1][j] *= donnees.getTurbinage();
		}
		
		e.setEnergies(values);
		e.setEnergiesMax(prodMax);
		
		return e;
	}
}
