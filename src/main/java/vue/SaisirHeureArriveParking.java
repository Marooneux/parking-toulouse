package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import modele.Parking;
import ui.theme.DefaultTheme;
import vue.PaiementVoirie.LimiteCaracteresFilter;

public class SaisirHeureArriveParking extends SaisirStationnementBase {

	private static final long serialVersionUID = -4863805517212778144L;
	private TemplateSaisie textFieldHeure;
	private Parking parking;
	private JButton btnMaintenant;

	public SaisirHeureArriveParking(Parking parking) {
		this.parking = parking;
		this.buildUI("Enregistrez votre arrivée au parking", "Démarrer le stationnement");
	}

	@Override
	protected String getTitreObjet() {
		return "Parking selectionné";
	}

	@Override
	protected JPanel buildDetailsObjet() {
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

	@Override
	protected String getTitreInput() {
		return "Heure d'arrivée";
	}

	@Override
	protected JPanel buildDetailsInput() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblTitre = new JLabel("Saisissez votre heure d'arrivée (format HH:mm)");
		lblTitre.setFont(DefaultTheme.FONT_LABEL);
		lblTitre.setForeground(new Color(50, 50, 50));
		p.add(lblTitre);

		p.add(Box.createRigidArea(new Dimension(0, 8)));

		JPanel heurePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		heurePanel.setBackground(Color.WHITE);

		this.textFieldHeure = new TemplateSaisie("Heure", "hh:mm", false, false);
		((AbstractDocument) this.textFieldHeure.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(5));
		this.textFieldHeure.getField().setPreferredSize(new Dimension(200, 30));
		heurePanel.add(this.textFieldHeure);

		this.btnMaintenant = new JButton("Maintenant");
		this.btnMaintenant.setFont(DefaultTheme.FONT_HERO_HINT);
		this.btnMaintenant.setBackground(new Color(220, 220, 220));
		this.btnMaintenant.setFocusPainted(false);
		this.btnMaintenant.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnMaintenant.setPreferredSize(new Dimension(100, 30));
		heurePanel.add(this.btnMaintenant);

		p.add(heurePanel);
		return p;
	}

	public JTextField getTextFieldHeure() {
		return this.textFieldHeure.getField();
	}

	public JButton getBtnMaintenant() {
		return this.btnMaintenant;
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
