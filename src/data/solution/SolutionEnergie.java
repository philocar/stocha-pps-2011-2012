package data.solution;


/**
 * Cette classe représente le niveau d'énergie par centrale et par jour
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 */
public class SolutionEnergie {

	private double[][] energies;
	private double[][] energiesMax;

	public SolutionEnergie() {
		super();
		energies = null;
	}

	public void setEnergies(double[][] energies) {
		this.energies = energies;
	}
	
	
	public double[][] getEnergiesMax() {
		return energiesMax;
	}

	public void setEnergiesMax(double[][] energiesMax) {
		this.energiesMax = energiesMax;
	}

	/**
	 * Renvoie un tableau contenant les énérgies des différentes centrales
	 * 
	 * @return
	 */
	public double[][] getEnergies() {
		return energies;
	}

	
	public String toString(){
		
		String s = "";
		for(int i=0; i<energies[0].length; i++)
			s += "centrale 1 : "+energies[0][i]+"\t max : "+energiesMax[0][i]+"\n";
		for(int i=0; i<energies[1].length; i++)
			s += "centrale 2 : "+energies[1][i]+"\t max : "+energiesMax[1][i]+"\n";
		for(int i=0; i<energies[2].length; i++)
			s += "centrale 3 : "+energies[2][i]+"\t max : "+energiesMax[2][i]+"\n";
		for(int i=0; i<energies[3].length; i++)
			s += "centrale 4 : "+energies[3][i]+"\t max : "+energiesMax[3][i]+"\n";
		for(int i=0; i<energies[4].length; i++)
			s += "reservoir hydraulique : "+energies[4][i]+"\t max : "+energiesMax[4][i]+"\n";
		
		return s;
	}
}





