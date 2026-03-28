package vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Utilisateur;

public class ModifierProfile extends JPanel {

	private static final long serialVersionUID = 7890505630568538549L;
	private TemplateSaisie txtNom;
	private TemplateSaisie txtPrenom;
	private TemplateSaisie txtEmail;
	private TemplateSaisie txtAncienMdp;
	private TemplateSaisie txtNouveauMdp;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	public ModifierProfile() {
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(500, 380));

		this.txtNom = new TemplateSaisie("Nom", "Nom", false);
		this.txtPrenom = new TemplateSaisie("Prenom", "Prenom", false);
		this.txtEmail = new TemplateSaisie("Email", "email@exemple.fr", false);
		this.txtAncienMdp = new TemplateSaisie("Ancien mot de passe", "", true);
		this.txtNouveauMdp = new TemplateSaisie("Nouveau mot de passe", "", true);

		this.add(this.txtNom);
		this.add(Box.createVerticalStrut(12));
		this.add(this.txtPrenom);
		this.add(Box.createVerticalStrut(12));
		this.add(this.txtEmail);
		this.add(Box.createVerticalStrut(12));
		this.add(this.txtAncienMdp);
		this.add(Box.createVerticalStrut(12));
		this.add(this.txtNouveauMdp);
		this.add(Box.createVerticalStrut(20));

		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnAnnuler = new JButton("Annuler");

		JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonRow.setOpaque(false);
		buttonRow.add(this.btnEnregistrer);
		buttonRow.add(this.btnAnnuler);
		this.add(buttonRow);
	}

	// --- Methodes pour le Controleur ---

	// Pre-remplit les champs (sauf les mots de passe pour securite)
	public void afficherUtilisateur(Utilisateur user) {
		this.txtNom.setText(user.getNom());
		this.txtPrenom.setText(user.getPrenom());
		this.txtEmail.setText(user.getEmail());
		this.txtAncienMdp.setText("");
		this.txtNouveauMdp.setText("");
	}

	// Getters
	public String getNomInput() {
		return this.txtNom.getText();
	}

	public String getPrenomInput() {
		return this.txtPrenom.getText();
	}

	public String getEmailInput() {
		return this.txtEmail.getText();
	}

	public String getAncienMdpInput() {
		return new String(this.txtAncienMdp.getPassword());
	}

	public String getNouveauMdpInput() {
		return new String(this.txtNouveauMdp.getPassword());
	}

	// Listeners
	public void addEnregistrerListener(ActionListener action) {
		this.btnEnregistrer.addActionListener(action);
	}

	public void addAnnulerListener(ActionListener action) {
		this.btnAnnuler.addActionListener(action);
	}

	public void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
}
