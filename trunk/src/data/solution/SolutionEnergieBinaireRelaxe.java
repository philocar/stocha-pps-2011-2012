package data.solution;

import data.DataBinaire;

/**
 * Une solution du problème de management de la production d'énergie en binaire pour le relaxé
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class SolutionEnergieBinaireRelaxe extends Solution implements SolutionCentrale {

	/**
	 * La matrice de décision pour chaque periode, chaque centrale, on a le vecteur de palier
	 */
	private double[][][] y;
	/** Le vecteur d'activation des scénarios par périodes */
	private double[][] z;
	/** Le vecteur des trajectoires hydrauliques */
	private double[] trajectoires;
	/** Les données du problèmes */
	private DataBinaire donnees;

	/**
	 * Crée une nouvelle solution spécifique au problème de management de la
	 * production d'énergie sous sa forme binaire et sa relaxation
	 * @param donnees les données du problème
	 */
	public SolutionEnergieBinaireRelaxe(DataBinaire donnees) {
		this.donnees = donnees;
		y = new double[donnees.nbPeriodes][donnees.nbCentrales][];
		z = new double[donnees.nbPeriodes][donnees.nbScenarios];
		trajectoires = new double[donnees.nbTrajectoires];
		
		for(int p=0; p<donnees.nbPeriodes; p++)
		{
			for(int c=0; c<donnees.nbCentrales; c++)
			{
				y[p][c] = new double[donnees.nbPaliers[c]];
			}
		}
	}

	@Override
	public Solution clone() {
		return null;
	}

	@Override
	public double deltaF(Solution solution) {

		return solution.fonctionObjectif() - this.fonctionObjectif();
	}

	@Override
	public double fonctionObjectif() {
		double val = 0;
		// Pour chaque période
		for (int p = 0; p < donnees.nbPeriodes; p++) {
			// Pour chaque centrale thermique
			for (int c = 0; c < donnees.nbCentrales; c++) {
				// On calcule son coût de production
				for(int palier=0; palier<donnees.nbPaliers[c]; palier++)
					val += donnees.getPalier(c, palier) * donnees.getCoutCentrale(c) * y[p][c][palier];
			}

			// On calcule le coût de production du réservoire
			for(int i=0; i<donnees.nbTrajectoires; i++)
			{
				val += (donnees.getTrajectoire(i) * trajectoires[i] / donnees.getTurbinage()) * donnees.getCoutCentrale(4);
			}
			
			val -= donnees.getApportsPeriode(p) * donnees.getCoutCentrale(4);
		}
		return val;
	}

	@Override
	public void solutionInitiale() {

	}

	/**
	 * Vérifie que la solution respecte la demande
	 * @return true si la solution respecte la demande, false sinon
	 */
	public boolean respecteContrainteDemande() {
		return true;
	}

	/**
	 * Vérifie que la solution respecte la demande pour une période donnée
	 * @param periode la période
	 * @return true si la solution respecte la demande pour la période donnée, false sinon
	 */
	public boolean respecteContrainteDemandePeriode(int periode) {
		return true;
	}

	/**
	 * Retourne la somme des probabilités des scénarios actifs
	 * @return la somme des probabilités des scénarios actifs
	 */
	public double probabiliteScenario() {
		return 0;
	}

	/**
	 * Retourne le vecteur des scénarios
	 * @return le vecteur des scénarios
	 */
	public double[][] getZ() {
		return z;
	}

	/**
	 * Retourne la matrice de décisions
	 * @return la matrice de décisions
	 */
	public double[][][] getY() {
		return y;
	}

	/**
	 * Teste si le scénario est actif pour une période
	 * @parma p la période
	 * @param s le scénario
	 * @return true si le scénario est actif, false sinon
	 */
	public double isActived(int p, int s) {
		return z[p][s];
	}
	
	/**
	 * Modifie l'activation d'un scénario pour une période
	 * @param p la période
	 * @param s le scénario
	 * @param val la valeur
	 */
	public void setZ(int p, int s, double val)
	{
		z[p][s] = val;
	}

	/**
	 * Retourne la décision pour une période, une centrale et le palier
	 * @param periode la période
	 * @param centrale la centrale
	 * @param palier le palier
	 * @return la décision pour la période, la centrale et le palier
	 */
	public double[] getDecisionPeriodeCentralePalier(int periode, int centrale, int palier) {
		return y[periode][centrale];
	}

	/**
	 * Retourne la trajectoire
	 * @return la trajectoire
	 */
	public double[] getTrajectoire() {
		return trajectoires;
	}

	/**
	 * Modifie la décision pour une période, une centrale, un palier
	 * @param periode la période
	 * @param centrale la centrale
	 * @param palier le palier
	 * @param val la valeur
	 */
	public void setDecisionPeriodeCentrale(int periode, int centrale, int palier, double val) {
		y[periode][centrale][palier] = val;
	}

	/** 
	 * Modifie la valeur d'un choix de trajectoire
	 * @param i
	 * @param val
	 */
	public void setChoixTrajectoire(int i, double val) {
		trajectoires[i] = val;
	}

	public String toString() {
		String str = "Fonction Objectif = " + fonctionObjectif() + "\n";

		for (int p = 0; p < donnees.nbPeriodes; p++) {
			str += "Periode : " + p + "\n";
			for (int c = 0; c < donnees.nbCentrales; c++) {
				str += "Centrale " + c + " : \n";
				for(int palier=0; palier<donnees.nbPaliers[c]; palier++)
				{
					str += "Palier " + palier + " : " + y[p][c][palier] + "\n";
				}
			}
		}
		
		str += "Reservoir : \n";
		for(int i=0; i<donnees.nbTrajectoires; i++)
		{
			str += "Trajectoire " + i + " : " + trajectoires[i] + "\n";
		}
		return str;
	}

	public SolutionEnergie genererSolutionEnergie() {
		/*	SolutionEnergie e = new SolutionEnergie();
		double[][] tabEnergie = new double[donnees.nbCentrales + 1][donnees.nbPeriodes];

		int c;
		for (int p = 0; p < donnees.nbPeriodes; p++) {
			for (c = 0; c < donnees.nbCentrales; c++) {
				tabEnergie[c][p] = donnees.getPalier(c, y[p][c]);
			}

			tabEnergie[c][p] = ((donnees.getTrajectoire(trajectoire) / donnees.getTurbinage()) - donnees
					.getApportsPeriode(p));
		}
		e.setEnergies(tabEnergie);

		double[][] energiesMax = donnees.getProductionsMax();
		e.setEnergiesMax(energiesMax);

		return e;*/
		return null;
	}
}

