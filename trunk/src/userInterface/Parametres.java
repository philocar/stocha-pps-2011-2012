package userInterface;

/**
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 *
 * Classe dont l'objet créé permet d'avoir une trace de tous les paramètres à utiliser durant le traitement
 */
public class Parametres {
	
	/*
	 * pour RS 
	 * rsIter : nombre d'itérations par pallier 
	 * rsTempF : température finale
	 * rsTaux : taux d'acceptation de la température initiale
	 * rsDec : décroissance de la température
	 * 
	 * pour VNS
	 * vnsIter : nombre d'itérations par pallier 
	 * vnsKVoisGVNS : nombre maximale de voisinage pour GVNS
	 * vndActif : utilisation ou non de VND
	 * 
	 * choixMethode : permet de savoir quelle méthode doit être utilisée pour le traitement
	 * nomFichier : le nom du fichier contenant les données du problème
	 */
	private int rsIter;
	private double rsTempF, rsTaux, rsDec;
	private int vnsIter, vnsKVoisGVNS;
	private boolean vndActif;
	private boolean actif;
	private int choixMethode;
	private String nomFichier;
	
	public Parametres(){
		nomFichier = new String("fichier.txt");
		defaut();
	}

	/**
	 * Méthode permettant de donner des valeurs par défaut à l'ensemble des paramètres
	 */
	public void defaut(){
		rsIter = 10;
		rsTempF = 0.01;
		rsTaux = 0.8;
		rsDec = 0.9;
		vnsIter = 10;
		vnsKVoisGVNS = 6;
		vndActif = false;
		actif = false;
		choixMethode = 1;
	}
	
	public void activer(){
		this.actif = true;
	}
	
	public boolean isActif() {
		return actif;
	}

	public int getRsIter() {
		return rsIter;
	}

	public void setRsIter(int rsIter) {
		this.rsIter = rsIter;
	}

	public double getRsTempF() {
		return rsTempF;
	}

	public void setRsTempF(double rsTempF) {
		this.rsTempF = rsTempF;
	}

	public double getRsTaux() {
		return rsTaux;
	}

	public void setRsTaux(double rsTaux) {
		this.rsTaux = rsTaux;
	}

	public double getRsDec() {
		return rsDec;
	}

	public void setRsDec(double rsDec) {
		this.rsDec = rsDec;
	}

	public int getVnsIter() {
		return vnsIter;
	}

	public void setVnsIter(int vnsIter) {
		this.vnsIter = vnsIter;
	}

	public int getVnsKVoisGVNS() {
		return vnsKVoisGVNS;
	}

	public void setVnsKVoisGVNS(int vnsKVoisGVNS) {
		this.vnsKVoisGVNS = vnsKVoisGVNS;
	}

	public boolean isVndActif() {
		return vndActif;
	}

	public void setVndActif(boolean vndActif) {
		this.vndActif = vndActif;
	}

	public int getChoixMethode() {
		return choixMethode;
	}

	public void setChoixMethode(int choixMethode) {
		this.choixMethode = choixMethode;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

}
