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

    private TemplateSaisie txtNom;
    private TemplateSaisie txtPrenom;
    private TemplateSaisie txtEmail;
    private TemplateSaisie txtAncienMdp;
    private TemplateSaisie txtNouveauMdp;
    private JButton btnEnregistrer;
    private JButton btnAnnuler;

    public ModifierProfile() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 380));

        txtNom = new TemplateSaisie("Nom", "Nom", false);
        txtPrenom = new TemplateSaisie("Prenom", "Prenom", false);
        txtEmail = new TemplateSaisie("Email", "email@exemple.fr", false);
        txtAncienMdp = new TemplateSaisie("Ancien mot de passe", "", true);
        txtNouveauMdp = new TemplateSaisie("Nouveau mot de passe", "", true);

        add(txtNom);
        add(Box.createVerticalStrut(12));
        add(txtPrenom);
        add(Box.createVerticalStrut(12));
        add(txtEmail);
        add(Box.createVerticalStrut(12));
        add(txtAncienMdp);
        add(Box.createVerticalStrut(12));
        add(txtNouveauMdp);
        add(Box.createVerticalStrut(20));

        btnEnregistrer = new JButton("Enregistrer");
        btnAnnuler = new JButton("Annuler");

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonRow.setOpaque(false);
        buttonRow.add(btnEnregistrer);
        buttonRow.add(btnAnnuler);
        add(buttonRow);
    }

    // --- Methodes pour le Controleur ---

    // Pre-remplit les champs (sauf les mots de passe pour securite)
    public void afficherUtilisateur(Utilisateur user) {
        txtNom.setText(user.getNom());
        txtPrenom.setText(user.getPrenom());
        txtEmail.setText(user.getEmail());
        txtAncienMdp.setText("");
        txtNouveauMdp.setText("");
    }

    // Getters
    public String getNomInput() { return txtNom.getText(); }
    public String getPrenomInput() { return txtPrenom.getText(); }
    public String getEmailInput() { return txtEmail.getText(); }
    public String getAncienMdpInput() { return new String(txtAncienMdp.getPassword()); }
    public String getNouveauMdpInput() { return new String(txtNouveauMdp.getPassword()); }

    // Listeners
    public void addEnregistrerListener(ActionListener action) {
        btnEnregistrer.addActionListener(action);
    }

    public void addAnnulerListener(ActionListener action) {
        btnAnnuler.addActionListener(action);
    }

    public void afficherMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
