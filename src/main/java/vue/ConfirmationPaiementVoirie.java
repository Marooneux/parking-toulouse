package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.ZoneVoirie;
import controleur.ControleurConfirmationPaiementVoirie;

public class ConfirmationPaiementVoirie extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 750, 550);
		this.setTitle("Paiement validé");

		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 255, 255));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 255, 255));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(new Color(255, 255, 255));
		panelCenterContainer.setBorder(new EmptyBorder(40, 100, 40, 100));
		this.contentPane.add(panelCenterContainer, BorderLayout.CENTER);
		panelCenterContainer.setLayout(new BorderLayout(0, 0));

		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(new Color(222, 226, 230), 1, true));
		panelCard.setBackground(new Color(255, 255, 255));
		panelCenterContainer.add(panelCard);
		panelCard.setLayout(new BorderLayout(0, 0));

		JPanel panelInnerContent = new JPanel();
		panelInnerContent.setBackground(Color.WHITE);
		panelInnerContent.setBorder(new EmptyBorder(30, 20, 30, 20));
		panelCard.add(panelInnerContent, BorderLayout.CENTER);
		panelInnerContent.setLayout(new GridLayout(5, 1, 0, 10));

		JLabel lblIconSuccess = new JLabel("✔");
		lblIconSuccess.setForeground(new Color(40, 167, 69));
		lblIconSuccess.setFont(new Font("Segoe UI Symbol", Font.BOLD, 50));
		lblIconSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		panelInnerContent.add(lblIconSuccess);

		JLabel lblTitre = new JLabel("Paiement Validé !");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(new Color(40, 167, 69));
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 26));
		panelInnerContent.add(lblTitre);

		JLabel lblMontant = new JLabel("Montant réglé : " + String.format("%.2f €", prix));
		lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontant.setForeground(new Color(33, 37, 41));
		lblMontant.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panelInnerContent.add(lblMontant);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(new Color(255, 255, 255));
		panelFooter.setBorder(new EmptyBorder(10, 0, 20, 0));
		this.contentPane.add(panelFooter, BorderLayout.SOUTH);

		btnTerminer = new JButton("Voir le e-ticket");
		btnTerminer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		new ControleurConfirmationPaiementVoirie(this);
		btnTerminer.setForeground(Color.WHITE);
		btnTerminer.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTerminer.setBackground(new Color(0, 123, 255));
		btnTerminer.setFocusPainted(false);
		btnTerminer.setBorderPainted(false);
		btnTerminer.setPreferredSize(new Dimension(250, 45));

		panelFooter.add(btnTerminer);
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