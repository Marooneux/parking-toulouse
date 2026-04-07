package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Adresse;
import modele.Parking;
import ui.theme.DefaultTheme;

public class ModifierParking extends JPanel {

	private static final long serialVersionUID = 5438146851248626756L;

	private final transient Parking parking;

	private TemplateSaisie groupNom;

	private TemplateSaisie groupNumero;
	private TemplateSaisie groupRue;
	private TemplateSaisie groupCP;
	private TemplateSaisie groupVille;

	private TemplateSaisie groupTarif;
	private TemplateSaisie groupHauteur;
	private TemplateSaisie groupPlacesMax;

	private JButton btnValider;
	private JButton btnAnnuler;

	public ModifierParking(Parking parking) {
		this.parking = parking;
		this.initialize();
		this.remplirChamps();
	}

	private void initialize() {
		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setPreferredSize(new Dimension(500, 600));

		JLabel title = new JLabel("Modification du parking");
		title.setFont(DefaultTheme.FONT_TITLE_S);
		this.add(title, BorderLayout.NORTH);

		JPanel form = new JPanel();
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

		this.groupNom = new TemplateSaisie("Nom du parking", "Ex: Parking Central");
		form.add(this.groupNom);
		form.add(Box.createVerticalStrut(10));

		JPanel rowAdresse1 = new JPanel();
		rowAdresse1.setLayout(new BoxLayout(rowAdresse1, BoxLayout.X_AXIS));
		rowAdresse1.setAlignmentX(LEFT_ALIGNMENT);

		this.groupNumero = new TemplateSaisie("N°", "Ex: 10");
		this.groupNumero.setMaximumSize(new Dimension(80, 60));

		this.groupRue = new TemplateSaisie("Rue", "Ex: Rue de la République");

		rowAdresse1.add(this.groupNumero);
		rowAdresse1.add(Box.createHorizontalStrut(10));
		rowAdresse1.add(this.groupRue);

		form.add(rowAdresse1);
		form.add(Box.createVerticalStrut(10));

		JPanel rowAdresse2 = new JPanel();
		rowAdresse2.setLayout(new BoxLayout(rowAdresse2, BoxLayout.X_AXIS));
		rowAdresse2.setAlignmentX(LEFT_ALIGNMENT);

		this.groupCP = new TemplateSaisie("Code Postal", "Ex: 75000");
		this.groupCP.setMaximumSize(new Dimension(100, 60));

		this.groupVille = new TemplateSaisie("Ville", "Ex: Paris");

		rowAdresse2.add(this.groupCP);
		rowAdresse2.add(Box.createHorizontalStrut(10));
		rowAdresse2.add(this.groupVille);

		form.add(rowAdresse2);
		form.add(Box.createVerticalStrut(10));

		// 4. Autres infos
		this.groupTarif = new TemplateSaisie("Tarif (€/h)", "");
		form.add(this.groupTarif);
		form.add(Box.createVerticalStrut(10));

		this.groupHauteur = new TemplateSaisie("Hauteur max (m)", "");
		form.add(this.groupHauteur);
		form.add(Box.createVerticalStrut(10));

		this.groupPlacesMax = new TemplateSaisie("Places max", "");
		form.add(this.groupPlacesMax);

		this.add(form, BorderLayout.CENTER);

		// --- Boutons ---
		this.btnValider = new JButton("Valider");
		this.btnAnnuler = new JButton("Annuler");

		JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		actions.add(this.btnAnnuler);
		actions.add(this.btnValider);

		this.add(actions, BorderLayout.SOUTH);
	}

	private void remplirChamps() {
		if (this.parking != null) {
			this.groupNom.setText(this.parking.getNom());

			Adresse adr = this.parking.getAdresse();
			if (adr != null) {
				this.groupNumero.setText(String.valueOf(adr.getNumero()));
				this.groupRue.setText(adr.getRue());
				this.groupCP.setText(String.valueOf(adr.getCodePostal()));
				this.groupVille.setText(adr.getVille());
			}

			this.groupTarif.setText(String.valueOf(this.parking.getTarif()));
			this.groupHauteur.setText(String.valueOf(this.parking.getHauteur()));
			this.groupPlacesMax.setText(String.valueOf(this.parking.getNbPlacesMax()));
		}
	}

	// --- Getters ---

	public JButton getBtnValider() {
		return this.btnValider;
	}

	public JButton getBtnAnnuler() {
		return this.btnAnnuler;
	}

	public String getNom() {
		return this.groupNom.getText().trim();
	}

	public String getNumero() {
		return this.groupNumero.getText().trim();
	}

	public String getRue() {
		return this.groupRue.getText().trim();
	}

	public String getCodePostal() {
		return this.groupCP.getText().trim();
	}

	public String getVille() {
		return this.groupVille.getText().trim();
	}

	public double getTarif() {
		try {
			return Double.parseDouble(this.groupTarif.getText().trim());
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}

	public double getHauteur() {
		try {
			return Double.parseDouble(this.groupHauteur.getText().trim());
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}

	public int getPlacesMax() {
		try {
			return Integer.parseInt(this.groupPlacesMax.getText().trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}