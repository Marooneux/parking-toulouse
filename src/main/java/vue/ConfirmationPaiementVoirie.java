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

import modele.ZoneVoirie;
import ui.theme.DefaultTheme;

public class ConfirmationPaiementVoirie extends JPanel {

	private static final long serialVersionUID = -1044509068457353369L;
	private ZoneVoirie zone;
	private String immatriculation;
	private int duree;
	private double prix;
	private String moyenPaiement;
	private JButton btnTerminer;

	public ConfirmationPaiementVoirie(ZoneVoirie zone2, String immatriculation, int duree, double prix,
			String moyenPaiement) {
		this.zone = zone2;
		this.immatriculation = immatriculation;
		this.duree = duree;
		this.prix = prix;
		this.moyenPaiement = moyenPaiement;

		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(Color.WHITE);
		panelCenterContainer.setBorder(new EmptyBorder(40, 100, 40, 100));
		this.add(panelCenterContainer, BorderLayout.CENTER);
		panelCenterContainer.setLayout(new BorderLayout(0, 0));

		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(DefaultTheme.BORDER_BUTTON, 1, true));
		panelCard.setBackground(Color.WHITE);
		panelCenterContainer.add(panelCard);
		panelCard.setLayout(new BorderLayout(0, 0));

		JPanel panelInnerContent = new JPanel();
		panelInnerContent.setBackground(Color.WHITE);
		panelInnerContent.setBorder(new EmptyBorder(30, 20, 30, 20));
		panelCard.add(panelInnerContent, BorderLayout.CENTER);
		panelInnerContent.setLayout(new GridLayout(5, 1, 0, 10));

		JLabel lblIconSuccess = new JLabel("✔");
		lblIconSuccess.setForeground(new Color(40, 167, 69));
		lblIconSuccess.setFont(DefaultTheme.FONT_SYMBOL_BIG);
		lblIconSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		panelInnerContent.add(lblIconSuccess);

		JLabel lblTitre = new JLabel("Paiement Validé !");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(new Color(40, 167, 69));
		lblTitre.setFont(DefaultTheme.FONT_TITLE);
		panelInnerContent.add(lblTitre);

		JLabel lblMontant = new JLabel("Montant réglé : " + String.format("%.2f €", prix));
		lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontant.setForeground(DefaultTheme.TEXT_COLOR);
		lblMontant.setFont(DefaultTheme.FONT_CARD_LABEL);
		panelInnerContent.add(lblMontant);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(Color.WHITE);
		panelFooter.setBorder(new EmptyBorder(10, 0, 20, 0));
		this.add(panelFooter, BorderLayout.SOUTH);

		this.btnTerminer = new JButton("Voir le e-ticket");
		this.btnTerminer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnTerminer.setForeground(Color.WHITE);
		this.btnTerminer.setFont(DefaultTheme.FONT_TITLE_SMALL);
		this.btnTerminer.setBackground(new Color(0, 123, 255));
		this.btnTerminer.setFocusPainted(false);
		this.btnTerminer.setBorderPainted(false);
		this.btnTerminer.setPreferredSize(new Dimension(250, 45));

		panelFooter.add(this.btnTerminer);
	}

	public JButton getBtnTerminer() {
		return this.btnTerminer;
	}

	public ZoneVoirie getZone() {
		return this.zone;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public int getDuree() {
		return this.duree;
	}

	public double getPrix() {
		return this.prix;
	}

	public String getMoyenPaiement() {
		return this.moyenPaiement;
	}
}