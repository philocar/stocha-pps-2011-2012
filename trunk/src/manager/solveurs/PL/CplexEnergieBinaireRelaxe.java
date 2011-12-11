package manager.solveurs.PL;

import ilog.concert.IloException;
import ilog.concert.IloIntExpr;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;
import data.DataBinaire;
import data.ScenarioBinaire;

/**
 * Cette classe permet de résoudre une instance du problème de management de la production d'énergie à l'aide de CPLEX
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class CplexEnergieBinaireRelaxe extends PLEnergieBinaireRelaxe
{
        /**
         * Crée un nouveau CPlexEnergie
         * 
         * @param donnees les données du problème
         */
        public CplexEnergieBinaireRelaxe(DataBinaire donnees)
        {
                super(donnees);
        }

        /**
         * Lance la résolution du programme linéaire.
         * Crée le programme à partir des tableaux de coefficients.
         */
        public void lancer()
        {
                try {
                        IloCplex cplex = new IloCplex();
                        IloIntVar[] y = cplex.boolVarArray(donnees.nbPeriodes*(donnees.nbPaliers[0]+donnees.nbPaliers[1]+donnees.nbPaliers[2]+donnees.nbPaliers[3])+donnees.nbTrajectoires);
                        IloIntVar[] z = cplex.boolVarArray(donnees.nbScenarios);
                        
                        int ythetaSize = donnees.nbPeriodes*(donnees.nbPaliers[0]+donnees.nbPaliers[1]+donnees.nbPaliers[2]+donnees.nbPaliers[3]);
                        int ynSize = donnees.nbTrajectoires;
                        int nbPaliers = donnees.nbPaliers[0]+donnees.nbPaliers[1]+donnees.nbPaliers[2]+donnees.nbPaliers[3];
                        IloIntVar[] ytheta = cplex.boolVarArray(ythetaSize);
                        IloIntVar[] yn = cplex.boolVarArray(ynSize);
                        for(int i = 0; i < ythetaSize; i++)
                        {
                                ytheta[i] = y[i];
                        }
                        for(int i = 0; i < ynSize; i++)
                        {
                                yn[i] = y[i+ythetaSize];
                        }
                        
                        double M = 200000;
                        
                        cplex.addMinimize(cplex.scalProd(couts, y));
                        
//                      // Un seul pallier accepté par période et par centrale
//                      for(int p = 0; p < donnees.nbPeriodes; p++)
//                      {
//                              int sommePalliers = 0;
//                              for(int c = 0; c < donnees.nbCentrales; c++)
//                              {
//                                      IloIntVar[] varY = new IloIntVar[donnees.nbPaliers[c]];
//                                      for(int numPallier = 0; numPallier < donnees.nbPaliers[c]; numPallier++)
//                                      {
//                                              varY[numPallier] = ytheta[p*nbPaliers + sommePalliers];
//                                              sommePalliers++;
//                                      }
//                                      IloRange eq = cplex.eq(cplex.sum(varY), 1);
//                                      cplex.add(eq);
//                              }
//                      }
//                      
//                      // Une seule trajectoire est choisie
//                      IloRange eq = cplex.eq(cplex.sum(yn), 1);
//                      cplex.add(eq);
                        
                        
                        IloLinearNumExpr exprYn = cplex.scalProd(donnees.getTrajectoires(), yn);
                        for(int p = 0; p < donnees.nbPeriodes; p++)
                        {
                                IloNumVar[] varYt = new IloNumVar[nbPaliers];
                                
                                for(int i = 0; i < nbPaliers; i++)
                                {
                                        varYt[i] = ytheta[p*nbPaliers + i];
                                }

                                for(int s = 0; s < donnees.nbScenarios; s++)
                                {
                                        double[] prod = new double[nbPaliers];
                                        int sommePalliers = 0;
                                        for(int c = 0; c < donnees.nbCentrales; c++)
                                        {
                                                for(int numPallier = 0; numPallier < donnees.nbPaliers[c]; numPallier++)
                                                {
                                                        prod[sommePalliers] = donnees.getScenario(s).getPaliersPeriodeCentrale(c, p)[numPallier];
                                                        sommePalliers++;
                                                }
                                        }
                                        
                                        IloLinearNumExpr exprYt = cplex.scalProd(prod, varYt);
                                        
                                        IloRange ge = cplex.ge(cplex.sum(exprYt, exprYn, cplex.prod(-(donnees.getScenario(s).getDemandePeriode(p) + M), z[s])), -M);
                                        cplex.add(ge);
                                }
                        }
                                
                        if (cplex.solve())
                        {
                                System.out.println("Solution status = " + cplex.getStatus());
                                System.out.println("Solution value = " + cplex.getObjValue());
                                
                                
                                cplex.end();
                        }
                        
                } catch (IloException e) {
                        e.printStackTrace();
                }
        }
        
        public static void main(String[] args)
        {
                DataBinaire data = new DataBinaire(98, "Data/Données_Recuit_demandes.csv", "Data/Données_Recuit_paliers1.csv", "Data/Données_Recuit_paliers2.csv", "Data/Données_Recuit_paliers3.csv", "Data/Données_Recuit_paliers4.csv", "Data/Données_Recuit_trajectoire_hydro.csv", "Data/Données_Recuit_parametres_hydro.csv", "Data/Données_Recuit_capacité.csv");
                CplexEnergieBinaireRelaxe test = new CplexEnergieBinaireRelaxe(data);
                test.lancer();
                
        }
}
