package data;


/**
 * Cette classe contient les informations concernant une instance du problème sous la forme d'un recours avec scénarios.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 *
 */
public class DataRecours extends DataBase {
	
	/** Le prix d'achat de l'énergie par période */
	private double[] prixAchat;
	/** Le prix de vente de l'énergie par période */
	private double[] prixVente;
	/** La liste des scénarios */
	private ScenarioRecours[] scenarios;
	
	/**
	 * Construit une donnée en fonction d'un fichier
	 * @param fileName le chemin vers le fichier de données
	 */
	public DataRecours(String fileName) {

	}

	/**
	 * Retourne les scénarios
	 * @return les scénarios
	 */
	public ScenarioRecours[] getScenarios() {
		return scenarios;
	}
	
	/**
	 * Retourne les prix d'achat par période
	 * @return les prix d'achat par période
	 */
	public double[] getPrixAchat() {
		return prixAchat;
	}

	/**
	 * Retourne les prix de vente par période
	 * @return les prix de vente par période
	 */
	public double[] getPrixVente() {
		return prixVente;
	}
	
	/**
	 * Retourne le prix d'achat de l'énergie pour une période
	 * @param periode la période
	 * @return le prix d'achat de l'énergie pour une période
	 */
	public double getPrixAchatPeriode(int periode)
	{
		return prixAchat[periode];
	}
	
	/**
	 * Retourne le prix d'achat de l'énergie pour une période
	 * @param periode la période
	 * @return le prix d'achat de l'énergie pour une période
	 */
	public double getPrixVentePeriode(int periode)
	{
		return prixVente[periode];
	}

	/**
	 * Retourne le scénario voulu
	 * @param scenario l'indice du scénario
	 * @return le scénario voulu
	 */
	public ScenarioRecours getScenario(int scenario)
	{
		return scenarios[scenario];		
	}
}
