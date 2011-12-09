package data;


/**
 * Cette classe contient les informations concernant une instance du problème sous la forme binaire et sa relaxation.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 *
 */
public class DataBinaire extends DataBase {
	/** Tableau contenant les différents scénarios */
	private ScenarioBinaire[] scenarios;
	/** Les trajectoires hydrauliques */
	private double[] trajectoires;
	/** La probabilité voulue que les scénarios se déroulent */
	double probabilite;
	
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
	public double[] getCouts() {
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
	 * Retourne les trajectoires hydrauliques
	 * @return les trajectoires hydrauliques
	 */
	public double[] getTrajectoires()
	{
		return trajectoires;
	}
	
	/**
	 * Retourne la trajectoire hydraulique voulue
	 * @param trajectoire la trajectoire voulue
	 * @return la trajectoire hydraulique voulue
	 */
	public double getTrajectoire(int trajectoire)
	{
		return trajectoires[trajectoire];
	}
}
