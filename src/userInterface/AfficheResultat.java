package userInterface;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import data.solution.SolutionEnergie;


public class AfficheResultat extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
		private SolutionEnergie solution;
        
        public AfficheResultat(String texte){
                super(texte);
                solution = null;
        }
        
        public SolutionEnergie getSolution() {
			return solution;
		}

		public void setSolution(SolutionEnergie solution) {
			this.solution = solution;
		}

		public void actionPerformed(ActionEvent e) { 
        	
        	FenetreSolution affichage = new FenetreSolution(solution);
        	affichage.setVisible(true);
        	
        } 
}
