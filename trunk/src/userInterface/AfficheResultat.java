package userInterface;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import data.solution.Solution;


public class AfficheResultat extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private MainFenetre parent;
		private Solution solution;
        
        public AfficheResultat(MainFenetre parent, String texte, Solution solution){
                super(texte);
                this.parent = parent;
                this.solution = solution;
        }
        
        public void actionPerformed(ActionEvent e) { 
        	
        	FenetreSolution affichage = new FenetreSolution();
        	affichage.setVisible(true);
        	
        	
        	
        } 
}
