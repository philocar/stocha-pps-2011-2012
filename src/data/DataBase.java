package data;

/**
 * Cette classe contient les informations de base concernant une instance du problème.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 *
 */
public abstract class DataBase {
	
	/** Le vecteur de coûts. Pour chaque centrale thermique il y a un cout de production en €/MW associé et pour la centrale hydrolique un cout d'utilisation de l'eau en €/m^3 */
	protected double[] couts;
	/** La production maximale de chaque centrale pour chaque période */
	protected double[][] productionsMax;
	/** Le volume initial d'eau dans le réservoir */
	protected double volumeInitial;
	/** Les volumes minimum d'eau dans le réservoir par périodes */
	protected double[] volumeMin;
	/** Les volumes maximum d'eau dans le réservoir par périodes */
	protected double[] volumeMax;
	/** Le turbinage */
	protected double turbinage;
	/** Les apports en eau pour chaque période */
	private double[] apports;
	
	/**
	 * Crée les données à partir des fichiers
	 * @param parametresHydrauliquesFile le fichier csv contenant les paramètres hydrauliques
	 * @param capaciteMaxFile le fichier csv contenant la capacité max
	 */
	public DataBase(String parametresHydrauliquesFile, String capaciteMaxFile)
	{
		
	}
	
	/**
	 * Retourne les coûts
	 * @return les couts
	 */
	public double[] getCouts() {
		return couts;
	}
	
	/**
	 * Retourne les productions maximales
	 * @return les productions maximales
	 */
	public double[][] getProductionsMax() {
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
	 * Retourne le volume minimum d'eau dans le réservoir pour un période
	 * @param periode la période
	 * @return le volume minimum d'eau dans le réservoir pour la période
	 */
	public double getVolumeMin(int periode) {
		return volumeMin[periode];
	}

	/**
	 * Retourne le volume maximum d'eau dans le réservoir pour un période
	 * @param periode la période
	 * @return le volume maximum d'eau dans le réservoir pour la période
	 */
	public double getVolumeMax(int periode) {
		return volumeMax[periode];
	}

	/**
	 * Retourne le turbinage
	 * @return le turbinage
	 */
	public double getTurbinage() {
		return turbinage;
	}
	
	/**
	 * Retourne le cout de production d'une centrale
	 * @param centrale la centrale
	 * @return le cout de production d'une centrale
	 */
	public double getCoutPeriodeCentrale(int centrale)
	{
		return couts[centrale];
	}
	
	/**
	 * Retourne la production maximale d'une centrale
	 * @param centrale la centrale
	 * @param periode la période
	 * @return les production maximale d'une centrale
	 */
	public double getProductionMaxCentrale(int centrale, int periode) 
	{
		return productionsMax[centrale][periode];
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
