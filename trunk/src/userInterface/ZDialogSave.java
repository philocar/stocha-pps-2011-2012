package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
 * Fenêtre de dialogue demandant à l'utilisateur d'entrer le nom du fichier dans lequel il souhaite sauvegarder les résultats
 */
public class ZDialogSave extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labSave;
	private JTextField save;
	private Parametres params = new Parametres();
	
	ArrayList<Integer> listeCentres;
	HashMap<Integer, ArrayList<Integer>> liste;
	double valFonctionObjectif;
	
	/**
	 * Constructeur
	 * 
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public ZDialogSave(JFrame parent, String title,  Parametres p) {
		super(parent, title);
		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.params = p;
		this.initComponent();
	}

	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent() {

		JPanel panSave = new JPanel();
		panSave.setBackground(Color.white);
		panSave.setPreferredSize(new Dimension(300, 40));
		save = new JTextField();
		save.setPreferredSize(new Dimension(150, 25));
		labSave = new JLabel("Chemin d'écriture :");
		panSave.add(labSave);
		panSave.add(save);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panSave);

		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JButton saveBouton = new JButton("Sauvegarder");
		JButton cancelBouton = new JButton("Annuler");

		saveBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fichier;
				
				//Le nom du fichier doit respecter une certaine norme 
				//Si rien n'est entré dans le champ un nom par défaut comprenant le nom du fichier de données utilisé est attribué
				if(save.getText().equals("") || save.getText().equals(".txt")){
					fichier = new String("save"+params.getNomFichier());
				}else{
					fichier = new String(save.getText());
				}
				if(!fichier.matches("^[a-zA-Z0-9.]*.txt$")){
					fichier = new String(fichier+".txt");
				}
				
				File f;
				FileWriter fw;
				try {
					f = new File(new String("Data/")+fichier);
					fw = new FileWriter(f);
					fw.write("valeur de la fonction objectif : "+valFonctionObjectif+"\n");
					
					
					fw.close();
					JOptionPane.showMessageDialog(null, "La sauvegarde des résultats a bien été faite dans "+fichier, "Infos", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		control.add(saveBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

}
