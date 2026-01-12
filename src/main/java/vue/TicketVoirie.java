package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleur.ControleurTicketVoirie;
import modele.ZoneVoirie;

public class TicketVoirie extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ZoneVoirie zone;
	private String immatriculation;
	private int duree;
	private String moyenPaiement;

	public TicketVoirie(ZoneVoirie zone2, String immatriculation, int duree, String moyenPaiement) {
		this.zone = zone2;
		this.immatriculation = immatriculation;
		this.duree = duree;
		this.moyenPaiement = moyenPaiement;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 750, 600);
		this.setTitle("Ticket de voirie");

		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 255, 255));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(255, 255, 255));
		this.contentPane.add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BorderLayout(20, 0));

		JLabel lblIcon = new JLabel("P");
		lblIcon.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblIcon.setForeground(new Color(60, 60, 60));
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setPreferredSize(new Dimension(60, 60));
		lblIcon.setBorder(new LineBorder(new Color(60, 60, 60), 2, true));
		panelHeader.add(lblIcon, BorderLayout.WEST);

		JPanel panelTextHeader = new JPanel();
		panelTextHeader.setBackground(Color.WHITE);
		panelHeader.add(panelTextHeader, BorderLayout.CENTER);
		panelTextHeader.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblTitre = new JLabel("Récapitulatif de stationnement");
		lblTitre.setForeground(new Color(33, 37, 41));
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panelTextHeader.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Veuillez conserver ce récapitulatif jusqu'à votre départ");
		lblSousTitre.setForeground(new Color(108, 117, 125));
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTextHeader.add(lblSousTitre);

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(new Color(255, 255, 255));
		panelCenterContainer.setBorder(new EmptyBorder(20, 80, 10, 80));
		this.contentPane.add(panelCenterContainer, BorderLayout.CENTER);
		panelCenterContainer.setLayout(new BorderLayout(0, 0));

		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(new Color(222, 226, 230), 1, true));
		panelCard.setBackground(new Color(255, 255, 255));
		panelCenterContainer.add(panelCard);
		panelCard.setLayout(new BorderLayout(0, 0));

		JPanel panelInfoGrid = new JPanel();
		panelInfoGrid.setBackground(new Color(255, 255, 255));
		panelInfoGrid.setBorder(new EmptyBorder(20, 30, 20, 30));
		panelCard.add(panelInfoGrid, BorderLayout.CENTER);
		panelInfoGrid.setLayout(new GridLayout(6, 1, 0, 10));
		this.createInfoRow(panelInfoGrid, "Numéro de Ticket :", "#V-00001");
		this.createInfoRow(panelInfoGrid, "Zone :", zone2.getNom());
		this.createInfoRow(panelInfoGrid, "Immatriculation :", immatriculation);
		this.createInfoRow(panelInfoGrid, "Heure d'arrivée :", ControleurTicketVoirie.getHeureActuelle());
		this.createInfoRow(panelInfoGrid, "Heure départ max :", ControleurTicketVoirie.calculerHeureDepart(duree));
		this.createInfoRow(panelInfoGrid, "Moyen de paiement :", moyenPaiement);

		this.createInfoRow(panelInfoGrid, "Numéro de Ticket :", "#V-00001");
		this.createInfoRow(panelInfoGrid, "Zone :", "Zone Rouge");
		this.createInfoRow(panelInfoGrid, "Immatriculation :", "AB-123-CD");
		this.createInfoRow(panelInfoGrid, "Heure d'arrivée :", "14:30");
		this.createInfoRow(panelInfoGrid, "Heure départ max :", "16:30");
		this.createInfoRow(panelInfoGrid, "Moyen de paiement :", "Carte Bancaire");

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(new Color(255, 255, 255));
		panelFooter.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.contentPane.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new GridLayout(2, 1, 0, 10));

		JLabel lblWarning = new JLabel("Lorsque vous souhaitez partir, appuyer sur le bouton suivant");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setForeground(new Color(33, 37, 41));
		lblWarning.setFont(new Font("Segoe UI", Font.BOLD, 13));
		panelFooter.add(lblWarning);
	}

	private void createInfoRow(JPanel parent, String label, String valeur) {
		JPanel row = new JPanel();
		row.setBackground(Color.WHITE);
		row.setLayout(new BorderLayout());

		JLabel lblKey = new JLabel(label);
		lblKey.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblKey.setForeground(new Color(100, 100, 100));

		JLabel lblVal = new JLabel(valeur);
		lblVal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblVal.setForeground(new Color(50, 50, 50));
		lblVal.setHorizontalAlignment(SwingConstants.RIGHT);

		row.add(lblKey, BorderLayout.WEST);
		row.add(lblVal, BorderLayout.EAST);

		JPanel separator = new JPanel();
		separator.setPreferredSize(new Dimension(10, 1));
		separator.setBackground(new Color(245, 245, 245));
		row.add(separator, BorderLayout.SOUTH);

		parent.add(row);
	}
}
