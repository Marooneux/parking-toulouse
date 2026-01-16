package vue.adminParking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AjouterParking extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNom;
	private JTextField txtAdresse;
	private JTextField txtTarif;
	private JTextField txtHauteur;
	private JTextField txtPlacesMax;
	private JTextField txtPlacesOccupees;
	private JTextField txtHeureOuverture;
	private JTextField txtHeureFermeture;
	private JCheckBox chkMoto;

	private JButton btnValider;
	private JButton btnAnnuler;

	public AjouterParking(int idAdmin) {
		this.initialize();
	}

	private void initialize() {
		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		JPanel content = new JPanel(new BorderLayout(10, 10));
		content.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.add(content, BorderLayout.CENTER);

		JLabel title = new JLabel("Ajout d'un nouveau parking");
		title.setFont(new Font("Segoe UI", Font.BOLD, 18));
		content.add(title, BorderLayout.NORTH);

		JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

		this.txtNom = new JTextField();
		this.txtAdresse = new JTextField();
		this.txtTarif = new JTextField();
		this.txtHauteur = new JTextField();
		this.txtPlacesMax = new JTextField();

		this.txtHeureOuverture = new JTextField();
		this.txtHeureFermeture = new JTextField();

		form.add(new JLabel("Nom"));
		form.add(this.txtNom);

		form.add(new JLabel("Adresse"));
		form.add(this.txtAdresse);

		form.add(new JLabel("Tarif (€/h)"));
		form.add(this.txtTarif);

		form.add(new JLabel("Hauteur max (m)"));
		form.add(this.txtHauteur);

		form.add(new JLabel("Places max"));
		form.add(this.txtPlacesMax);

		form.add(new JLabel("Places occupées"));
		form.add(this.txtPlacesMax);

		form.add(new JLabel("Horaire d'ouverture (hh:mm:ss)"));
		form.add(this.txtHeureOuverture);

		form.add(new JLabel("Horaire de fermeture (hh:mm:ss)"));
		form.add(this.txtHeureFermeture);

		this.chkMoto = new JCheckBox("Places moto");
		this.chkMoto.setSelected(false);
		form.add(this.chkMoto);

		content.add(form, BorderLayout.CENTER);

		this.btnValider = new JButton("Valider");
		this.btnAnnuler = new JButton("Annuler");

		JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		actions.add(this.btnAnnuler);
		actions.add(this.btnValider);

		content.add(actions, BorderLayout.SOUTH);
	}

	public JButton getBtnValider() {
		return this.btnValider;
	}

	public JButton getBtnAnnuler() {
		return this.btnAnnuler;
	}

	public String getNom() {
		return this.txtNom.getText().trim();
	}

	public String getAdresse() {
		return this.txtAdresse.getText().trim();
	}

	public double getTarif() {
		return Double.parseDouble(this.txtTarif.getText().trim());
	}

	public double getHauteur() {
		return Double.parseDouble(this.txtHauteur.getText().trim());
	}

	public int getPlacesMax() {
		return Integer.parseInt(this.txtPlacesMax.getText().trim());
	}

	public int getPlacesOccupees() {
		return Integer.parseInt(this.txtPlacesOccupees.getText().trim());
	}

	public LocalTime getHeureOuverture() {
		return LocalTime.parse(this.txtHeureOuverture.getText());
	}

	public LocalTime getHeureFermeture() {
		return LocalTime.parse(this.txtHeureFermeture.getText());
	}

	public boolean isContientPlacesMoto() {
		return this.chkMoto.isSelected();
	}

}
