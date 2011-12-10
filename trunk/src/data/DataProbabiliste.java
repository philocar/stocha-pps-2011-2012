package data;


/**
 * Cette classe contient les informations concernant une instance du problème sous la forme probabiliste.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 *
 */
public class DataProbabiliste extends DataBase {

	/** Pour chaque période il y a une probabilité minimale souhaitée */
	private double[] probabilites;
	/** L'espérance du facteur de disponibilité par période */
	private double[] EFacteurDisponibilite;
	/** L'espérance de la demande par période */
	private double[] EDemande;
	/** La variance de la loi de distribution voulue */
	private double variance;
	
	/**
	 * Construit une donnée en fonction d'un fichier
	 * @param fileName le chemin vers le fichier de données
	 */
	public DataProbabiliste(String fileName) {
		super(null, null);
	}

	/**
	 * Retourne les probabilités
	 * @return les probabilités
	 */
	public double[] getProbabilites() {
		return probabilites;
	}

	/**
	 * Retourne les espérances des facteur de disponibilité
	 * @return les espérances des facteur de disponibilité
	 */
	public double[] getEFacteurDisponibilite() {
		return EFacteurDisponibilite;
	}

	/**
	 * Retourne les espérances des demandes
	 * @return les espérances des demandes
	 */
	public double[] getEDemande() {
		return EDemande;
	}

	/**
	 * @return la variance
	 */
	public double getVariance() {
		return variance;
	}
	
	/**
	 * @return the eFacteurDisponibilite
	 */
	public double getEFacteurDisponibilitePeriode(int periode) {
		return EFacteurDisponibilite[periode];
	}

	/**
	 * @return the eDemande
	 */
	public double getEDemandePeriode(int periode) {
		return EDemande[periode];
	}
}
