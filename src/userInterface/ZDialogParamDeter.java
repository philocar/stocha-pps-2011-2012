package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
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
 *         Fenêtre de dialogue demandant à l'utilisateur d'entrer les paramètres
 *         à utiliser pour le traitement par RS
 */
public class ZDialogParamDeter extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tempF, taux, dec, fichier;
	private Parametres params = new Parametres();
	private MainFenetre parent;

	/**
	 * Constructeur
	 * 
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public ZDialogParamDeter(MainFenetre parent, String title, boolean modal,
			Parametres p) {
		super(parent, title, modal);
		this.parent = parent;
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComponent();
		this.params = p;
		parent.setEtat(MainFenetre.State.FICHIER_CHOISI);
		parent.updateVisibility();
	}

	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent() {

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(new JLabel(
				"<html><br>Cette méthode de résolution utilise matlab.<br>"
						+ "Un fichier avec des données compréhensibles par matlab va être généré."
						+ "<br><br>Vous pourrez ensuite charger ce fichier sous matlab<br>"
						+ "</html>"));

		fichier = new JTextField(35);
		JPanel panelFile = new JPanel();
		panelFile.setBackground(Color.white);
		panelFile.add(new JLabel("fichier : "));
		panelFile.add(fichier);
		content.add(panelFile);

		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Annuler");
		JButton explorer = new JButton("explorer");

		explorer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser chooser = new JFileChooser();

				int returnVal = chooser.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fichier.setText(chooser.getSelectedFile().getAbsolutePath());
				}

			}
		});

		// Création du bouton "OK" dont l'exécution vérifie que les paramètres
		// entrés sont corrects
		// S'ils ne le sont pas un message d'information est transmis à
		// l'utilisateur
		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String fileName = fichier.getText();
					File file = new File(fileName);
					if (!file.exists()) {
						JOptionPane.showMessageDialog(null,
								"Le fichier est introuvable", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(parent,
								"Le fichier a bien été sauvegardé");
						setVisible(false);
					}

				} catch (IllegalFormatException e) {
					e.printStackTrace();
				}
			}
		});

		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				params.defaut();
				setVisible(false);
			}
		});
		control.add(explorer);
		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
