package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * Fenêtre de dialogue demandant à l'utilisateur d'entrer les paramètres à utiliser pour le traitement par RS
 */
public class ZDialogParamRS extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labIter, labTempF, labTaux, labDec;
	private JTextField iter, tempF, taux, dec;
	private Parametres params = new Parametres();

	/**
	 * Constructeur
	 * 
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public ZDialogParamRS(JFrame parent, String title, boolean modal,
			Parametres p) {
		super(parent, title, modal);
		this.setSize(350, 260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComponent();
		this.params = p;
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
		iter.setText((new Integer(params.getRsIter())).toString());
		labIter = new JLabel("Nombre d'itérations par palier :");
		panIter.add(labIter);
		panIter.add(iter);

		JPanel panTempF = new JPanel();
		panTempF.setBackground(Color.white);
		panTempF.setPreferredSize(new Dimension(300, 40));
		tempF = new JTextField();
		tempF.setPreferredSize(new Dimension(50, 25));
		tempF.setText((new Double(params.getRsTempF())).toString());
		labTempF = new JLabel("Température finale :");
		panTempF.add(labTempF);
		panTempF.add(tempF);

		JPanel panTaux = new JPanel();
		panTaux.setBackground(Color.white);
		panTaux.setPreferredSize(new Dimension(300, 40));
		taux = new JTextField();
		taux.setPreferredSize(new Dimension(50, 25));
		taux.setText((new Double(params.getRsTaux())).toString());
		labTaux = new JLabel("Taux d'acceptation température initiale :");
		panTaux.add(labTaux);
		panTaux.add(taux);

		JPanel panDec = new JPanel();
		panDec.setBackground(Color.white);
		panDec.setPreferredSize(new Dimension(300, 40));
		dec = new JTextField();
		dec.setPreferredSize(new Dimension(50, 25));
		dec.setText((new Double(params.getRsDec())).toString());
		labDec = new JLabel("décroissance de la température :");
		panDec.add(labDec);
		panDec.add(dec);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panIter);
		content.add(panTempF);
		content.add(panTaux);
		content.add(panDec);

		JPanel control = new JPanel();
		control.setBackground(Color.white);
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Annuler");

		//Création du bouton "OK" dont l'exécution vérifie que les paramètres entrés sont corrects
		//S'ils ne le sont pas un message d'information est transmis à l'utilisateur 
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
				}else{
					if(Double.parseDouble(tempF.getText()) == 0.0){
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
				
				if(valide){
					setVisible(false);
					params.setRsIter(Integer.decode(iter.getText()));
					params.setRsTempF(Double.parseDouble(tempF.getText()));
					params.setRsTaux(Double.parseDouble(taux.getText()));
					params.setRsDec(Double.parseDouble(dec.getText()));
					params.setChoixMethode(2);
					params.activer();
				}else{
					JOptionPane.showMessageDialog(null, rep, "Erreur", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				params.defaut();
				setVisible(false);
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
