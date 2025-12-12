package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import modele.StationnementVoirie;
import modele.StationnementVoirie.Couleur;
import vue.PaiementVoirie.LimiteCaracteresFilter;

public class SaisirDureeStationnement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnConfirmer;
	private JTextField textField;
	private JTextField textFieldDuree;
	private JTextField textFieldPlaque;
    private JTextField textFieldNom;
	private StationnementVoirie zone;

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

	public SaisirDureeStationnement(StationnementVoirie zone) {
		this.zone = zone;
		
		this.setTitle("Démarrer le Stationnement");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		if (zone.getCouleur() == Couleur.BLEU) {
			this.btnConfirmer = new JButton("Confirmer votre stationnement");
		} else {
			this.btnConfirmer = new JButton("Continuer vers le paiement");
		}
		
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
		this.ajouterCarteDeDétails(body, "Informations du véhicule", this.detailsVoiture());
		this.ajouterCarteDeDétails(body, "Durée du stationnement", this.detailsDureeStationnement());

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
            String strDuree = this.textFieldNom.getText();
            String immatriculation = this.textFieldPlaque.getText();
            if (immatriculation == null || immatriculation.trim().isEmpty()) {
            	JOptionPane.showMessageDialog(this, "Veuillez saisir votre plaque d'immatriculation avant de payer.");
                return;
            }
            if (strDuree == null || strDuree.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir une durée avant de payer.");
                return;
            }
            int intDuree;
            try {
            	intDuree = Integer.parseInt(strDuree);
                if (intDuree > zone.getDureeMax()) {
                	JOptionPane.showMessageDialog(this, "La durée saisie est supérieure à la durée maximum de cette zone.");
                    return;
                }
                if (zone.getCouleur() == Couleur.BLEU) {
                    TicketVoirie frameTicketVoirie = new TicketVoirie();
                    frameTicketVoirie.setVisible(true);
	                dispose();
                } else if (zone.getCouleur() == Couleur.ROUGE && intDuree <= 30) {
                	TicketVoirie frameTicketVoirie = new TicketVoirie();
                    frameTicketVoirie.setVisible(true);
 	                dispose();
                
            	} else {
	                PaiementVoirie framePaiementVoirie = new PaiementVoirie(zone, intDuree);
	                framePaiementVoirie.setVisible(true);
	                dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une durée en minutes uniquement.");
                return;
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
		
		String minsGratuites = "";
		if (zone.getCouleur() == Couleur.ROUGE) {
			minsGratuites = " (30 minutes gratuites)";
		}
		JLabel lblZone = new JLabel(zone.couleurZoneToString() + minsGratuites);
		lblZone.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblZone.setForeground(new Color(50, 50, 50));
		p.add(lblZone);
		
		JLabel lblDureeMax = new JLabel("Durée maximum : " + zone.dureeMaxToString());
		lblDureeMax.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDureeMax.setForeground(new Color(50, 50, 50));
		p.add(lblDureeMax);

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

		// Todo: Rendre Plaque imatricule changeable par une méthode;
        textFieldPlaque = new PlaceholderTextField("AB-001-CD", 4);
        ((AbstractDocument) textFieldPlaque.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(20));
        textFieldPlaque.setPreferredSize(new Dimension(250, 30)); 
		p.add(textFieldPlaque);

		JLabel lblInfoImatricule = new JLabel("Vous serez susceptible de reçevoir une amende si la plaque indiquée n'est pas la bonne");
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

		JLabel lblTitreHeureArrive = new JLabel("Saisissez la durée de stationnement (en minutes)");
		lblTitreHeureArrive.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTitreHeureArrive.setForeground(new Color(50, 50, 50));
		p.add(lblTitreHeureArrive);

		p.add(Box.createRigidArea(new Dimension(0, 8)));

		// Todo : Rendre le texte field changeable par méthode pour pouvoir manipuler sa
		// valeur
        textFieldNom = new PlaceholderTextField("minutes", 4);
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
	
	
    public String dureeMaxToString(int dureeMax) {
    	String duree = " heures";
    	int heures = dureeMax / 60;
    	int minutes = dureeMax % 60;
    	if (heures <= 1) {
    		duree = duree.substring(0, duree.length() - 1);
    	}
    	if (minutes > 0) {
    		duree = duree + " " + minutes + " minutes";
    	}
    	return (heures + duree);
    }

	public JButton getBtnPayment() {
		return this.btnConfirmer;
	}

	public JTextField getTextField() {
		return this.textField;
	}
}
