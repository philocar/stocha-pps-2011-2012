package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


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
	/** Les probabilités vouluent que les scénarios se déroulent par période */
	private double[] probabilites = {0.96, 0.96, 0.96, 0.96, 0.95, 0.94, 0.93};
	/** Les paliers de chaque centrales thermiques */
	private double[][] paliers;
	/** Le nombre de scénarios */
	public final int nbScenarios = 100;
	/** Le nombre de trajectoires */
	public final int nbTrajectoires = 8;
	/** Le nombre de paliers pour chaque centrale thermique */
	public final int[] nbPaliers = {3, 4, 4, 5};
	/** Le nombre de périodes */
	public final int nbPeriodes = 7;
	/** Le nombre de centrales */
	public final int nbCentrales = 4;
	
	/**
	 * Construit une donnée binaire en fonction des fichiers. Les scénarios sont équiprobables
	 * @param demandesFile le fichier csv contenant les scénarios de demandes
	 * @param coeffCentrale1File le fichier csv contenant les paliers de la centrale 1
	 * @param coeffCentrale2File le fichier csv contenant les paliers de la centrale 2
	 * @param coeffCentrale3File le fichier csv contenant les paliers de la centrale 3
	 * @param coeffCentrale4File le fichier csv contenant les paliers de la centrale 4
	 * @param trajectoiresFile le fichier csv contenant les trajectoires hydrauliques
	 * @param parametresHydrauliquesFile le fichier csv contenant les paramètres hydrauliques
	 * @param capaciteMaxFile le fichier csv contenant la capacité max et les coûts
	 */
	public DataBinaire(String demandesFile, String coeffCentrale1File, String coeffCentrale2File, String coeffCentrale3File, String coeffCentrale4File, String trajectoiresFile, String parametresHydrauliquesFile, String capaciteMaxFile) {
		super(parametresHydrauliquesFile, capaciteMaxFile);
		
		scenarios = new ScenarioBinaire[nbScenarios];
		trajectoires = new double[nbTrajectoires];
		paliers = new double[nbCentrales][];
		
		for(int i=0; i<nbCentrales; i++)
			paliers[i] = new double[nbPaliers[i]];
			
		double[][] demandesTmp = new double[nbScenarios][nbPeriodes];
		double[][][][] productionsTmp = new double[nbScenarios][nbCentrales][nbPeriodes][];
		
		for(int i=0; i<nbScenarios; i++)
		{
			for(int j=0; j<nbPeriodes; j++)
			{
				productionsTmp[i][0][j] = new double[nbPaliers[0]];
				productionsTmp[i][1][j] = new double[nbPaliers[1]];
				productionsTmp[i][2][j] = new double[nbPaliers[2]];
				productionsTmp[i][3][j] = new double[nbPaliers[3]];
			}
		}

		try{
			BufferedReader buffDemandes = new BufferedReader(new FileReader(demandesFile));
			BufferedReader buffCoeffCentrale1 = new BufferedReader(new FileReader(coeffCentrale1File));
			BufferedReader buffCoeffCentrale2 = new BufferedReader(new FileReader(coeffCentrale2File));
			BufferedReader buffCoeffCentrale3 = new BufferedReader(new FileReader(coeffCentrale3File));
			BufferedReader buffCoeffCentrale4 = new BufferedReader(new FileReader(coeffCentrale4File));
			BufferedReader buffTrajectoires = new BufferedReader(new FileReader(trajectoiresFile));

			try {
				String line;
				int i = 0;
				// Lecture du fichier des demandes ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffDemandes.readLine()) != null && i < nbScenarios) {
					StringTokenizer tokenizer = new StringTokenizer(line, ";");
					int j = 0;
					// Tant qu'il y a des tokens on les parcourt
					while(tokenizer.hasMoreTokens())
					{
						String token = tokenizer.nextToken();
						demandesTmp[i][j] = Double.parseDouble(token);
						j++;
					}
					i++;
				}
				
				i = 0;
				// Lecture du fichier des coefficients de la centrale 1 ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffCoeffCentrale1.readLine()) != null) {
					if(i == 1)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// On parcourt les n paliers
						while(tokenizer.hasMoreTokens() && j < nbPaliers[0])
						{
							String token = tokenizer.nextToken();
							paliers[0][j] = Double.parseDouble(token);
							j++;
						}
					}
					else if(i > 2)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							productionsTmp[i-3][0][j/nbPaliers[0]][j%nbPaliers[0]] = Double.parseDouble(token);
							j++;
						}
					}
					i++;
				}
				
				i = 0;
				// Lecture du fichier des coefficients de la centrale 2 ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffCoeffCentrale2.readLine()) != null) {
					if(i == 1)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// On parcourt les n paliers
						while(tokenizer.hasMoreTokens() && j < nbPaliers[1])
						{
							String token = tokenizer.nextToken();
							paliers[1][j] = Double.parseDouble(token);
							j++;
						}
					}
					else if(i > 2)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							productionsTmp[i-3][1][j/nbPaliers[1]][j%nbPaliers[1]] = Double.parseDouble(token);
							j++;
						}
					}
					i++;
				}
				
				i = 0;
				// Lecture du fichier des coefficients de la centrale 3 ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffCoeffCentrale3.readLine()) != null) {
					if(i == 1)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// On parcourt les n paliers
						while(tokenizer.hasMoreTokens() && j < nbPaliers[2])
						{
							String token = tokenizer.nextToken();
							paliers[2][j] = Double.parseDouble(token);
							j++;
						}
					}
					else if(i > 2)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							productionsTmp[i-3][2][j/nbPaliers[2]][j%nbPaliers[2]] = Double.parseDouble(token);
							j++;
						}
					}
					i++;
				}
				
				i = 0;
				// Lecture du fichier des coefficients de la centrale 4 ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffCoeffCentrale4.readLine()) != null) {
					if(i == 1)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// On parcourt les n paliers
						while(tokenizer.hasMoreTokens() && j < nbPaliers[3])
						{
							String token = tokenizer.nextToken();
							paliers[3][j] = Double.parseDouble(token);
							j++;
						}
					}
					else if(i > 2)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							productionsTmp[i-3][3][j/nbPaliers[3]][j%nbPaliers[3]] = Double.parseDouble(token);
							j++;
						}
					}
					i++;
				}

				// Lecture du fichier des trajectoires hydrauliques.
				// La première ligne n'est pas intéressante
				buffTrajectoires.readLine();
				line = buffTrajectoires.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ";");
				// Le premier token n'est pas intéressant
				tokenizer.nextToken();
				int j = 0;
				// Tant qu'il y a des tokens on les parcourt
				while(tokenizer.hasMoreTokens())
				{
					String token = tokenizer.nextToken();
					trajectoires[j] = Double.parseDouble(token);
					j++;
				}
				
				// Création des scénarios
				for(int scenario=0; scenario<nbScenarios; scenario++)
				{
					scenarios[scenario] = new ScenarioBinaire(productionsTmp[scenario], demandesTmp[scenario], 1/(double)nbScenarios);
				}			
			} finally {
				// dans tous les cas, on ferme nos flux
				buffDemandes.close();
				buffCoeffCentrale1.close();
				buffCoeffCentrale2.close();
				buffCoeffCentrale3.close();
				buffCoeffCentrale4.close();
				buffTrajectoires.close();
			}
		} catch (IOException ioe) {
			// erreur de fermeture des flux
			System.out.println("Erreur --" + ioe.toString());
		}
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
	public ScenarioBinaire getScenario(int scenario)
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
	
	/**
	 * Retourne la probabilité pour la période voulue
	 * @param p la période
	 * @return la probabilité pour la période voulue
	 */
	public double getProbabilite(int p)
	{
		return probabilites[p];
	}
	
	/**
	 * Retourne la production du palier demandé de la centrale voulue
	 * @param centrale la centrale
	 * @param palier le palier
	 * @return La production du palier demandé de la centrale voulue
	 */
	public double getPalier(int centrale, int palier) {
		return paliers[centrale][palier];
	}
	
	public static void main(String[] args)
	{
		DataBinaire data = new DataBinaire("Data/Données_Recuit_demandes.csv", "Data/Données_Recuit_paliers1.csv", "Data/Données_Recuit_paliers2.csv", "Data/Données_Recuit_paliers3.csv", "Data/Données_Recuit_paliers4.csv", "Data/Données_Recuit_trajectoire_hydro.csv", "Data/Données_Recuit_parametres_hydro.csv", "Data/Données_Recuit_capacité.csv");
		for(ScenarioBinaire scenario : data.getScenarios())
		{
			double[] test = scenario.getPaliersPeriodeCentrale(0, 0);
			System.out.println(test.length);
			for(double palier : test)
			{
				System.out.println(palier);
			}
		}
	}
}
