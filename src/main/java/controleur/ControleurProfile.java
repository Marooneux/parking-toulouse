package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Utilisateur;
import vue.ChoixTypeStationnement;
import vue.ModifierProfile;
import vue.NavigationFrame;
import vue.Profile;

public class ControleurProfile implements ActionListener {

    private final Utilisateur utilisateur;
    private final Profile vue;

    public ControleurProfile(Utilisateur utilisateur, Profile vue) {
        this.utilisateur = utilisateur;
        this.vue = vue;

        initController();
    }

    private void initController() {
        rafraichirVueInfos();
        vue.addEditListener(this);
        vue.addSaveListener(this);
        vue.addAnnulerEditListener(this);
        vue.addMenuInfosListener(this);
        vue.addMenuHistoriqueListener(this);
        vue.addRetourListener(this);
        vue.addToggleSidebarListener(this);

        vue.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vue.getBtnModifierInfos()) {
            vue.fillEditForm(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMdp());
            vue.showEditionTab();
            return;
        }

        if (source == vue.getBtnEnregistrer()) {
            sauvegarder();
            return;
        }

        if (source == vue.getBtnAnnulerEdit()) {
            vue.showInfosTab();
            return;
        }

        if (source == vue.getBtnSidebarInfos()) {
            vue.showInfosTab();
            return;
        }

        if (source == vue.getBtnSidebarHistorique()) {
            vue.showHistoriqueTab();
            return;
        }

        if (source == vue.getBtnRetour()) {
            ouvrirChoixStationnement();
            return;
        }

        if (source == vue.getBtnToggle()) {
            vue.toggleSidebarState();
        }
    }

    public void modifierInfos() {
        ModifierProfile pageModif = new ModifierProfile();
        new ControleurModifierProfile(pageModif, utilisateur);
        NavigationFrame.getInstance().showPage("profile-edit", () -> pageModif, "Modifier le profil");
    }

    public void ouvrirChoixStationnement() {
        NavigationFrame.getInstance().showPage("Stationnement",
                () -> new ChoixTypeStationnement(utilisateur.getId()),
                "Stationnement");
    }

    private void rafraichirVueInfos() {
        vue.updateInfoDisplay(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail());
    }

    private void sauvegarder() {
        String nom = vue.getNomInput();
        String prenom = vue.getPrenomInput();
        String email = vue.getEmailInput();
        String mdp = vue.getMdpInput();

        if (nom.isEmpty() || email.isEmpty()) {
            vue.afficherMessage("Erreur : Champs obligatoires manquants.");
            return;
        }

        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setMdp("temp", mdp);

        boolean ok = true;

        if (ok) {
            vue.afficherMessage("Profil mis à jour avec succès !");
            rafraichirVueInfos();
            vue.showInfosTab();
        } else {
            vue.afficherMessage("Erreur BDD");
        }
    }
    
}