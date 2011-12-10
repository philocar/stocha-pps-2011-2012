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
		
		int nbScenarios = 100;
		int nbtrajectoires = 8;
		int nbPaliers1 = 3;
		int nbPaliers2 = 4;
		int nbPaliers3 = 4;
		int nbPaliers4 = 5;
		
		scenarios = new ScenarioBinaire[nbScenarios];
		trajectoires = new double[nbtrajectoires];
		this.probabilite = probabilite;
		
		double[][] demandesTmp = new double[nbScenarios][7];
		double[][][][] productionsTmp = new double[nbScenarios][4][7][];
		
		for(int i=0; i<nbScenarios; i++)
		{
			for(int j=0; j<7; j++)
			{
				productionsTmp[i][0][j] = new double[nbPaliers1];
				productionsTmp[i][1][j] = new double[nbPaliers2];
				productionsTmp[i][2][j] = new double[nbPaliers3];
				productionsTmp[i][3][j] = new double[nbPaliers4];
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
				while ((line = buffDemandes.readLine()) != null && i < 100) {
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
					// Les 3 premières lignes n'ont pas de données
					if(i > 2)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							productionsTmp[i-3][0][j/nbPaliers1][j%nbPaliers1] = Double.parseDouble(token);
							j++;
						}
					}
					i++;
				}
				
				i = 0;
				// Lecture du fichier des coefficients de la centrale 2 ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffCoeffCentrale2.readLine()) != null) {
					// Les 3 premières lignes n'ont pas de données
					if(i > 2)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							productionsTmp[i-3][1][j/nbPaliers2][j%nbPaliers2] = Double.parseDouble(token);
							j++;
						}
					}
					i++;
				}
				
				i = 0;
				// Lecture du fichier des coefficients de la centrale 3 ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffCoeffCentrale3.readLine()) != null) {
					// Les 3 premières lignes n'ont pas de données
					if(i > 2)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							productionsTmp[i-3][2][j/nbPaliers3][j%nbPaliers3] = Double.parseDouble(token);
							j++;
						}
					}
					i++;
				}
				
				i = 0;
				// Lecture du fichier des coefficients de la centrale 4 ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffCoeffCentrale4.readLine()) != null) {
					// Les 3 premières lignes n'ont pas de données
					if(i > 2)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Le premier token n'est pas intéressant
						tokenizer.nextToken();
						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							productionsTmp[i-3][3][j/nbPaliers4][j%nbPaliers4] = Double.parseDouble(token);
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
				
				double[] test = productionsTmp[0][0][0];
				System.out.println(test.length);
				for(double palier : test)
				{
					System.out.println(palier);
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
	
	public static void main(String[] args)
	{
		DataBinaire data = new DataBinaire(98, "Data/Données_Recuit_demandes.csv", "Data/Données_Recuit_paliers1.csv", "Data/Données_Recuit_paliers2.csv", "Data/Données_Recuit_paliers3.csv", "Data/Données_Recuit_paliers4.csv", "Data/Données_Recuit_trajectoire_hydro.csv", "Data/Données_Recuit_parametres_hydro.csv", "Data/Données_Recuit_capacité.csv");
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
