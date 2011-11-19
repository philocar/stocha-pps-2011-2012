package userInterface;

import javax.swing.SwingUtilities;

public class User {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainFenetre fenetre = new MainFenetre();
				fenetre.setVisible(true);
			}
		});
	}
}