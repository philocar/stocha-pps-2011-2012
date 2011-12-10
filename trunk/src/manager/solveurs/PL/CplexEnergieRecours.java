package manager.solveurs.PL;

import java.awt.DisplayMode;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;
import data.DataRecours;
import data.ScenarioRecours;

/**
 * Cette classe permet de résoudre une instance du problème de management de la production d'énergie à l'aide de CPLEX
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class CplexEnergieRecours extends PLEnergieRecours 
{
	/**
	 * Crée un nouveau CPlexEnergie
	 * 
	 * @param donnees les données du problème
	 */
	public CplexEnergieRecours(DataRecours donnees)
	{
		super(donnees);
	}

	/**
	 * Lance la résolution du programme linéaire.
	 * Crée le programme à partir des tableaux de coefficients.
	 */
	public void lancer()
	{
		// min couts * x + probabilitesPrix * y
		// une contrainte par scénario et par période pour l'offre et la demande
		
		try {
			IloCplex cplex = new IloCplex();
			double[] lb1 = new double[7*5];
			double[] ub1 = new double[7*5];
			double[] lb2 = new double[7*100];
			double[] ub2 = new double[7*100];
			double[] qplus = new double[7*100];
			double[] qmoins = new double[7*100];
			for(int p = 0; p < 7; p++)
			{
				for(int c = 0; c < 5; c++)
				{
					lb1[p*5 + c] = 0.0;
					ub1[p*5 + c] = donnees.getProductionMaxCentrale(c, p);
				}
			}
			for(int p = 0; p < 7; p++)
			{
				for(int s = 0; s < 100; s++)
				{
					lb2[p*100 + s] = 0.0;
					ub2[p*100 + s] = Double.MAX_VALUE;
					qplus[p*100 + s] = 100*0.01;
					qmoins[p*100 + s] = 200*0.01;
				}
			}
			
			IloNumVar[] x = cplex.numVarArray(5*7, lb1, ub1);
			IloNumVar[] yp = cplex.numVarArray(7*100, lb2, ub2);
			IloNumVar[] ym = cplex.numVarArray(7*100, lb2, ub2);
			
			IloLinearNumExpr cx = cplex.scalProd(couts, x);
			IloLinearNumExpr cyp = cplex.scalProd(qplus, yp);
			IloLinearNumExpr cym = cplex.scalProd(qmoins, ym);
			cplex.addMinimize(cplex.sum(cx, cyp, cym));
			
			double sommeApports = 0;
			for(int p = 0; p < 7; p++)
			{
				for(int s = 0; s < 100; s++)
				{
					IloNumVar[] x2 = new IloNumVar[4];
					double[] f = new double[4];
					for(int c = 0; c < 4; c++)
					{
						f[c] = facteursDisponibilite[s][p][c];
						x2[c] = x[p*5 + c];
					}
					IloLinearNumExpr fx2 = cplex.scalProd(f, x2);
					
					IloRange eq = cplex.eq(cplex.sum(fx2, cplex.prod(donnees.getTurbinage(), x[p*5+4]), cplex.prod(1, yp[p*100 + s]), cplex.prod(-1, ym[p*100 + s])), donnees.getScenario(s).getDemandePeriode(p));
					cplex.add(eq);
				}
			
				sommeApports += donnees.getApportsPeriode(p);
				//System.out.println("apport :  "+sommeApports);
				IloNumVar[] xi5 = new IloNumVar[p+1];
				double[] un = new double[p+1];
				for(int ip = 0; ip <= p; ip++)
				{
					xi5[ip] = x[ip*5+4];
					un[ip] = 1;
				}
				IloLinearNumExpr exprxi5 = cplex.scalProd(un, xi5);
				IloRange le = cplex.le(exprxi5, donnees.getVolumeInitial() + sommeApports - donnees.getVolumeMin(p));
				IloRange ge = cplex.ge(exprxi5, donnees.getVolumeInitial() + sommeApports - donnees.getVolumeMax(p));
				cplex.add(le);
				cplex.add(ge);
			}
			
			if (cplex.solve())
			{
				System.out.println("Solution status = " + cplex.getStatus());
				System.out.println("Solution value = " + cplex.getObjValue());
				double[] val = cplex.getValues(x);
				
//				// Si la variable xii est à 1 alors i est un centre
//				for(int i=0; i<donnees.getNbEntites(); i++)
//				{
//					for(int j=0; j<donnees.getNbEntites(); j++)
//					{
//						if(i == j && val[i*donnees.getNbEntites()+j] > 0)
//						{
//							solution.setCentre(true, j);
//						}
//					}
//				}
				
				cplex.end();
			}
			
		} catch (IloException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		DataRecours data = new DataRecours("Data/Données_Recours_parametres_hydraulique.csv", "Data/Données_Recours_capacite_max.csv", "Data/Données_Recours_scenarios_demande.csv", "Data/Données_Recours_scenarios_coeff_dispo_centrale1.csv", "Data/Données_Recours_scenarios_coeff_dispo_centrale2.csv", "Data/Données_Recours_scenarios_coeff_dispo_centrale3.csv", "Data/Données_Recours_scenarios_coeff_dispo_centrale4.csv");
		CplexEnergieRecours test = new CplexEnergieRecours(data);
		test.lancer();
	}
}
