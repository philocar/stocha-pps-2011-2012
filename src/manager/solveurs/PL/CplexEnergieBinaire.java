package manager.solveurs.PL;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;
import data.DataBinaire;

/**
 * Cette classe permet de résoudre une instance du problème de management de la
 * production d'énergie à l'aide de CPLEX
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class CplexEnergieBinaire extends PLEnergieBinaire {

	/**
	 * Crée un nouveau CPlexEnergie
	 * 
	 * @param donnees
	 *            les données du problème
	 * @param fileName
	 */
	public CplexEnergieBinaire(DataBinaire donnees) {
		super(donnees);
		System.out.println("solveur relaxe");
	}

	/**
	 * Lance la résolution du programme linéaire. Crée le programme à partir des
	 * tableaux de coefficients.
	 */
	public void lancer() {
		try {
			IloCplex cplex = new IloCplex();
			int nbPaliers = donnees.nbPaliers[0] + donnees.nbPaliers[1] + donnees.nbPaliers[2] + donnees.nbPaliers[3];

			IloIntVar[] y = cplex.boolVarArray(donnees.nbPeriodes * nbPaliers + donnees.nbTrajectoires);
			IloIntVar[] z = cplex.boolVarArray(donnees.nbScenarios);

			int ythetaSize = donnees.nbPeriodes * nbPaliers;
			int ynSize = donnees.nbTrajectoires;

			IloIntVar[] ytheta = cplex.boolVarArray(ythetaSize);
			IloIntVar[] yn = cplex.boolVarArray(ynSize);
			for (int i = 0; i < ythetaSize; i++) {
				ytheta[i] = y[i];
			}
			for (int i = 0; i < ynSize; i++) {
				yn[i] = y[i + ythetaSize];
			}

			double M = 200000;

			cplex.addMinimize(cplex.scalProd(couts, y));

			// Un seul palier accepté par période et par centrale
			for (int p = 0; p < donnees.nbPeriodes; p++) {
				int sommePaliers = 0;
				for (int c = 0; c < donnees.nbCentrales; c++) {
					IloIntVar[] varY = new IloIntVar[donnees.nbPaliers[c]];
					for (int numPalier = 0; numPalier < donnees.nbPaliers[c]; numPalier++) {
						varY[numPalier] = ytheta[p * nbPaliers + sommePaliers];
						sommePaliers++;
					}
					IloRange eq = cplex.eq(cplex.sum(varY), 1);
					cplex.add(eq);
				}
			}

			// Une seule trajectoire est choisie
			IloRange eq = cplex.eq(cplex.sum(yn), 1);
			cplex.add(eq);

			// La somme des probabilités des scénarios doit être supérieurs à p
			double[] probabilites = new double[donnees.nbScenarios];
			for (int i = 0; i < donnees.nbScenarios; i++) {
				probabilites[i] = donnees.getScenario(i).getProbabilite();
			}
			IloRange eqg = cplex.ge(cplex.scalProd(probabilites, z), donnees.getProbabilite());
			cplex.add(eqg);

			// Contraintes sur les demandes
			IloLinearNumExpr exprYn = cplex.scalProd(donnees.getTrajectoires(), yn);
			for (int p = 0; p < donnees.nbPeriodes; p++) {
				IloNumVar[] varYt = new IloNumVar[nbPaliers];

				for (int i = 0; i < nbPaliers; i++) {
					varYt[i] = ytheta[p * nbPaliers + i];
				}

				for (int s = 0; s < donnees.nbScenarios; s++) {
					double[] prod = new double[nbPaliers];
					int sommePaliers = 0;
					for (int c = 0; c < donnees.nbCentrales; c++) {
						for (int numPalier = 0; numPalier < donnees.nbPaliers[c]; numPalier++) {
							prod[sommePaliers] = donnees.getScenario(s).getPaliersPeriodeCentrale(c, p)[numPalier];
							sommePaliers++;
						}
					}
					IloLinearNumExpr exprYt = cplex.scalProd(prod, varYt);
					IloRange ge = cplex.ge(
							cplex.sum(exprYt, exprYn,
									cplex.prod(-1 * (donnees.getScenario(s).getDemandePeriode(p) + M), z[s])), -M);
					cplex.add(ge);
				}
			}

			if (cplex.solve()) {
				System.out.println("Solution status = " + cplex.getStatus());
				System.out.println("Solution value = " + cplex.getObjValue());
				double[] valYt = cplex.getValues(ytheta);
				double[] valYn = cplex.getValues(yn);
				double[] valZ = cplex.getValues(z);

				for (int p = 0; p < donnees.nbPeriodes; p++) {
					int sommePaliers = 0;
					for (int c = 0; c < donnees.nbCentrales; c++) {
						for (int numPalier = 0; numPalier < donnees.nbPaliers[c]; numPalier++) {
							if (valYt[p * nbPaliers + sommePaliers] > 0) {
								solution.setDecisionPeriodeCentrale(p, c, numPalier);
							}

							sommePaliers++;
						}
					}
				}

				for (int i = 0; i < valYn.length; i++) {
					if (valYn[i] > 0)
						solution.setTrajectoire(i);
				}

				for (int s = 0; s < valZ.length; s++) {
					if (valZ[s] > 0)
						solution.active(s, true);
					else
						solution.active(s, false);
				}

				System.out.println(solution.toString());
				cplex.end();
			}

		} catch (IloException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DataBinaire data = new DataBinaire(0.98, "Data/Données_Recuit_demandes.csv",
				"Data/Données_Recuit_paliers1.csv", "Data/Données_Recuit_paliers2.csv",
				"Data/Données_Recuit_paliers3.csv", "Data/Données_Recuit_paliers4.csv",
				"Data/Données_Recuit_trajectoire_hydro.csv", "Data/Données_Recuit_parametres_hydro.csv",
				"Data/Données_Recuit_capacité.csv");
		CplexEnergieBinaire test = new CplexEnergieBinaire(data);
		test.lancer();
	}
}
