package userInterface;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.solution.Solution;
import data.solution.SolutionCentrale;

public class MainFenetre extends JFrame {
	/**
	 * 
	 */

	public enum State {
		INITIAL, FICHIER_CHOISI, CALCUL_EN_COURS, RESULTAT_CALCULE
	}

	private static final long serialVersionUID = 1L;

	private JLabel methodeText;
	private JComboBox choixMethode;
	private JMenuItem ouvrir;
	private JPanel panel;
	private MainFenetre me;
	private JLabel fileName;
	private State etat = State.INITIAL;
	private Solution solution;
	private Optimise optimiser;

	private ActionListener charger;
	private JButton enregistrer;
	private JButton resultat;
	private JLabel cout;
	private JButton suivant;

	private JLabel descriptionFichier;

	private HashMap<String, String> descriptionChoix;

	private AfficheResultat affichage;

	public MainFenetre() {
		super();
		etat = State.INITIAL;
		me = this;
		build();// On initialise notre fenêtre
	}

	private void build() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu1 = new JMenu("Menu");

		optimiser = new Optimise(this, "Suivant");
		JMenuItem suivant = new JMenuItem(optimiser);
		menu1.add(suivant);

		// Création du bouton de chargement de fichier
		ouvrir = new JMenuItem("Charger fichier");
		charger = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				me.etat = State.INITIAL;
				me.fileName.setText("");
				boolean directory = true;
				String methode = (String) me.choixMethode.getSelectedItem();
				if (methode.equals("modèle probabiliste"))
					directory = false;

				ZDialogCharger zd = new ZDialogCharger(me,
						"Fichier de données", true, directory);
				zd.setVisible(true);
			}
		};
		ouvrir.addActionListener(charger);
		// Le bouton de chargement de fichier est placé dans la barre de menu
		// dans l'onglet "fichier"
		menu1.add(ouvrir);
		menu1.addSeparator();

		JMenuItem quitter = new JMenuItem(new QuitterAction("Quitter"));
		menu1.add(quitter);

		menuBar.add(menu1);

		JMenu menu2 = new JMenu("?");

		JMenuItem aPropos = new JMenuItem(new AProposAction(this, "A propos"));
		menu2.add(aPropos);
		JMenuItem aide = new JMenuItem("aide");
		menu2.add(aide);
		aide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// L'aide est ouverte dans le navigateur par défaut de
					// l'utilisateur
					Desktop.getDesktop().browse(new URI("index.html"));
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Le fichier d'aide est introuvable", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} catch (URISyntaxException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"L'aide n'est pas disponible pour votre OS",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		menuBar.add(menu2);

		setJMenuBar(menuBar);

		setTitle("Programmation stochastique"); // On donne un titre à
												// l'application
		setSize(600, 500); // On donne une taille à notre fenêtre
		setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		setResizable(false); // On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à
														// l'application de se
														// fermer lors du clic
														// sur la croix
		setContentPane(buildContentPane());
	}

	private JPanel buildContentPane() {
		panel = new JPanel();
		panel.setBackground(Color.white);

		GridLayout layout = new GridLayout(3, 1);
		panel.setLayout(layout);
		JPanel premiereLigne = new JPanel();
		premiereLigne.setLayout(new GridLayout(1, 3));
		premiereLigne.setBackground(Color.white);
		JPanel troisiemeLigne = new JPanel();
		troisiemeLigne.setLayout(new GridLayout(1, 3));
		panel.add(premiereLigne);

		methodeText = new JLabel("methode de résolution : ");
		Object[] listeMethode = new Object[] { "modèle probabiliste",
				"modèle avec recours", "recuit simulé", "modèle binaire", "relaxation du binaire"};
		descriptionChoix = new HashMap<String, String>();
		descriptionChoix.put("modèle probabiliste",
				"Le fichier doit contenir le modèle probabiliste");
		descriptionChoix
				.put("modèle avec recours",
						"<HTML>Le répertoire doit contenir les fichiers avec les noms suivants : <br>"
								+ " Données_Recours.xls<br>"
								+ " Données_Recours_capacite_max.csv  <br>"
								+ " Données_Recours_parametres_hydraulique.csv <br>"
								+ " Données_Recours_scenarios_coeff_dispo_centrale<sub>i</sub>.csv &nbsp;&nbsp;&nbsp;<sub>i</sub> allant de 1 à 4<br>"
								+ " Données_Recours_scenarios_demande.csv <br>"
								+ "</HTML>");

		descriptionChoix
				.put("recuit simulé",
						"<HTML>Le répertoire doit contenir les fichiers avec les noms suivants : <br>"
								+ " Données_Recuit.xls<br>"
								+ " Données_Recuit_capacité.csv  <br>"
								+ " Données_Recuit_demandes.csv <br>"
								+ " Données_Recuit_paliers<sub>i</sub>.csv &nbsp;&nbsp;&nbsp;<sub>i</sub> allant de 1 à 4<br>"
								+ " Données_Recuit_parametres_hydro.csv<br>"
								+ " Données_Recuit_trajectoire_hydro.csv<br>"
								+ "</HTML>");

		descriptionChoix.put("relaxation du binaire",
				descriptionChoix.get("recuit simulé"));
		descriptionChoix.put("modèle binaire",
				descriptionChoix.get("recuit simulé"));

		descriptionFichier = new JLabel(
				descriptionChoix.get("modèle probabiliste"));
		choixMethode = new JComboBox(listeMethode);
		choixMethode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				me.descriptionFichier.setText(descriptionChoix.get(choixMethode
						.getSelectedItem()));
			}
		});

		JPanel configurePanel = new JPanel();
		GridLayout configureLayout = new GridLayout(2, 1);
		configurePanel.setLayout(configureLayout);
		configurePanel.setBackground(Color.white);
		premiereLigne.add(configurePanel);
		JPanel methodePanel = new JPanel();
		methodePanel.setBackground(Color.white);
		methodePanel.add(methodeText);
		methodePanel.add(choixMethode);
		configurePanel.add(methodePanel);
		JPanel fichierPanel = new JPanel();
		fichierPanel.setBackground(Color.white);
		premiereLigne.add(fichierPanel);
		JButton fileButton = new JButton("charger fichier");
		fileButton.addActionListener(charger);
		fichierPanel.add(fileButton);
		fileName = new JLabel();
		fichierPanel.add(fileName);

		JPanel suite = new JPanel();

		enregistrer = new JButton("enregister le résultat");
		enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ZDialogSave(me, "enregistrer le résultat")
						.setVisible(true);
			}
		});
		// enregistrer.setVisible(false);

		suite = new JPanel();
		suite.setBackground(Color.white);
		suite.add(enregistrer);
		premiereLigne.add(suite);

		suite = new JPanel();
		suite.add(descriptionFichier);
		suite.setBackground(Color.white);
		panel.add(suite);

		panel.add(troisiemeLigne);
		suite = new JPanel();
		suite.setBackground(Color.white);
		FlowLayout l = new FlowLayout();
		l.setHgap(30);
		suite.setLayout(l);
		troisiemeLigne.add(suite);
		affichage = new AfficheResultat("voir le résultat");
		resultat = new JButton(affichage);
		// resultat.setVisible(false);
		suite.add(resultat);

		suite = new JPanel();
		suite.setBackground(Color.white);
		suivant = new JButton(optimiser);
		suite.setBackground(Color.white);
		suite.add(suivant);
		troisiemeLigne.add(suite);

		suite = new JPanel();
		suite.setBackground(Color.white);
		cout = new JLabel("coût optimal : 89 083");
		suite.add(cout);
		troisiemeLigne.add(suite);

		updateVisibility();

		return panel;
	}

	public void updateVisibility() {

		switch (etat) {

		case FICHIER_CHOISI:
			suivant.setVisible(true);
			resultat.setVisible(false);
			enregistrer.setVisible(false);
			cout.setVisible(false);
			break;

		case RESULTAT_CALCULE:
			resultat.setVisible(true);
			enregistrer.setVisible(true);
			cout.setVisible(true);
			break;

		case INITIAL:
			suivant.setVisible(false);
			resultat.setVisible(false);
			enregistrer.setVisible(false);
			cout.setVisible(false);
			break;

		}

	}

	public JComboBox getChoixMethode() {
		return choixMethode;
	}

	// lire les données depuis un fichier
	public void chargerDonnees(String file) throws FileNotFoundException {
		fileName.setText(file);
		optimiser.setFileName(file);
		etat = State.FICHIER_CHOISI;
		suivant.setVisible(true);
	}

	public State getEtat() {
		return etat;
	}

	public void setEtat(State etat) {
		this.etat = etat;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
		affichage.setSolution( ((SolutionCentrale)solution).genererSolutionEnergie());
		cout.setText("coût "+(int)(solution.fonctionObjectif()) + " €");
		System.out.println("solution : "+solution);
	}

	public void setDescription(String text){
		descriptionFichier.setText(text);
	}
	
	public void cacherResultat(){
		resultat.setVisible(false);		
	}
	
}