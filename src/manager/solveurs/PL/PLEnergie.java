package manager.solveurs.PL;

import manager.solveurs.Solveur;

import data.Data;
import data.solution.SolutionEnergie;
import data.solution.SolutionEnergieBinaire;

/**
 * Cette classe permet d'écrire un programme linéaire à partir de données pour le problème de management de la production d'énergie.
 * Ce PL est utilisé par un solveur de programmes linéaires.
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 */
public abstract class PLEnergie implements Solveur {

	/** Variable contenant les données du problème */
	protected Data donnees;
	/** Le vecteur de solution du problème. La production de chaque centrale par période en MW */
	protected double[] solution;
	/** Le vecteur des couts de production de l'énergie en €/MW. */
	protected double[] couts;
	/** Pour chaque scénario il y a un vecteur d'énergie achetée par période en MW */
	protected double[][] energieAchetee;
	/** Pour chaque scénario il y a un vecteur d'énergie vendue par période en MW */
	protected double[][] energieVendue;
	/** Le prix du MW acheté */
	protected double prixAchete;
	/** Le prix du MW vendu */
	protected double prixVente;
	/** Pour chaque scénario il y a un vecteur des demandes par période */
	protected double[][] demandes;
	/** Pour chaque scénario il y a un vecteur, toujours le même, pour la partie droite des contraintes sur les productions */
	protected double[] vecteurContraintesMembreDroit;
	/** Pour chaque scénario il y a une matrice, toujours la même, pour la partie gauche des contraintes sur les productions */
	protected int[][] matriceContraintesMembreGauche;
	/** Pour chaque scénario il y a un vecteur de disponibilité par période (entre 0 et 1) */
	protected double[][] matriceDisponibilite;
	
	/**
	 * Crée un nouveau PLEnergie.
	 * @param donnees les données du problème
	 */
	public PLEnergie(Data donnees)
	{
		
	}

	/**
	 * Génère le programme linéaire de la p-mediane à partir des données.
	 * Remplit les tableaux du problème.
	 */
	private void genererPL()
	{
		
	}
	
	/**
	 * Renvoie la solution calculée.
	 * @return la solution calculée
	 */
	public SolutionEnergie getSolution() {
		return null;
	}
	
	public abstract void lancer();
}
