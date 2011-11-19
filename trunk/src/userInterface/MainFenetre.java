package userInterface;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelCharbon;
	private JLabel labelJour;
	private JTextField fpChar;
	private JLabel labelNucleaire;
	private JTextField fpNucleaire;
	private JLabel labelFioul;
	private JTextField fpFioul;
	private JLabel labelGaz;
	private JTextField fpGaz;
	private JLabel labelMinReservoir;
	private JTextField maxRes;
	private JTextField initRes;
	private JPanel panel;
	private JTextField minRes;
	private JLabel labelMaxReservoir;
	private JLabel labelInitReservoir;
	private JTextField nbJours;
	private JButton scenariiReservoir;
	private JButton scenariiCentrales;

	public MainFenetre() {
		super();

		build();// On initialise notre fenêtre
	}

	private void build() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu1 = new JMenu("Menu");

		JMenuItem suivant = new JMenuItem(new AjoutScenarii(this, "Suivant"));
		menu1.add(suivant);

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

		setTitle("Calculatrice"); // On donne un titre à l'application
		setSize(800, 600); // On donne une taille à notre fenêtre
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
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);

		GridLayout layout = new GridLayout(14, 2);
		panel.setLayout(layout);
		labelJour = new JLabel("nombre de jours :");
		panel.add(labelJour);
		nbJours = new JTextField();
		nbJours.setColumns(4);
		panel.add(nbJours);
		panel.add(new JLabel());
		panel.add(new JLabel());

		labelCharbon = new JLabel("centrale à charbon puissance (MW) :");
		panel.add(labelCharbon);
		fpChar = new JTextField();
		fpChar.setColumns(4);
		panel.add(fpChar);
		
		labelNucleaire = new JLabel(
				"centrale nucléaire puissance (MW) :");
		panel.add(labelNucleaire);
		fpNucleaire = new JTextField();
		fpNucleaire.setColumns(4);
		panel.add(fpNucleaire);

		labelFioul = new JLabel("centrale au fioul puissance (MW) :");
		panel.add(labelFioul);
		fpFioul = new JTextField();
		fpFioul.setColumns(4);
		panel.add(fpFioul);

		labelGaz = new JLabel("centrale au gaz puissance (MW) :");
		panel.add(labelGaz);
		fpGaz = new JTextField();
		fpGaz.setColumns(4);
		panel.add(fpGaz);

		panel.add(new JLabel());
		panel.add(new JLabel());
		labelMinReservoir = new JLabel(
				"reservoir hydraulique volume minimal :");
		panel.add(labelMinReservoir);
		minRes = new JTextField();
		minRes.setColumns(4);
		panel.add(minRes);
		labelMaxReservoir = new JLabel("volume maximal :");
		panel.add(labelMaxReservoir);
		maxRes = new JTextField();
		maxRes.setColumns(4);
		panel.add(maxRes);
		labelInitReservoir = new JLabel("volume initial :");
		panel.add(labelInitReservoir);
		initRes = new JTextField();
		initRes.setColumns(4);
		panel.add(initRes);


		panel.add(new JLabel());
		panel.add(new JLabel());
		scenariiCentrales = new JButton("scenarii des centrales");
		scenariiReservoir = new JButton("scenarii du réservoir");
		panel.add(scenariiReservoir);
		panel.add(scenariiCentrales);
		scenariiCentrales.setVisible(false);
		scenariiReservoir.setVisible(false);
		JButton suivant = new JButton(new AjoutScenarii(this,
				"suivant"));
		panel.add(suivant);

		return panel;
	}


	public JButton getScenariiReservoir() {
		return scenariiReservoir;
	}

	public JButton getScenariiCentrales() {
		return scenariiCentrales;
	}

	public void effaceOption1(){
		remove(labelCharbon);
		remove(labelJour);
		remove(fpChar);
		remove(labelNucleaire);
		remove(fpNucleaire);
		remove(labelFioul);
		remove(fpFioul);
		remove(labelGaz);
		remove(fpGaz);
		remove(labelMinReservoir);
		remove(maxRes);
		remove(minRes);
		remove(initRes);
		remove(labelMaxReservoir);
		remove(labelInitReservoir);
		remove(nbJours);
		
		repaint();
	}

	public JTextField getNbJours() {
		return nbJours;
	}

	public JTextField getMinRes() {
		return minRes;
	}


	public JPanel getPanel() {
		return panel;
	}

	public JTextField getMaxRes() {
		return maxRes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public JTextField getFpChar() {
		return fpChar;
	}

	public JTextField getFpNucleaire() {
		return fpNucleaire;
	}

	public JTextField getFpFioul() {
		return fpFioul;
	}

	public JTextField getFpGaz() {
		return fpGaz;
	}

	public JTextField getInitRes() {
		return initRes;
	}

	public JLabel getLabel() {
		return labelCharbon;
	}
}