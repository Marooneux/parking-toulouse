package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

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
import javax.swing.text.AbstractDocument;

import modele.Parking;
import vue.PaiementVoirie.LimiteCaracteresFilter;

public class SaisirHeureArriveParking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnConfirmer;
	private JTextField textField;
	private JTextField textFieldDuree;
	private JTextField textFieldPlaque;
    private JTextField textFieldNom;
	private Parking parking;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				//SaisirDureeStationnement frame = new SaisirDureeStationnement();
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public SaisirHeureArriveParking(Parking parking) {
		this.parking = parking;
		
		this.setTitle("Démarrer le Stationnement");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		this.btnConfirmer = new JButton("Démarrer le stationnement");
		
		
		this.contentPane = new JPanel(new BorderLayout(15, 15));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.contentPane.setBackground(new Color(250, 250, 250));
		this.setContentPane(this.contentPane);

		// Entête
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

		// Création des champs de details du stationnement
		this.ajouterCarteDeDétails(body, "Parking selectionné", this.detailsZone());
		this.ajouterCarteDeDétails(body, "Informations du véhicule", this.detailsVoiture());
		this.ajouterCarteDeDétails(body, "Heure d'arrivée", this.detailsDureeStationnement());

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
		this.btnConfirmer.setMinimumSize(new Dimension(250, 50));
		this.btnConfirmer.setOpaque(true);
        this.btnConfirmer.addActionListener(e -> {
            String strHeureArrive= this.textFieldNom.getText();
            String immatriculation = this.textFieldPlaque.getText();
            if (immatriculation == null || immatriculation.trim().isEmpty()) {
            	JOptionPane.showMessageDialog(this, "Veuillez saisir votre plaque d'immatriculation de continuer.");
                return;
            }
            if (strHeureArrive == null || strHeureArrive.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir une heure d'arrivée avant de continuer.");
                return;
            }
            
            DateTimeFormatter strictFormatter = DateTimeFormatter.ofPattern("HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);

            try {
            	LocalTime heureArrivee = LocalTime.parse(strHeureArrive, strictFormatter);
            	if (heureArrivee.isBefore(LocalTime.now())) {
            		new TicketParking(parking, immatriculation, strHeureArrive).setVisible(true);
            		dispose();
            	} else {
            		JOptionPane.showMessageDialog(this, "L'heure entrée doit être inférieure à l'heure actuelle.");
            	}
            } catch (DateTimeParseException ex) {
            	JOptionPane.showMessageDialog(this, "L'heure entrée n'est pas au bon format.");
            }
            
    	});
 
		buttonPanel.add(this.btnConfirmer);
		this.contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JPanel detailsZone() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		JLabel lblZone = new JLabel(parking.getNom());
		lblZone.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblZone.setForeground(new Color(50, 50, 50));
		p.add(lblZone);
		
		JLabel lblDureeMax = new JLabel("Tarif horaire : " + parking.getTarif() + "€/h");
		lblDureeMax.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDureeMax.setForeground(new Color(50, 50, 50));
		p.add(lblDureeMax);
		
		JLabel lblHauteur = new JLabel("Hauteur :" + parking.getHauteur() + "m");
		lblHauteur.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblHauteur.setForeground(new Color(50, 50, 50));
		p.add(lblHauteur);
		return p;
	}

	private JPanel detailsVoiture() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblInfoVehicule = new JLabel("Entrez la plaque d'immatriculation de votre véhicule avec le format suivant");
		lblInfoVehicule.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblInfoVehicule.setForeground(new Color(50, 50, 50));
		p.add(lblInfoVehicule);

        textFieldPlaque = new PlaceholderTextField("AB-001-CD", 4);
        ((AbstractDocument) textFieldPlaque.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(20));
        textFieldPlaque.setPreferredSize(new Dimension(250, 30)); 
		p.add(textFieldPlaque);

		JLabel lblInfoImatricule = new JLabel(
				"Vous serez susceptible de reçevoir une amende si la plaque indiquée n'est pas la bonne");
		lblInfoImatricule.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblInfoImatricule.setForeground(new Color(120, 120, 120));
		lblInfoImatricule.setBorder(new EmptyBorder(5, 0, 0, 0));
		p.add(lblInfoImatricule);

		return p;
	}

	private JPanel detailsDureeStationnement() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblTitreHeureArrive = new JLabel("Saisissez votre heure d'arrivée avec le format suivant");
		lblTitreHeureArrive.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTitreHeureArrive.setForeground(new Color(50, 50, 50));
		p.add(lblTitreHeureArrive);

		p.add(Box.createRigidArea(new Dimension(0, 8)));

		// Todo : Rendre le texte field changeable par méthode pour pouvoir manipuler sa
		// valeur
        textFieldNom = new PlaceholderTextField("hh:mm", 4);
        ((AbstractDocument) textFieldNom.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(20));
        textFieldNom.setPreferredSize(new Dimension(250, 30)); 
		p.add(this.textFieldNom);

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

	public JButton getBtnConfirmer() {
		return this.btnConfirmer;
	}

	public JTextField getTextField() {
		return this.textField;
	}

}
