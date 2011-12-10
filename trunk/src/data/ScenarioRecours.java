package data;

/** 
 * Cette classe représente un scénario pour le problème avec recours
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class ScenarioRecours {

	/** Pour chaque période et chaque centrale thermique il y a un facteur de disponiblité */
	private double[][] facteursDisponibilite;
	/** Le vecteur de demandes. Pour chaque période il y a une demande  */
	private double[] demandes;
	/** La probabilité que le scénario se déroule */
	private double probabilite;
	
	/**
	 * Crée un scénario pour le problème binaire à partir des productions et des demandes
	 * @param productions les paliers de productions par période par centrale
	 * @param demandes les demandes par période
	 * @param probabilite la probabilité que le scénario se déroule
	 */
	public ScenarioRecours(double[][] facteurDisponibilite, double[] demandes, double probabilite) {
		this.facteursDisponibilite = facteurDisponibilite;
		this.demandes = demandes;
		this.probabilite = probabilite;
	}

	/**
	 * Retourne la matrice des facteurs de disponibilité
	 * @return la matrice des facteurs de disponibilité
	 */
	public double[][] getFacteursDisponibilite() {
		return facteursDisponibilite;
	}

	/**
	 * Retourne le vecteur de demandes
	 * @return le vecteur de demandes
	 */
	public double[] getDemandes() {
		return demandes;
	}
	
	public double getProbabilite()
	{
		return probabilite;
	}
	
	/**
	 * Retourne le facteur de disponibilité d'une centrale pour une période
	 * @param periode la periode
	 * @param centrale la centrale
	 * @return le facteur de disponibilité d'une centrale pour une période
	 */
	public double getPaliersPeriodeCentrale(int periode, int centrale)
	{
		return facteursDisponibilite[periode][centrale];
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
