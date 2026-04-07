package vue;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;

import modele.ZoneVoirie;
import vue.PaiementCarte.LimiteCaracteresFilter;

public class SaisirDureeStationnement extends JPanel {

	private static final long serialVersionUID = -1213099304739348672L;
	private JButton btnConfirmer;
	private TemplateSaisie textFieldPlaque;
	private TemplateSaisie textFieldNom;
	private ZoneVoirie zone;
	private String defaultFont = "Segoe UI";
	private String defaultEmojiFont = "Segoe UI Emoji";

	public SaisirDureeStationnement(ZoneVoirie zone) {
		this.zone = zone;
		this.setLayout(new BorderLayout(15, 15));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setBackground(new Color(250, 250, 250));
		if (zone.getCouleur().equals("bleue")) {
			this.btnConfirmer = new JButton("Confirmer votre stationnement");
		} else {
			this.btnConfirmer = new JButton("Continuer vers le paiement");
		}

		// Entête
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(new Color(250, 250, 250));

		JLabel lblIcon = new JLabel("\uD83C\uDFE2");
		lblIcon.setFont(new Font(this.defaultEmojiFont, Font.PLAIN, 28));
		header.add(lblIcon);

		JPanel texte = new JPanel();
		texte.setBackground(new Color(250, 250, 250));
		texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Démarrer le Stationnement");
		lblTitre.setFont(new Font(this.defaultFont, Font.BOLD, 22));
		lblTitre.setForeground(new Color(40, 40, 40));
		texte.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Veuillez confirmez votre stationnement en voirie");
		lblSousTitre.setFont(new Font(this.defaultFont, Font.PLAIN, 14));
		lblSousTitre.setForeground(new Color(100, 100, 100));
		texte.add(lblSousTitre);

		header.add(texte);
		this.add(header, BorderLayout.NORTH);

		// Corps
		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(new Color(250, 250, 250));
		body.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.add(body, BorderLayout.CENTER);

		// Création des champs de details du stationnement
		body.add(UICard.create("Zone sélectionné", this.detailsZone()));
		body.add(Box.createRigidArea(new Dimension(0, 10)));
		body.add(UICard.create("Informations du véhicule", this.detailsVoiture()));
		body.add(Box.createRigidArea(new Dimension(0, 10)));
		body.add(UICard.create("Durée du stationnement", this.detailsDureeStationnement()));
		body.add(Box.createRigidArea(new Dimension(0, 10)));
	
		// Button Payer
		// ? Créer un prototype que peut être utilisé et changé partout dans l'appli ?
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(250, 250, 250));

		this.btnConfirmer = UIButtons.primary("Démarrer le stationnement");
		this.btnConfirmer.setPreferredSize(new Dimension(250, 50));
		this.btnConfirmer.setOpaque(true);

		buttonPanel.add(this.btnConfirmer);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JPanel detailsZone() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		String minsGratuites = "";
		if (this.zone.getCouleur().equals("rouge")) {
			minsGratuites = " (30 minutes gratuites)";
		}
		JLabel lblZone = new JLabel("Zone " + this.zone.getCouleur() + minsGratuites);
		lblZone.setFont(new Font(this.defaultFont, Font.PLAIN, 15));
		lblZone.setForeground(new Color(50, 50, 50));
		p.add(lblZone);

		JLabel lblDureeMax = new JLabel("Durée maximum : " + this.zone.minsToHeures());
		lblDureeMax.setFont(new Font(this.defaultFont, Font.PLAIN, 15));
		lblDureeMax.setForeground(new Color(50, 50, 50));
		p.add(lblDureeMax);

		return p;
	}

	private JPanel detailsVoiture() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblInfoVehicule = new JLabel(
				"Entrez la plaque d'immatriculation de votre véhicule avec le format suivant");
		lblInfoVehicule.setFont(new Font(this.defaultFont, Font.PLAIN, 15));
		lblInfoVehicule.setForeground(new Color(50, 50, 50));
		p.add(lblInfoVehicule);

		this.textFieldPlaque = new TemplateSaisie("Plaque", "AB-001-CD", false, false);
		((AbstractDocument) this.textFieldPlaque.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(20));
		this.textFieldPlaque.getField().setPreferredSize(new Dimension(250, 30));
		p.add(this.textFieldPlaque);

		JLabel lblInfoImatricule = new JLabel(
				"Vous serez susceptible de reçevoir une amende si la plaque indiquée n'est pas la bonne");
		lblInfoImatricule.setFont(new Font(this.defaultFont, Font.PLAIN, 12));
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
		lblTitreHeureArrive.setFont(new Font(this.defaultFont, Font.PLAIN, 15));
		lblTitreHeureArrive.setForeground(new Color(50, 50, 50));
		p.add(lblTitreHeureArrive);

		p.add(Box.createRigidArea(new Dimension(0, 8)));

		this.textFieldNom = new TemplateSaisie("Duree", "minutes", false, false);
		((AbstractDocument) this.textFieldNom.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(20));
		this.textFieldNom.getField().setPreferredSize(new Dimension(250, 30));
		p.add(this.textFieldNom);

		return p;
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

	public JButton getBtnConfirmer() {
		return this.btnConfirmer;
	}

	public String getDureeSaisie() {
		return this.textFieldNom.getText();
	}

	public String getImmatriculation() {
		return this.textFieldPlaque.getText();
	}

	public JTextComponent getPlaque() {
		return this.textFieldPlaque.getField();
	}

	public ZoneVoirie getZone() {
		return this.zone;
	}
}
