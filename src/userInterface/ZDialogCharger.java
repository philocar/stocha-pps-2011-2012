package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 *
 * Fenêtre de dialogue demandant à l'utilisateur d'entrer le nom du fichier de données
 */
public class ZDialogCharger extends JDialog {

	private static final long serialVersionUID = 1L;
	private MainFenetre parent;
	
	private JLabel labCharger;
	private JTextField charger;
	private String fileName;
	
	/**
	 * Constructeur
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public ZDialogCharger(MainFenetre parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		this.parent = parent;
		
	}
	
	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent(){

		JPanel panCharger = new JPanel();
		panCharger.setBackground(Color.white);
		panCharger.setPreferredSize(new Dimension(300, 40));
		charger = new JTextField();
		charger.setPreferredSize(new Dimension(150, 25));
		labCharger = new JLabel("Chemin d'accès :");
		panCharger.add(labCharger);
		panCharger.add(charger);
		
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panCharger);
		
		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JButton chargerBouton = new JButton("Charger");
		JButton cancelBouton = new JButton("Annuler");
		
		chargerBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileName = "Data/"+charger.getText(); 
					File file = new File(fileName);
			       
					parent.chargerDonnees(fileName);
					setVisible(false);
					
				} catch (IllegalFormatException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Le fichier est introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
		
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		control.add(chargerBouton);
		control.add(cancelBouton);
		
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	
}
