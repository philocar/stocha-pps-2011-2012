package userInterface;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class Optimise extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private MainFenetre fenetre;
		private Parametres params;
        
        public Optimise(MainFenetre fenetre, String texte, Parametres params){
                super(texte);
                this.fenetre = fenetre;
                this.params = params;
        }
        
        public void actionPerformed(ActionEvent e) { 
        	String methodeName = (String) fenetre.getChoixMethode().getSelectedItem();
        	if(methodeName.equals("recuit simulé")){
        		ZDialogParamRS zd = new ZDialogParamRS(fenetre, "Choix des paramètres", true, params);
				zd.setVisible(true);
        		
        	}
        	else if(methodeName.equals("modèle probabiliste")){
        		ZDialogParamDeter zd = new ZDialogParamDeter(fenetre, "Choix des paramètres", true, params);
				zd.setVisible(true);
        	}
        	
        	
        	
        } 
}
