package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;
import vue.InscriptionPage;
import vue.LoginPage;
import vue.NavigationFrame;

public class ControleurInscriptionPage implements ActionListener {

    private final InscriptionPage vue;
    private final DaoUtilisateur dao;

    public ControleurInscriptionPage(InscriptionPage vue) {
        this.vue = vue;
        this.dao = new DaoUtilisateur();
        MySQLDataSource.creerAcces();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.creerCompte();
    }

    private void creerCompte() {
        String nom = this.vue.getNom();
        String prenom = this.vue.getPrenom();
        String email = this.vue.getEmail();
        String mdp = this.vue.getMotDePasse();
        String mdpConfirm = this.vue.getConfirmationMotDePasse();

        if (nom.isBlank() || prenom.isBlank() || email.isBlank() || mdp.isBlank() || mdpConfirm.isBlank()) {
            JOptionPane.showMessageDialog(this.vue, "Tous les champs sont obligatoires.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this.vue, "Format d'email invalide.");
            return;
        }

        if (mdp.length() < 8) {
            JOptionPane.showMessageDialog(this.vue, "Le mot de passe doit contenir au moins 8 caracteres.");
            return;
        }

        if (!mdp.equals(mdpConfirm)) {
            JOptionPane.showMessageDialog(this.vue, "Les mots de passe ne correspondent pas.");
            return;
        }

        try {
            Utilisateur existant = this.dao.findByEmail(email);
            if (existant != null) {
                JOptionPane.showMessageDialog(this.vue, "Un compte existe deja avec cet email.");
                return;
            }

            Utilisateur nouvelUtilisateur = new Utilisateur(0, nom, prenom, email, "", Type.CLIENT);
            nouvelUtilisateur.setMdp(null, mdp);
            this.dao.create(nouvelUtilisateur);

            JOptionPane.showMessageDialog(this.vue, "Compte cree avec succes. Vous pouvez vous connecter.");
            NavigationFrame.getInstance().showPage("Connexion", LoginPage::new, "Connexion");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.vue, "Erreur lors de la creation du compte : " + ex.getMessage());
        }
    }
}
