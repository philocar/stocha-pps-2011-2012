package data;


/**
 * Cette classe contient les informations concernant une instance du problème sous la forme binaire et sa relaxation.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 *
 */
public class DataBinaire {
	
	/** Le vecteur de coûts. Pour chaque période et chaque centrale thermique il y a un cout de production en €/MW associé et pour la centrale hydrolique un cout d'utilisation de l'eau */
	private double[][] couts;
	/** Tableau contenant les différents scénarios */
	private ScenarioBinaire[] scenarios;
	
	/**
	 * Construit une donnée en fonction d'un fichier
	 * @param fileName le chemin vers le fichier de données
	 */
	public DataBinaire(String fileName) {
		
	}

	/**
	 * Retourne les coûts
	 * @return les couts
	 */
	public double[][] getCouts() {
		return couts;
	}

	/**
	 * Retourne les scénarios
	 * @return les scenarios
	 */
	public ScenarioBinaire[] getScenarios() {
		return scenarios;
	}
	
	/**
	 * Retourne le scénario voulu
	 * @param scenario l'indice du scénario
	 * @return le scénario voulu
	 */
	public ScenarioBinaire getScénario(int scenario)
	{
		return scenarios[scenario];		
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
}
