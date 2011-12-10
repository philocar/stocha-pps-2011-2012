package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import manager.solveurs.RS.RSEnergie;
import data.DataBinaire;

/**
 * 
 * @author Fabien BINI & Nathanaël MASRI & Nicolas POIRIER
 * 
 *         Fenêtre de dialogue demandant à l'utilisateur d'entrer les paramètres
 *         à utiliser pour le traitement par RS
 */
public class ZDialogParamRS extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labIter, labTempF, labTaux, labDec;
	private JTextField iter, tempF, taux, dec;
	private RSEnergie solveur;
	private String fileName;
	private double probabilite;
	private JTextField proba;
	private JLabel labProba;

	/**
	 * Constructeur
	 * 
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public ZDialogParamRS(MainFenetre parent, String title, boolean modal,
			String fileName) {
		super(parent, title, modal);
		this.setSize(350, 310);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComponent();
		parent.setEtat(MainFenetre.State.FICHIER_CHOISI);
		parent.updateVisibility();
		solveur = null;
		this.fileName = fileName;
	}

	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent() {

		JPanel panIter = new JPanel();
		panIter.setBackground(Color.white);
		panIter.setPreferredSize(new Dimension(300, 40));
		iter = new JTextField();
		iter.setPreferredSize(new Dimension(50, 25));
		iter.setText("16384");
		labIter = new JLabel("Nombre d'itérations par palier :");
		panIter.add(labIter);
		panIter.add(iter);

		JPanel panTempF = new JPanel();
		panTempF.setBackground(Color.white);
		panTempF.setPreferredSize(new Dimension(300, 40));
		tempF = new JTextField();
		tempF.setPreferredSize(new Dimension(50, 25));
		tempF.setText("20000");
		labTempF = new JLabel("Température finale :");
		panTempF.add(labTempF);
		panTempF.add(tempF);

		JPanel panTaux = new JPanel();
		panTaux.setBackground(Color.white);
		panTaux.setPreferredSize(new Dimension(300, 40));
		taux = new JTextField();
		taux.setPreferredSize(new Dimension(50, 25));
		taux.setText("0.8");
		labTaux = new JLabel("Taux d'acceptation température initiale :");
		panTaux.add(labTaux);
		panTaux.add(taux);

		JPanel panDec = new JPanel();
		panDec.setBackground(Color.white);
		panDec.setPreferredSize(new Dimension(300, 40));
		dec = new JTextField();
		dec.setPreferredSize(new Dimension(50, 25));
		dec.setText("0.9");
		labDec = new JLabel("décroissance de la température :");
		panDec.add(labDec);
		panDec.add(dec);

		JPanel panProba = new JPanel();
		panProba.setBackground(Color.white);
		panProba.setPreferredSize(new Dimension(300, 40));
		proba = new JTextField();
		proba.setPreferredSize(new Dimension(50, 25));
		proba.setText("0.98");
		labProba = new JLabel("probabilité des scénarios :");
		panProba.add(labProba);
		panProba.add(proba);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panIter);
		content.add(panTempF);
		content.add(panTaux);
		content.add(panDec);
		content.add(panProba);

		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Annuler");

		// Création du bouton "OK" dont l'exécution vérifie que les paramètres
		// entrés sont corrects
		// S'ils ne le sont pas un message d'information est transmis à
		// l'utilisateur
		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rep = new String();
				boolean valide = true;

				if (!iter.getText().matches("^[1-9][0-9]*$")) {
					valide = false;
					rep += "le champ \"nombre d'itérations\" n'est pas valide.\n";
				}
				if (!tempF.getText().matches(
						"^([0-9]+)|([0-9]+.[0-9]+)|(.[0-9]+)$")) {
					valide = false;
					rep += "le champ \"température finale\" n'est pas valide.\n";
				} else {
					if (Double.parseDouble(tempF.getText()) == 0.0) {
						valide = false;
						rep += "le champ \"température finale\" n'est pas valide.\n";
					}
				}
				if (!taux.getText().matches("^(0?.[0-9]+)|(1)$")) {
					valide = false;
					rep += "le champ \"taux d'acceptation\" n'est pas valide.\n";

				} else {
					if (!(Double.parseDouble(taux.getText()) <= 1)) {
						valide = false;
						rep += "le champ \"taux d'acceptation\" n'est pas valide.\n";
					}
				}
				if (!dec.getText().matches("^0?.[0-9]+$")) {
					valide = false;
					rep += "le champ \"décroissance température\" n'est pas valide.\n";
				}
				try {
					probabilite = Double.parseDouble(proba.getText());
					if (probabilite > 1 || probabilite < 0) {
						valide = false;
						rep += "le champ \"probabilité des scénarios\" n'est pas valide.\n";
					}

				} catch (java.lang.NumberFormatException e) {
					valide = false;
					rep += "le champ \"probabilité des scénarios\" n'est pas valide.\n";
				}

				if (valide) {
					System.out.println("début lecture des données");
					DataBinaire data = new DataBinaire(0, fileName
							+ "Données_Recuit_demandes.csv", fileName
							+ "Données_Recuit_paliers1.csv", fileName
							+ "Données_Recuit_paliers2.csv", fileName
							+ "Données_Recuit_paliers3.csv", fileName
							+ "Données_Recuit_paliers4.csv", fileName
							+ "Données_Recuit_trajectoire_hydro.csv", fileName
							+ "Données_Recours_parametres_hydraulique.csv",
							fileName + "Données_Recours_capacite_max.csv");
					System.out.println("fin lecture des données");
					setVisible(false);
					solveur = new RSEnergie(data, Double.parseDouble(dec
							.getText()), Double.parseDouble(tempF.getText()),
							Integer.decode(iter.getText()), Double
									.parseDouble(taux.getText()), 10, 100);
					System.out.println("solveur construit");
				} else {
					JOptionPane.showMessageDialog(null, rep, "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

	public RSEnergie getSolveur() {
		return solveur;
	}

	public void setSolveur(RSEnergie solveur) {
		this.solveur = solveur;
	}
}
