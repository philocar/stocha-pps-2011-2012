package userInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.solution.SolutionEnergie;

public class FenetreSolution extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private double[][] points;
	private double max[];
	private SolutionEnergie solution;
	
	public FenetreSolution(SolutionEnergie solution) {
		super();
		this.solution = solution;
		System.out.println(solution);
		build();// On initialise notre fenêtre
	}


	private void build() {
		setTitle("solutions"); // On donne un titre à l'application
		setSize(800, 600); // On donne une taille à notre fenêtre
		setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		setResizable(true); // On interdit la redimensionnement de la fenêtre
		
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane() {
		panel = new JPanel();
		
		GridLayout layout = new GridLayout(3, 2);
		layout.setHgap(10);
		layout.setVgap(7);
		panel.setLayout(layout);
		
		DessinCentrale centrale = new DessinCentrale();
		// points y Ã  dessiner autour de la courbe, x allant de 0 à max.
	//*
		double[] nu = {  200 , 100 , 400 , 0 , 200 , 300 , 100, 1000 };
		points = new double[5][];
		points[0] = nu;
		max = new double[5];
		max[0] = 1000;
	//	centrale.setNuage(points[0], max[0]);
		//*/
		centrale.setNuage(solution.getEnergies()[0], solution.getEnergiesMax()[0]);
	
		JPanel centralePanel = new JPanel();
		BorderLayout configureLayout = new BorderLayout();
		centralePanel.setLayout(configureLayout);
		panel.add(centralePanel);
		centralePanel.add(centrale, BorderLayout.CENTER);
		centralePanel.add(new JLabel("centrale 1"), BorderLayout.NORTH);
		centralePanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			
			public void mousePressed(MouseEvent arg0) {
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DessinCentrale centrale = new DessinCentrale(true);
				//centrale.setNuage(points[0], max[0]);
				centrale.setNuage(solution.getEnergies()[0], solution.getEnergiesMax()[0]);
			}
		});

		double[] nua = {  100 , 0 , 0 , 30 , 120 , 80 , 40, 90 };
		max[1] = 150;
		points[1] = nua;
		centrale = new DessinCentrale();
	//	centrale.setNuage(points[1] , max[1]);
		centrale.setNuage(solution.getEnergies()[1], solution.getEnergiesMax()[1]);
		centralePanel = new JPanel();
		configureLayout = new BorderLayout();
		centralePanel.setLayout(configureLayout);
		panel.add(centralePanel);
		centralePanel.add(centrale, BorderLayout.CENTER);
		centralePanel.add(new JLabel("centrale 2"), BorderLayout.NORTH);
		centralePanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			
			public void mousePressed(MouseEvent arg0) {
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DessinCentrale centrale = new DessinCentrale(true);
				//centrale.setNuage(points[1], max[1]);
				centrale.setNuage(solution.getEnergies()[1], solution.getEnergiesMax()[1]);
			}
		});
	
		
		double[] nuag = {  60 , 40 , 70 , 30 , 16 , 10 , 140, 20 };
		max[2] = 200;
		points[2] = nuag;
		centrale = new DessinCentrale();
//		centrale.setNuage(points[2], max[2]);
		centrale.setNuage(solution.getEnergies()[2], solution.getEnergiesMax()[2]);
		centralePanel = new JPanel();
		configureLayout = new BorderLayout();
		centralePanel.setLayout(configureLayout);
		panel.add(centralePanel);
		centralePanel.add(centrale, BorderLayout.CENTER);
		centralePanel.add(new JLabel("centrale 3"), BorderLayout.NORTH);
		centralePanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			
			public void mousePressed(MouseEvent arg0) {
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DessinCentrale centrale = new DessinCentrale(true);
				//centrale.setNuage(points[2], max[2]);
				centrale.setNuage(solution.getEnergies()[2], solution.getEnergiesMax()[2]);
			}
		});
		
		double[] nuage = {  30 , 20 , 10 , 2 , 16 , 30 , 0, 80 };
		max[3] = 80;
		points[3] = nuage;
		centrale = new DessinCentrale();
	//	centrale.setNuage(points[3], max[3]);
		centrale.setNuage(solution.getEnergies()[3], solution.getEnergiesMax()[3]);
		centralePanel = new JPanel();
		configureLayout = new BorderLayout();
		centralePanel.setLayout(configureLayout);
		panel.add(centralePanel);
		centralePanel.add(centrale, BorderLayout.CENTER);
		centralePanel.add(new JLabel("centrale 4"), BorderLayout.NORTH);
		centralePanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			
			public void mousePressed(MouseEvent arg0) {
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DessinCentrale centrale = new DessinCentrale(true);
		//		centrale.setNuage(points[3], max[3]);
				centrale.setNuage(solution.getEnergies()[3], solution.getEnergiesMax()[3]);
			}
		});
		
		double[] nuages = {  10000 , 1550 , 7040 , 29000 , 16232 , 36980 , 55982, 60000 };
		max[4] = 60000;
		points[4] = nuages;
		DessinReservoir reservoir = new DessinReservoir();
		//reservoir.setNuage(points[4]);
		reservoir.setNuage(solution.getEnergies()[4]);
		centralePanel = new JPanel();
		configureLayout = new BorderLayout();
		centralePanel.setLayout(configureLayout);
		panel.add(centralePanel);
		centralePanel.add(reservoir, BorderLayout.CENTER);
		centralePanel.add(new JLabel("réservoir hydraulique"), BorderLayout.NORTH);centralePanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			
			public void mousePressed(MouseEvent arg0) {
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DessinReservoir reservoir = new DessinReservoir(true);
				reservoir.setNuage(points[4]);
			}
		});
		
		
		
		
		DessinHistogramme histogramme = new DessinHistogramme();
		final double[][] liste_points = { solution.getEnergies()[0], 
				solution.getEnergies()[1],
				solution.getEnergies()[2],
				solution.getEnergies()[3],
				solution.getEnergies()[4]};
		histogramme.setNuage(liste_points);
		centralePanel = new JPanel();
		configureLayout = new BorderLayout();
		centralePanel.setLayout(configureLayout);
		panel.add(centralePanel);
		centralePanel.add(histogramme, BorderLayout.CENTER);
		centralePanel.add(new JLabel("histogramme"), BorderLayout.NORTH);centralePanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			
			public void mousePressed(MouseEvent arg0) {
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DessinHistogramme histogramme = new DessinHistogramme(true);
				histogramme.setNuage(liste_points);
			}
		});
		
		
		return panel;
	}


}
