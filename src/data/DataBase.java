package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

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
		apports = new double[7];
		volumeMax = new double[7];
		volumeMin = new double[7];
		couts = new double[5];
		productionsMax = new double[5][7];
		
		try{
			BufferedReader buffParamHydro = new BufferedReader(new FileReader(parametresHydrauliquesFile));
			BufferedReader buffCapaciteMax = new BufferedReader(new FileReader(capaciteMaxFile));

			try {
				String line;
				int i = 0;
				// Lecture du fichier des paramètres hydrauliques ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buffParamHydro.readLine()) != null) {
					// On saut les lignes sans donnée
					if(i % 2 != 0)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						
						if(i != 9)
						{
							// Saute le nom du paramètre
							tokenizer.nextToken();
	
							int j = 0;
							// Tant qu'il y a des tokens on les parcourt
							while(tokenizer.hasMoreTokens())
							{
								String token = tokenizer.nextToken();
								// On ne s'intéresse qu'aux tokens représentant une donnée qui est la production maximale
								if(i == 1)
								{
									apports[j] = Double.parseDouble(token);
								}
								else if(i == 3)
								{
									volumeMax[j] = Double.parseDouble(token);
								}
								else if(i == 5)
								{
									volumeInitial = Double.parseDouble(token);
								}
								else if(i == 7)
								{
									volumeMin[j] = Double.parseDouble(token);
								}
								j++;
							}
						}
						else if(i == 9)
						{
							turbinage = Double.parseDouble(tokenizer.nextToken().substring(4));
						}
					}
					i++;
				}
				
				// Lecture du fichier des capacités max
				i = 0;
				while ((line = buffCapaciteMax.readLine()) != null) {
					// On saut les lignes sans donnée
					if(i % 2 != 0)
					{
						StringTokenizer tokenizer = new StringTokenizer(line, ";");
						// Saute le nom de la centrale
						tokenizer.nextToken();
						// Le deuxième token contient le cout de production
						couts[i/2] = Double.parseDouble(tokenizer.nextToken());

						int j = 0;
						// Tant qu'il y a des tokens on les parcourt
						while(tokenizer.hasMoreTokens())
						{
							String token = tokenizer.nextToken();
							// On ne s'intéresse qu'aux tokens représentant une donnée qui est la production maximale
							if(!token.equals("MW") && !token.equals("m3"))
							{
								productionsMax[i/2][j] = Double.parseDouble(token);
								j++;
							}
						}
					}
					i++;
				}
			} finally {
				// dans tous les cas, on ferme nos flux
				buffParamHydro.close();
				buffCapaciteMax.close();
			}
		} catch (IOException ioe) {
			// erreur de fermeture des flux
			System.out.println("Erreur --" + ioe.toString());
		}
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
	public double getCoutCentrale(int centrale)
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
