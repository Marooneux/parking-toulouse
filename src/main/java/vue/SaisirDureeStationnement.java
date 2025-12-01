package main.java.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SaisirDureeStationnement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnConfirmer;
	private JTextField textField;
	private JTextField textFieldDuree;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				SaisirDureeStationnement frame = new SaisirDureeStationnement();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public SaisirDureeStationnement() {
		this.setTitle("Démarrer le Stationnement");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		this.btnConfirmer = new JButton("Continuer vers le paiement");

		this.contentPane = new JPanel(new BorderLayout(15, 15));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.contentPane.setBackground(new Color(250, 250, 250));
		this.setContentPane(this.contentPane);

		// Entente
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

		JLabel lblSousTitre = new JLabel("Veuillez confirmez votre stationnement en voirie");
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

		// Création des champs de details du stationnement
		this.ajouterCarteDeDétails(body, "Zone Sélectionnée", this.detailsZone());
		this.ajouterCarteDeDétails(body, "Informations du Véhicule", this.detailsVoiture());
		this.ajouterCarteDeDétails(body, "Durée Stationnement", this.detailsDureeStationnement());

		// Button Payer
		// ? Créer un prototype que peut être utilisé et changé partout dans l'appli ?
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(250, 250, 250));

		this.btnConfirmer.setFont(new Font("Segoe UI", Font.BOLD, 16));
		this.btnConfirmer.setBackground(new Color(0, 122, 255));
		this.btnConfirmer.setForeground(Color.WHITE);
		this.btnConfirmer.setFocusPainted(false);
		this.btnConfirmer.setBorderPainted(false);
		this.btnConfirmer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnConfirmer.setPreferredSize(new Dimension(250, 40));
		this.btnConfirmer.setOpaque(true);
		this.btnConfirmer.addActionListener(e -> {
			String duree = this.textField.getText();
			if (duree == null || duree.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Veuillez saisir une durée avant de payer.");
				return;
			}

			PaiementVoirie framePaiementVoirie = new PaiementVoirie();
			framePaiementVoirie.setVisible(true);
			this.dispose();
		});

		buttonPanel.add(this.btnConfirmer);
		this.contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JPanel detailsZone() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblParking = new JLabel("Zone rouge");
		lblParking.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblParking.setForeground(new Color(50, 50, 50));
		p.add(lblParking);

		return p;
	}

	private JPanel detailsVoiture() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblInfoVehicule = new JLabel("Numéro de Plaque d'Immatriculation");
		lblInfoVehicule.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblInfoVehicule.setForeground(new Color(50, 50, 50));
		p.add(lblInfoVehicule);

		// Todo: Rendre Plaque imatricule changeable par une méthode;
		JLabel plaque = new JLabel("AB-123-CD");
		plaque.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		plaque.setForeground(new Color(70, 70, 70));
		plaque.setBorder(new EmptyBorder(5, 0, 0, 0));
		p.add(plaque);

		JLabel lblInfoImatricule = new JLabel(
				"Vous serez susceptible de reçevoir une amende si la plaque indiquée n'est pas la bonne");
		lblInfoImatricule.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblInfoImatricule.setForeground(new Color(120, 120, 120));
		lblInfoImatricule.setBorder(new EmptyBorder(5, 0, 0, 0));
		p.add(lblInfoImatricule);

		return p;
	}

	private JPanel detailsDureeStationnement() {
		this.textFieldDuree = new PlaceholderTextField("Nom Prénom", 20);
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblTitreHeureArrive = new JLabel("Saisissez la durée de stationnement");
		lblTitreHeureArrive.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTitreHeureArrive.setForeground(new Color(50, 50, 50));
		p.add(lblTitreHeureArrive);

		p.add(Box.createRigidArea(new Dimension(0, 8)));

		// Todo : Rendre le texte field changeable par méthode pour pouvoir manipuler sa
		// valeur
		this.textField = new JTextField();
		this.textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		this.textField.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));
		this.textField.setBackground(new Color(245, 245, 245));
		p.add(this.textField);

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
		header.add(lblTitle);

		card.add(header, BorderLayout.NORTH);
		card.add(innerContent, BorderLayout.CENTER);

		parent.add(card);
		parent.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	public JButton getBtnPayment() {
		return this.btnConfirmer;
	}

	public JTextField getTextField() {
		return this.textField;
	}
}
