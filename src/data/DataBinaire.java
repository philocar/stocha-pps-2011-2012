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
	 * Construit une donnée binaire en fonction des fichiers. Les scénarios sont équiprobables
	 * @param demandesFile
	 * @param coeffCentrale1File
	 * @param coeffCentrale2File
	 * @param coeffCentrale3File
	 * @param coeffCentrale4File
	 * @param trajectoiresFile
	 * @param parametresHydrauliquesFile le fichier csv contenant les paramètres hydrauliques
	 * @param capaciteMaxFile le fichier csv contenant la capacité max
	 */
	public DataBinaire(double probabilite, String demandesFile, String coeffCentrale1File, String coeffCentrale2File, String coeffCentrale3File, String coeffCentrale4File, String trajectoiresFile, String parametresHydrauliquesFile, String capaciteMaxFile) {
		super(parametresHydrauliquesFile, capaciteMaxFile);
		
		
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
