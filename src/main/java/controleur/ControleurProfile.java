package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Utilisateur;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;
import vue.ChoixTypeStationnement;
import vue.ModifierProfile;
import vue.NavigationFrame;
import vue.Profile;
import vue.VehiculesPanel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurProfile implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurProfile.class.getName());


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
        vue.addMenuVehiculesListener(this);
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

        if (source == vue.getBtnSidebarVehicules()) {
            ouvrirVehicules();
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

    public void ouvrirVehicules() {
        NavigationFrame.getInstance().showPage(
                "Vehicules",
                () -> {
                    VehiculesPanel panel = new VehiculesPanel(utilisateur);
                    new ControleurVehicules(utilisateur, panel);
                    return panel;
                },
                "Mes véhicules");
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
        if (!mdp.isBlank()) {
            utilisateur.setMdp(utilisateur.getMdp(), mdp);
        }

        try {
            MySQLDataSource.creerAcces();
            new DaoUtilisateur().update(utilisateur);
            vue.afficherMessage("Profil mis à jour avec succès !");
            rafraichirVueInfos();
            vue.showInfosTab();
        } catch (Exception ex) {
            vue.afficherMessage("Erreur lors de la mise à jour : " + ex.getMessage());
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
}