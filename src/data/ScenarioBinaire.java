package data;

/** 
 * Cette classe représente un scénario pour le problème binaire
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class ScenarioBinaire {

	/** La matrice de productions. Pour chaque période, pour chaque centrale, il y a un vecteur de paliers de production réels affectés par l'aléa */
	double[][][] productions;
	/** Le vecteur de demandes. Pour chaque période il y a une demande  */
	double[] demandes;
	
	/**
	 * Crée un scénario pour le problème binaire à partir des productions et des demandes
	 * @param productions les paliers de productions par période par centrale
	 * @param demandes les demandes par période
	 */
	public ScenarioBinaire(double[][][] productions, double[] demandes) {
		this.productions = productions;
		this.demandes = demandes;
	}

	/**
	 * Retourne la matrice de productions
	 * @return la matrice de productions
	 */
	public double[][][] getProductions() {
		return productions;
	}

	/**
	 * Retourne le vecteur de demandes
	 * @return le vecteur de demandes
	 */
	public double[] getDemandes() {
		return demandes;
	}
	
	/**
	 * Retourne les paliers de production d'une centrale pour une période
	 * @param periode la periode
	 * @param centrale la centrale
	 * @return les paliers de production d'une centrale pour une période
	 */
	public double[] getPaliersPeriodeCentrale(int periode, int centrale)
	{
		return productions[periode][centrale];
	}
	
	/**
	 * Returne la demande de la période souhaitée
	 * @param periode la période
	 * @return la demande de la période souhaitée
	 */
	public double getDemandePeriode(int periode)
	{
		return demandes[periode];
	}
}
