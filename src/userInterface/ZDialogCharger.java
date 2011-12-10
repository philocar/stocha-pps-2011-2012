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
import javax.swing.JFileChooser;
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
	private boolean directoryOnly;
	
	/**
	 * Constructeur
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public ZDialogCharger(MainFenetre parent, String title, boolean modal, boolean directoryOnly){
		super(parent, title, modal);
		this.setSize(400, 180);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComponent();
		this.parent = parent;
		this.directoryOnly = directoryOnly;
		
	}
	
	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent(){

		JPanel panCharger = new JPanel();
		panCharger.setBackground(Color.white);
		charger = new JTextField(25);
		labCharger = new JLabel("fichier :");
		panCharger.add(labCharger);
		panCharger.add(charger);
		
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panCharger);
		JButton explorer = new JButton("explorer");
		content.add(explorer);
		
		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JButton chargerBouton = new JButton("Charger");
		JButton cancelBouton = new JButton("Annuler");
		
		explorer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				if(directoryOnly)
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					charger.setText(chooser.getSelectedFile().getAbsolutePath());
				}

			}
		});
		
		chargerBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileName = charger.getText(); 
					File file = new File(fileName);
					if ( !file.exists() ) {
						JOptionPane.showMessageDialog(null, "Le fichier est introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					else
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
