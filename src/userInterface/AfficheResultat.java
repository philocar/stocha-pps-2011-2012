package userInterface;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import data.solution.Solution;
import data.solution.SolutionCentrale;
import data.solution.SolutionEnergie;


public class AfficheResultat extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private MainFenetre parent;
		private SolutionEnergie solution;
        
        public AfficheResultat(MainFenetre parent, String texte){
                super(texte);
                this.parent = parent;
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
