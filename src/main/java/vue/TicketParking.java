package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.Parking;
import modele.ReservationParking;
import ui.theme.DefaultTheme;

public class TicketParking extends JPanel {

	private static final long serialVersionUID = -719850425250157066L;
	private JButton btnPaiement;
	private JLabel lblNumeroTicket;
	private JLabel lblParking;
	private JLabel lblPlaque;
	private JLabel lblHeure;
	private JLabel lblAdresse;
	private JLabel lblInstruction;
	private Parking parking;
	private String immatriculation;
	private String heureArrivee;
	private ReservationParking reservation;
	private boolean confirmationRequise;

	public TicketParking(ReservationParking reservation, String plaque) {
		this(reservation, plaque, true);
	}

	public TicketParking(ReservationParking reservation, String plaque, boolean confirmationRequise) {
		this.reservation = reservation;
		this.parking = reservation.getParking();
		this.immatriculation = plaque;
		this.heureArrivee = reservation.dateArriveeToString();
		this.confirmationRequise = confirmationRequise;

		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setLayout(new BorderLayout(0, 0));

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Color.WHITE);
		this.add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BorderLayout(20, 0));

		JLabel lblIcon = new JLabel("P");
		lblIcon.setFont(DefaultTheme.FONT_TITLE_BIG);
		lblIcon.setForeground(new Color(60, 60, 60));
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setPreferredSize(new Dimension(60, 60));
		lblIcon.setBorder(new LineBorder(new Color(60, 60, 60), 2, true));
		panelHeader.add(lblIcon, BorderLayout.WEST);

		JPanel panelTextHeader = new JPanel();
		panelTextHeader.setBackground(Color.WHITE);
		panelHeader.add(panelTextHeader, BorderLayout.CENTER);
		panelTextHeader.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblTitre = new JLabel("Récapitulatif de votre stationnement");
		lblTitre.setForeground(DefaultTheme.TEXT_COLOR);
		lblTitre.setFont(DefaultTheme.FONT_TITLE_LABEL);
		panelTextHeader.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Veuillez conserver ce récapitulatif jusqu'à votre départ");
		lblSousTitre.setForeground(DefaultTheme.SUBTEXT_COLOR);
		lblSousTitre.setFont(DefaultTheme.FONT_FIELD);
		panelTextHeader.add(lblSousTitre);

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(Color.WHITE);
		panelCenterContainer.setBorder(new EmptyBorder(20, 80, 10, 80));
		this.add(panelCenterContainer, BorderLayout.CENTER);
		panelCenterContainer.setLayout(new BorderLayout(0, 0));

		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(DefaultTheme.BORDER_BUTTON, 1, true));
		panelCard.setBackground(Color.WHITE);
		panelCenterContainer.add(panelCard);
		panelCard.setLayout(new BorderLayout(0, 0));

		JPanel panelInfoGrid = new JPanel();
		panelInfoGrid.setBackground(Color.WHITE);
		panelInfoGrid.setBorder(new EmptyBorder(20, 30, 20, 30));
		panelCard.add(panelInfoGrid, BorderLayout.CENTER);
		panelInfoGrid.setLayout(new GridLayout(5, 1, 0, 10));

		this.lblNumeroTicket = this.createInfoRow(panelInfoGrid, "Numéro de Ticket :", "#P-00001");
		this.lblParking = this.createInfoRow(panelInfoGrid, "Parking :", this.parking.getNom());
		this.lblAdresse = this.createInfoRow(panelInfoGrid, "Adresse :", this.parking.getAdresse().getRue());
		this.lblPlaque = this.createInfoRow(panelInfoGrid, "Immatriculation :", this.immatriculation);
		this.lblHeure = this.createInfoRow(panelInfoGrid, "Heure d'arrivée :", this.heureArrivee);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(Color.WHITE);
		panelFooter.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new GridLayout(2, 1, 0, 10));

		this.lblInstruction = new JLabel();
		this.lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblInstruction.setForeground(DefaultTheme.TEXT_COLOR);
		this.lblInstruction.setFont(DefaultTheme.FONT_BUTTON_ALT);
		panelFooter.add(this.lblInstruction);

		JPanel panelButtonContainer = new JPanel();
		panelButtonContainer.setBackground(Color.WHITE);
		panelFooter.add(panelButtonContainer);

		this.btnPaiement = new JButton();
		panelButtonContainer.add(this.btnPaiement);
		this.btnPaiement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnPaiement.setForeground(Color.WHITE);
		this.btnPaiement.setFont(DefaultTheme.FONT_TITLE_SMALL);
		this.btnPaiement.setBackground(new Color(0, 123, 255));
		this.btnPaiement.setFocusPainted(false);
		this.btnPaiement.setBorderPainted(false);
		this.btnPaiement.setPreferredSize(new Dimension(250, 45));

		this.refreshActions();
	}

	private JLabel createInfoRow(JPanel parent, String label, String valeur) {
		JPanel row = new JPanel();
		row.setBackground(Color.WHITE);
		row.setLayout(new BorderLayout());

		JLabel lblKey = new JLabel(label);
		lblKey.setFont(DefaultTheme.FONT_LABEL);
		lblKey.setForeground(new Color(100, 100, 100));

		JLabel lblVal = new JLabel(valeur);
		lblVal.setFont(DefaultTheme.FONT_TITLE_SMALL);
		lblVal.setForeground(new Color(50, 50, 50));
		lblVal.setHorizontalAlignment(SwingConstants.RIGHT);

		row.add(lblKey, BorderLayout.WEST);
		row.add(lblVal, BorderLayout.EAST);

		JPanel separator = new JPanel();
		separator.setPreferredSize(new Dimension(10, 1));
		separator.setBackground(new Color(245, 245, 245));
		row.add(separator, BorderLayout.SOUTH);

		parent.add(row);
		return lblVal;
	}

	private void refreshActions() {
		if (this.confirmationRequise) {
			this.lblInstruction.setText("Veuillez confirmer les informations du ticket");
			this.btnPaiement.setText("Confirmer le ticket");
		} else {
			this.lblInstruction.setText("Lorsque vous souhaitez partir, appuyer sur le bouton suivant");
			this.btnPaiement.setText("Finir et payer");
		}
	}

	public JButton getBtnPaiement() {
		return this.btnPaiement;
	}

	public ReservationParking getReservation() {
		return this.reservation;
	}

	public Parking getParking() {
		return this.parking;
	}

	public String getHeureArrivee() {
		return this.heureArrivee;
	}

	public boolean isConfirmationRequise() {
		return this.confirmationRequise;
	}

	public void remplirInfos(String numeroTicket, String parking, String plaque, String heure, String adresse) {
		this.lblNumeroTicket.setText(numeroTicket);
		this.lblParking.setText(this.reservation.getParking().getNom());
		this.lblPlaque.setText(plaque);
		this.lblHeure.setText(this.reservation.dateArriveeToString());
		this.lblAdresse.setText(this.reservation.getParking().getAdresse().getRue());
	}
}
