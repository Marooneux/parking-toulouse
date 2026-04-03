package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;

import modele.Parking;
import ui.theme.DefaultTheme;
import vue.PaiementCarte.LimiteCaracteresFilter;

public class SaisirHeureArriveParking extends JPanel {

	private static final long serialVersionUID = -4863805517212778144L;
	private JButton btnConfirmer;
	private TemplateSaisie textFieldPlaque;
	private TemplateSaisie textFieldHeure;
	private Parking parking;
	private JButton btnMaintenant;

	public SaisirHeureArriveParking(Parking parking) {
		this.parking = parking;

		this.setLayout(new BorderLayout(15, 15));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setBackground(new Color(250, 250, 250));

		this.btnConfirmer = new JButton("Démarrer le stationnement");

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(new Color(250, 250, 250));

		JLabel lblIcon = new JLabel("\uD83C\uDFE2");
		lblIcon.setFont(DefaultTheme.FONT_ICON);
		header.add(lblIcon);

		JPanel texte = new JPanel();
		texte.setBackground(new Color(250, 250, 250));
		texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Démarrer le Stationnement");
		lblTitre.setFont(DefaultTheme.FONT_TITLE);
		lblTitre.setForeground(new Color(40, 40, 40));
		texte.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Enregistrez votre arrivée au parking");
		lblSousTitre.setFont(DefaultTheme.FONT_BODY);
		lblSousTitre.setForeground(new Color(100, 100, 100));
		texte.add(lblSousTitre);

		header.add(texte);
		this.add(header, BorderLayout.NORTH);

		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(new Color(250, 250, 250));
		body.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.add(body, BorderLayout.CENTER);

		this.ajouterCarteDeDetails(body, "Parking selectionné", this.detailsZone());
		this.ajouterCarteDeDetails(body, "Informations du véhicule", this.detailsVoiture());
		this.ajouterCarteDeDetails(body, "Heure d'arrivée", this.detailsHeureArrivee());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(250, 250, 250));

		this.btnConfirmer.setFont(DefaultTheme.FONT_TITLE_XS);
		this.btnConfirmer.setBackground(new Color(0, 122, 255));
		this.btnConfirmer.setForeground(Color.WHITE);
		this.btnConfirmer.setFocusPainted(false);
		this.btnConfirmer.setBorderPainted(false);
		this.btnConfirmer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnConfirmer.setMinimumSize(new Dimension(250, 50));
		this.btnConfirmer.setOpaque(true);

		buttonPanel.add(this.btnConfirmer);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JPanel detailsZone() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblZone = new JLabel(this.parking.getNom());
		lblZone.setFont(DefaultTheme.FONT_LABEL);
		lblZone.setForeground(new Color(50, 50, 50));
		p.add(lblZone);

		JLabel lblHauteur = new JLabel("Hauteur : " + this.parking.getHauteurMax() + "m");
		lblHauteur.setFont(DefaultTheme.FONT_LABEL);
		lblHauteur.setForeground(new Color(50, 50, 50));
		p.add(lblHauteur);

		return p;
	}

	private JPanel detailsVoiture() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblInfoVehicule = new JLabel(
				"Entrez la plaque d'immatriculation de votre véhicule avec le format suivant");
		lblInfoVehicule.setFont(DefaultTheme.FONT_LABEL);
		lblInfoVehicule.setForeground(new Color(50, 50, 50));
		p.add(lblInfoVehicule);

		this.textFieldPlaque = new TemplateSaisie("Plaque", "AB-001-CD", false, false);
		((AbstractDocument) this.textFieldPlaque.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(20));
		this.textFieldPlaque.getField().setPreferredSize(new Dimension(250, 30));
		p.add(this.textFieldPlaque);

		JLabel lblInfoImatricule = new JLabel(
				"Vous serez susceptible de recevoir une amende si la plaque indiquée n'est pas la bonne");
		lblInfoImatricule.setFont(DefaultTheme.FONT_BODY_S);
		lblInfoImatricule.setForeground(new Color(120, 120, 120));
		lblInfoImatricule.setBorder(new EmptyBorder(5, 0, 0, 0));
		p.add(lblInfoImatricule);

		return p;
	}

	private JPanel detailsHeureArrivee() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblTitreHeureArrive = new JLabel("Saisissez votre heure d'arrivée (format HH:mm)");
		lblTitreHeureArrive.setFont(DefaultTheme.FONT_LABEL);
		lblTitreHeureArrive.setForeground(new Color(50, 50, 50));
		p.add(lblTitreHeureArrive);

		p.add(Box.createRigidArea(new Dimension(0, 8)));

		JPanel heurePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		heurePanel.setBackground(Color.WHITE);

		this.textFieldHeure = new TemplateSaisie("Heure", "hh:mm", false, false);
		((AbstractDocument) this.textFieldHeure.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(5));
		this.textFieldHeure.getField().setPreferredSize(new Dimension(200, 30));
		heurePanel.add(this.textFieldHeure);

		this.btnMaintenant = new JButton("Maintenant");
		this.btnMaintenant.setFont(DefaultTheme.FONT_BODY_S);
		this.btnMaintenant.setBackground(new Color(220, 220, 220));
		this.btnMaintenant.setFocusPainted(false);
		this.btnMaintenant.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnMaintenant.setPreferredSize(new Dimension(100, 30));
		heurePanel.add(this.btnMaintenant);

		p.add(heurePanel);
		return p;
	}

	private void ajouterCarteDeDetails(JPanel parent, String title, JPanel innerContent) {
		JPanel card = new JPanel();
		card.setLayout(new BorderLayout());
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0),
				BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true)));

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(Color.WHITE);

		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(DefaultTheme.FONT_BUTTON);
		lblTitle.setForeground(new Color(70, 70, 70));
		header.add(lblTitle);

		card.add(header, BorderLayout.NORTH);
		card.add(innerContent, BorderLayout.CENTER);

		parent.add(card);
		parent.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	public JButton getBtnConfirmer() {
		return this.btnConfirmer;
	}

	public JTextField getTextFieldHeure() {
		return this.textFieldHeure.getField();
	}

	public JTextComponent getPlaque() {
		return this.textFieldPlaque.getField();
	}

	public JButton getBtnMaintenant() {
		return this.btnMaintenant;
	}

	public void addConfirmerListener(java.awt.event.ActionListener listener) {
		this.btnConfirmer.addActionListener(listener);
	}

	public boolean verifierHeure() {
		String valeur = this.textFieldHeure.getText().trim();
		if (valeur.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir une heure d'arrivée avant de continuer.");
			return false;
		}
		DateTimeFormatter strictFormatter = DateTimeFormatter.ofPattern("HH:mm")
				.withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalTime heureArrivee = LocalTime.parse(valeur, strictFormatter);
			if (heureArrivee.isAfter(LocalTime.now())) {
				JOptionPane.showMessageDialog(this, "L'heure d'arrivée doit être antérieure à l'heure actuelle.");
				return false;
			}
			return true;
		} catch (DateTimeParseException ex) {
			JOptionPane.showMessageDialog(this, "L'heure entrée n'est pas au bon format.");
			return false;
		}
	}

	public void reinitialiserChamps() {
		if (this.textFieldPlaque != null && this.textFieldPlaque.getField() != null) {
			this.textFieldPlaque.getField().setText("");
		}
		if (this.textFieldHeure != null && this.textFieldHeure.getField() != null) {
			this.textFieldHeure.getField().setText("");
		}
	}
}
