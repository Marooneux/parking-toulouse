package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modele.Parking;

public class SaisirHeureArriveParking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnStart;
	private JButton btnNow;
	private JLabel lblParkingInfo;
	private ParkingPanel parking;
	private JTextField plaque;

	public SaisirHeureArriveParking(ParkingPanel parking) {

		this.parking = parking;
	    this.setTitle("Démarrer le Stationnement");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(600, 700);
	    this.setLocationRelativeTo(null);
	    this.btnStart = new JButton("Démarrer le Stationnement");

	    this.contentPane = new JPanel(new BorderLayout(15, 15));
	    this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
	    this.contentPane.setBackground(new Color(250, 250, 250));
	    this.setContentPane(this.contentPane);
    

	    // Header (no changes)
	    JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    header.setBackground(new Color(250, 250, 250));
	    JLabel lblIcon = new JLabel("\uD83C\uDFE2");
	    lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
	    header.add(lblIcon);
	    JPanel texte = new JPanel();
	    texte.setBackground(new Color(250, 250, 250));
	    texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));
	    JLabel lblTitre = new JLabel("Démarrer le Stationnement");
	    lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
	    lblTitre.setForeground(new Color(40, 40, 40));
	    texte.add(lblTitre);
	    JLabel lblSousTitre = new JLabel("Enregistrez votre arrivée au parking");
	    lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    lblSousTitre.setForeground(new Color(100, 100, 100));
	    texte.add(lblSousTitre);
	    header.add(texte);
	    this.contentPane.add(header, BorderLayout.NORTH);

	    // Body
	    JPanel body = new JPanel();
	    body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
	    body.setBackground(new Color(250, 250, 250));
	    body.setBorder(new EmptyBorder(10, 0, 10, 0));
	    this.contentPane.add(body, BorderLayout.CENTER);

	    // Add details using the **already initialized labels**
	    this.ajouterCarteDeDétails(body, "Parking Sélectionné", this.detailsDuParking());
	    this.ajouterCarteDeDétails(body, "Informations du Véhicule", this.detailsVoiture());
	    this.ajouterCarteDeDétails(body, "Heure d'Arrivée", this.detailsHeureArrive());

	    // Buttons
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setBackground(new Color(250, 250, 250));
	    this.btnStart.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    this.btnStart.setBackground(new Color(0, 122, 255));
	    this.btnStart.setForeground(Color.WHITE);
	    this.btnStart.setFocusPainted(false);
	    this.btnStart.setBorderPainted(false);
	    this.btnStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    this.btnStart.setPreferredSize(new Dimension(250, 40));
	    this.btnStart.setOpaque(true);
	    buttonPanel.add(this.btnStart);
	    this.contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

	public SaisirHeureArriveParking() {
		this.setTitle("Démarrer le Stationnement");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		this.btnStart = new JButton("Démarrer le Stationnement");

		this.contentPane = new JPanel(new BorderLayout(15, 15));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.contentPane.setBackground(new Color(250, 250, 250));
		this.setContentPane(this.contentPane);

		// Header
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(new Color(250, 250, 250));

		JLabel lblIcon = new JLabel("\uD83C\uDFE2");
		lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
		header.add(lblIcon);

		JPanel texte = new JPanel();
		texte.setBackground(new Color(250, 250, 250));
		texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Démarrer le Stationnement");
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitre.setForeground(new Color(40, 40, 40));
		texte.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Enregistrez votre arrivée au parking");
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSousTitre.setForeground(new Color(100, 100, 100));
		texte.add(lblSousTitre);

		header.add(texte);
		this.contentPane.add(header, BorderLayout.NORTH);

		// Corps
		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(new Color(250, 250, 250));
		body.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.contentPane.add(body, BorderLayout.CENTER);

		this.ajouterCarteDeDétails(body, "Parking Sélectionné", this.detailsDuParking());
		this.ajouterCarteDeDétails(body, "Informations du Véhicule", this.detailsVoiture());
		this.ajouterCarteDeDétails(body, "Heure d'Arrivée", this.detailsHeureArrive());

		// Button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(250, 250, 250));

		this.btnStart.setFont(new Font("Segoe UI", Font.BOLD, 16));
		this.btnStart.setBackground(new Color(0, 122, 255));
		this.btnStart.setForeground(Color.WHITE);
		this.btnStart.setFocusPainted(false);
		this.btnStart.setBorderPainted(false);
		this.btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnStart.setPreferredSize(new Dimension(250, 40));
		this.btnStart.setOpaque(true);
		buttonPanel.add(this.btnStart);
		this.contentPane.add(buttonPanel, BorderLayout.SOUTH);
		this.btnStart.addActionListener(e -> {
			String duree = this.textField.getText();
			if (duree == null || duree.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Veuillez saisir une durée avant de payer.");
				return;
			}

			TicketParking frameTicketParking = new TicketParking();
			frameTicketParking.setVisible(true);
			this.dispose();
		});
	}

	private JPanel detailsDuParking() {
	    JPanel p = new JPanel();
	    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	    p.setBackground(Color.WHITE);
	    p.setBorder(new EmptyBorder(15, 15, 15, 15));
	    
	    lblParkingInfo = new JLabel("blabla");
	    lblParkingInfo.setFont(new Font("Segoe UI", Font.BOLD, 15));
	    lblParkingInfo.setForeground(new Color(50, 50, 50));
	    p.add(lblParkingInfo);
	    return p;
	}



	private JPanel detailsVoiture() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblInfoVehicule = new JLabel("Informations du Véhicule");
		lblInfoVehicule.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblInfoVehicule.setForeground(new Color(50, 50, 50));
		p.add(lblInfoVehicule);

		plaque = new JTextField("");
		plaque.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		plaque.setForeground(new Color(70, 70, 70));
		plaque.setBorder(new EmptyBorder(5, 0, 0, 0));
		p.add(plaque);

		JLabel info = new JLabel("Nécessaire pour l'entrée et la sortie automatisées");
		info.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		info.setForeground(new Color(120, 120, 120));
		info.setBorder(new EmptyBorder(5, 0, 0, 0));
		p.add(info);

		return p;
	}

	private JPanel detailsHeureArrive() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblTitreHeureArrive = new JLabel("Sélectionnez votre heure d'arrivée");
		lblTitreHeureArrive.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTitreHeureArrive.setForeground(new Color(50, 50, 50));
		p.add(lblTitreHeureArrive);

		p.add(Box.createRigidArea(new Dimension(0, 8)));

		JPanel heurePanel = new JPanel();
		heurePanel.setLayout(new BoxLayout(heurePanel, BoxLayout.X_AXIS));
		heurePanel.setBackground(Color.WHITE);

		this.textField = new JTextField();
		this.textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		this.textField.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));
		this.textField.setBackground(new Color(245, 245, 245));
		heurePanel.add(this.textField);

		this.btnNow = new JButton("Maintenant");
		this.btnNow.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		this.btnNow.setBackground(new Color(230, 230, 230));
		this.btnNow.setBorderPainted(false);
		this.btnNow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnNow.setOpaque(true);
		heurePanel.add(Box.createRigidArea(new Dimension(10, 0)));
		heurePanel.add(this.btnNow);

		p.add(heurePanel);

		JLabel info = new JLabel(
				"Vous pourrez quitter l'application et revenir plus tard pour enregistrer votre départ");
		info.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		info.setForeground(new Color(120, 120, 120));
		info.setBorder(new EmptyBorder(10, 0, 0, 0));
		p.add(info);

		return p;
	}

	private void ajouterCarteDeDétails(JPanel parent, String title, JPanel innerContent) {
		JPanel card = new JPanel();
		card.setLayout(new BorderLayout());
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0),
				BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true)));

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(Color.WHITE);

		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTitle.setForeground(new Color(70, 70, 70));
		lblTitle.setPreferredSize(new Dimension(340, 40));
		header.add(lblTitle);

		card.add(header, BorderLayout.NORTH);
		card.add(innerContent, BorderLayout.CENTER);

		parent.add(card);
		parent.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	public JButton getBtnPayment() {
		return this.btnStart;
	}

	public JTextField getTextField() {
		return this.textField;
	}

	public JButton getBtnMaintenant() {
		return this.btnNow;
	}
	
	public JLabel getLblParkingInfo() {
	    return this.lblParkingInfo;
	}
	
	public JTextField getPlaque() {
		return this.plaque;
	}

}
