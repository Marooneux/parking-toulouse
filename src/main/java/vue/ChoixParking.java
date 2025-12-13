package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.BaseDeDonnees;

public class ChoixParking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnParking;
	private List<ParkingPanel> parkingsPanels;

	public ChoixParking() {
		this.setTitle("Stationnement dans un Parking");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);

		this.contentPane = new JPanel(new BorderLayout(15, 15));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.contentPane.setBackground(new Color(250, 250, 250));
		this.setContentPane(this.contentPane);
		this.parkingsPanels = new ArrayList<>();

		// HEADER
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(new Color(250, 250, 250));

		JLabel lblIcon = new JLabel("\uD83D\uDED1");
		lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
		header.add(lblIcon);

		JPanel texte = new JPanel();
		texte.setBackground(new Color(250, 250, 250));
		texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Stationnement dans un Parking");
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitre.setForeground(new Color(40, 40, 40));

		JLabel lblSousTitre = new JLabel("Choisissez une place de parking disponible");
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSousTitre.setForeground(new Color(100, 100, 100));

		texte.add(lblTitre);
		texte.add(lblSousTitre);
		header.add(texte);

		this.contentPane.add(header, BorderLayout.NORTH);
		// prendre donnees
		List<Object[]> rawData = BaseDeDonnees.getListeDonnées("parkings");
		List<ParkingPanel> panels = new ArrayList<>();

		for (Object[] row : rawData) {
			String nom = (String) row[0];
			String adresse = (String) row[1];
			String heures = (String) row[2];
			String remplissage = (String) row[3];
			String prix = (String) row[4];
			String etat = (String) row[5];

			ParkingPanel panel = new ParkingPanel(nom, adresse, heures, remplissage, prix, etat);

			panel.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					ChoixParking.this.selectOnlyThis(panel);
				}
			});

			this.parkingsPanels.add(panel);
		}

		// ----

		ListeParkings listeParkings = new ListeParkings(this.parkingsPanels);
		this.contentPane.add(listeParkings, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		this.contentPane.add(panel, BorderLayout.SOUTH);

		JPanel panelBtn = new JPanel();
		panelBtn.setBackground(Color.WHITE);

		this.btnParking = new JButton("Choisir Parking");
		this.btnParking.setBackground(new Color(0, 128, 255));
		this.btnParking.setForeground(Color.WHITE);
		this.btnParking.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		this.btnParking.setFocusPainted(false);
		this.btnParking.setPreferredSize(new Dimension(160, 40));
		panelBtn.add(this.btnParking);
		panel.add(this.btnParking);

	}

	public JButton getBtnChoisirParking() {
		return this.btnParking;
	}

	// une méthode pour récupérer le parking sélectionné
	public ParkingPanel getParkingSelectionne() {
		for (ParkingPanel p : this.parkingsPanels) {
			if (p.isSelected()) {
				return p;
			}
		}
		return null;
	}

	// pour ne sélectionner qu’un seul parking à la fois
	private void selectOnlyThis(ParkingPanel selectedPanel) {
		for (ParkingPanel p : this.parkingsPanels) {
			if (p != selectedPanel && p.isSelected()) {
				p.toggleSelection();
			}
		}
		if (!selectedPanel.isSelected()) {
			selectedPanel.toggleSelection();
		}
	}

}
