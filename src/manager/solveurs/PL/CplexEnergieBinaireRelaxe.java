package manager.solveurs.PL;

import ilog.concert.IloException;
import ilog.concert.IloIntExpr;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import data.DataBinaire;
import data.ScenarioBinaire;

/**
 * Cette classe permet de résoudre une instance du problème de management de la
 * production d'énergie à l'aide de CPLEX
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 */
public class CplexEnergieBinaireRelaxe extends PLEnergieBinaireRelaxe {

	private String fileName;
	private static String ajout = "mn = m*n;\n"
			+ "	c = [cth w cth w cth w cth w cth w cth w cth w];\n"
			+ "	M=[amoy1(1) amoy2(1) amoy3(1) amoy4(1) rau; amoy1(2) amoy2(2) amoy3(2) amoy4(2) rau; amoy1(3) amoy2(3) amoy3(3) amoy4(3) rau; amoy1(4) amoy2(4) amoy3(4) amoy4(4) rau; amoy1(5) amoy2(5) amoy3(5) amoy4(5) rau; amoy1(6) amoy2(6) amoy3(6) amoy4(6) rau; amoy1(7) amoy2(7) amoy3(7) amoy4(7) rau];\n"
			+ "	Xmax=[x1max(1); x2max(1); x3max(1); x4max(1); x5max(1); x1max(2); x2max(2); x3max(2); x4max(2); x5max(2); x1max(3); x2max(3); x3max(3); x4max(3); x5max(3); x1max(4); x2max(4); x3max(4); x4max(4); x5max(4); x1max(5); x2max(5); x3max(5); x4max(5); x5max(5); x1max(6); x2max(6); x3max(6); x4max(6); x5max(6); x1max(7); x2max(7); x3max(7); x4max(7); x5max(7)];\n"
			+ "	q1=[v0+ah(1)-vmin(1); v0+ah(2)-vmin(2); v0+ah(3)-vmin(3); v0+ah(4)-vmin(4); v0+ah(5)-vmin(5); v0+ah(6)-vmin(6); v0+ah(7)-vmin(7)];\n"
			+ "	q2=[v0+ah(1)-vmax(1); v0+ah(2)-vmax(2); v0+ah(3)-vmax(3); v0+ah(4)-vmax(4); v0+ah(5)-vmax(5); v0+ah(6)-vmax(6); v0+ah(7)-vmax(7)];\n"
			+ "	H=tril(ones(m,m));\n"
			+ "	\n"
			+ "	A1=[siga1(1) 0 0 0 0; 0 siga2(1) 0 0 0; 0 0 siga3(1) 0 0; 0 0 0 siga4(1) 0; 0 0 0 0 0; 0 0 0 0 0];\n"
			+ "	b1=[0 0 0 0 0 sigb(1)]';\n"
			+ "	f1=M(1,1:n)/phinv(1);\n"
			+ "	d1=-bmoy(1)/phinv(1);\n"
			+ "	\n"
			+ "	A2=[siga1(2) 0 0 0 0; 0 siga2(2) 0 0 0; 0 0 siga3(2) 0 0; 0 0 0 siga4(2) 0; 0 0 0 0 0; 0 0 0 0 0];\n"
			+ "	b2=[0 0 0 0 0 sigb(2)]';\n"
			+ "	f2=M(2,1:n)/phinv(2);\n"
			+ "	d2=-bmoy(2)/phinv(2);\n"
			+ "	\n"
			+ "	A3=[siga1(3) 0 0 0 0; 0 siga2(3) 0 0 0; 0 0 siga3(3) 0 0; 0 0 0 siga4(3) 0; 0 0 0 0 0; 0 0 0 0 0];\n"
			+ "	b3=[0 0 0 0 0 sigb(3)]';\n"
			+ "	f3=M(3,1:n)/phinv(3);\n"
			+ "	d3=-bmoy(3)/phinv(3);\n"
			+ "	\n"
			+ "	A4=[siga1(4) 0 0 0 0; 0 siga2(4) 0 0 0; 0 0 siga3(4) 0 0; 0 0 0 siga4(4) 0; 0 0 0 0 0; 0 0 0 0 0];\n"
			+ "	b4=[0 0 0 0 0 sigb(4)]';\n"
			+ "	f4=M(4,1:n)/phinv(4);\n"
			+ "	d4=-bmoy(4)/phinv(4);\n"
			+ "	\n"
			+ "	A5=[siga1(5) 0 0 0 0; 0 siga2(5) 0 0 0; 0 0 siga3(5) 0 0; 0 0 0 siga4(5) 0; 0 0 0 0 0; 0 0 0 0 0];\n"
			+ "	b5=[0 0 0 0 0 sigb(5)]';\n"
			+ "	f5=M(5,1:n)/phinv(5);\n"
			+ "	d5=-bmoy(5)/phinv(5);\n"
			+ "	\n"
			+ "	A6=[siga1(6) 0 0 0 0; 0 siga2(6) 0 0 0; 0 0 siga3(6) 0 0; 0 0 0 siga4(6) 0; 0 0 0 0 0; 0 0 0 0 0];\n"
			+ "	b6=[0 0 0 0 0 sigb(6)]';\n"
			+ "	f6=M(6,1:n)/phinv(6);\n"
			+ "	d6=-bmoy(6)/phinv(6);\n"
			+ "	\n"
			+ "	A7=[siga1(7) 0 0 0 0; 0 siga2(7) 0 0 0; 0 0 siga3(7) 0 0; 0 0 0 siga4(7) 0; 0 0 0 0 0; 0 0 0 0 0];\n"
			+ "	b7=[0 0 0 0 0 sigb(7)]';\n" + "	f7=M(7,1:n)/phinv(7);\n"
			+ "	d7=-bmoy(7)/phinv(7);\n" + "	\n" + "	\n" + "	cvx_begin\n"
			+ "	    variable x(mn)\n" + "	    minimize (c*x)\n"
			+ "	    subject to\n"
			+ "	    x5=[x(n) x(2*n) x(3*n) x(4*n) x(5*n) x(6*n) x(7*n)]';\n"
			+ "	    norm(A1*x(1:n)+b1,2) <= f1*x(1:n)+d1;\n"
			+ "	    norm(A2*x(n+1:2*n)+b2,2) <= f2*x(n+1:2*n)+d2;\n"
			+ "		norm(A3*x(2*n+1:3*n)+b3,2) <= f3*x(2*n+1:3*n)+d3;\n"
			+ "		norm(A4*x(3*n+1:4*n)+b4,2) <= f4*x(3*n+1:4*n)+d4;\n"
			+ "		norm(A5*x(4*n+1:5*n)+b5,2) <= f5*x(4*n+1:5*n)+d5;\n"
			+ "		norm(A6*x(5*n+1:6*n)+b6,2) <= f6*x(5*n+1:6*n)+d6;\n"
			+ "		norm(A7*x(6*n+1:7*n)+b7,2) <= f7*x(6*n+1:7*n)+d7;\n"
			+ "	    x>=0;\n" + "	    x<=Xmax;\n" + "	    H*x5 <= q1;\n"
			+ "	    H*x5 >= q2;\n" + "	cvx_end ";

	/**
	 * Crée un nouveau CPlexEnergie
	 * 
	 * @param donnees
	 *            les données du problème
	 * @param fileName
	 */
	public CplexEnergieBinaireRelaxe(DataBinaire donnees, String fileName) {
		super(donnees);
		System.out.println("solveur relaxe");
		this.fileName = fileName;
	}

	/**
	 * Lance la résolution du programme linéaire. Crée le programme à partir des
	 * tableaux de coefficients.
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
			
//			// Un seul pallier accepté par période et par centrale
//			for(int p = 0; p < donnees.nbPeriodes; p++)
//			{
//				int sommePalliers = 0;
//				for(int c = 0; c < donnees.nbCentrales; c++)
//				{
//					IloIntVar[] varY = new IloIntVar[donnees.nbPaliers[c]];
//					for(int numPallier = 0; numPallier < donnees.nbPaliers[c]; numPallier++)
//					{
//						varY[numPallier] = ytheta[p*nbPaliers + sommePalliers];
//						sommePalliers++;
//					}
//					IloRange eq = cplex.eq(cplex.sum(varY), 1);
//					cplex.add(eq);
//				}
//			}
//			
//			// Une seule trajectoire est choisie
//			IloRange eq = cplex.eq(cplex.sum(yn), 1);
//			cplex.add(eq);
			
			
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
		CplexEnergieBinaireRelaxe test = new CplexEnergieBinaireRelaxe(data, "Data");
		test.lancer();
		
	}

	private void genererFichier(String file) {
		// lecture des informations

		String contenu = "";
		try {
			System.out.println("lecture");
			File f = new File(fileName
					+ "Données_Modèle_Probabiliste_Variables_Réelles.m");
			Scanner sc;

			sc = new Scanner(f);

			while (sc.hasNext()) {
				contenu += sc.nextLine() + "\n";
			}
			contenu += ajout;
			System.out.println(contenu + "\n fin lecture");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// écriture des données
		FileWriter writer = null;
		try {
			writer = new FileWriter(file, false);
			writer.write(contenu, 0, contenu.length());
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
