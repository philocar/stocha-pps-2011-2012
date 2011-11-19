package data;

/**
 * Cette classe contient les informations de base concernant une instance du problème.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 *
 */
public abstract class DataBase {
	
	/** Le vecteur de coûts. Pour chaque période et chaque centrale thermique il y a un cout de production en €/MW associé et pour la centrale hydrolique un cout d'utilisation de l'eau */
	protected double[][] couts;
	/** La production maximale de chaque centrale */
	protected double[] productionsMax;
	/** Le volume initial d'eau dans le réservoir */
	protected double volumeInitial;
	/** Le volume minimum d'eau dans le réservoir */
	protected double volumeMin;
	/** Le volume maximum d'eau dans le réservoir */
	protected double volumeMax;
	/** Le turbinage */
	protected double turbinage;
	/** Les apports en eau pour chaque période */
	private double[] apports;
	
	/**
	 * Retourne les coûts
	 * @return les couts
	 */
	public double[][] getCouts() {
		return couts;
	}
	
	/**
	 * Retourne les productions maximales
	 * @return les productions maximales
	 */
	public double[] getProductionsMax() {
		return productionsMax;
	}

	/**
	 * Retourne le volume initial d'eau dans le réservoir
	 * @return le volume initial d'eau dans le réservoir
	 */
	public double getVolumeInitial() {
		return volumeInitial;
	}

	/**
	 * Retourne le volume minimum d'eau dans le réservoir
	 * @return le volume minimum d'eau dans le réservoir
	 */
	public double getVolumeMin() {
		return volumeMin;
	}

	/**
	 * Retourne le volume maximum d'eau dans le réservoir
	 * @return le volume maximum d'eau dans le réservoir
	 */
	public double getVolumeMax() {
		return volumeMax;
	}

	/**
	 * Retourne le turbinage
	 * @return le turbinage
	 */
	public double getTurbinage() {
		return turbinage;
	}
	
	/**
	 * Retourne le cout de production d'une centrale pour une période
	 * @param periode la période
	 * @param centrale la centrale
	 * @return le cout de production d'une centrale pour une période
	 */
	public double getCoutPeriodeCentrale(int periode, int centrale)
	{
		return couts[periode][centrale];
	}
	
	/**
	 * Retourne la production maximale d'une centrale
	 * @param centrale la centrale
	 * @return les production maximale d'une centrale
	 */
	public double getProductionMaxCentrale(int centrale) 
	{
		return productionsMax[centrale];
	}

	/**
	 * Retourne les apports
	 * @return les apports
	 */
	public double[] getApports() {
		return apports;
	}
	
	/**
	 * Retourne les apports d'une période
	 * @return les apports d'une période
	 */
	public double getApportsPeriode(int periode) {
		return apports[periode];
	}
}
