package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import vue.ChoixTypeStationnement;
import vue.LoginPage;
import vue.NavigationFrame;
import vue.adminParking.Accueil;

public class ControleurLoginPage implements ActionListener {
    private LoginPage vue;

    public ControleurLoginPage(LoginPage vue) {
        this.vue = vue;
        MySQLDataSource.creerAcces();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Attention : vérifie que ta vue a bien getLogin() et pas getIdentifiant()
        // (J'utilise les noms de ton code fourni)
        String utilisateur = this.vue.getLogin(); 
        
        // Attention : getMdp() renvoie souvent un char[], s'assurer de la conversion
        String mdp = this.vue.getMdp(); 

        boolean ok = AuthManager.login(utilisateur, mdp);
        if (!ok) {
            this.vue.viderChampMdp();
            return;
        }

        Utilisateur user = AuthManager.getCurrentUser();

        // --- CORRECTION 2 : REDIRECTION ADMIN COMPLETE ---
        if (AuthManager.hasRole(Type.SYSADMIN.name(), Type.PARKINGADMIN.name())) {
            
            // 1. Créer la vue
            Accueil vueAdmin = new Accueil(user.getId());
            
            // 2. Créer le contrôleur (IMPORTANT : sinon les boutons de l'accueil ne feront rien)
            new ControleurAccueilAdminParking(vueAdmin, user.getId());
            
            // 3. Afficher la page via le système de navigation
            NavigationFrame.getInstance().showPage("admin-home", () -> vueAdmin, "Administration");
            
            return;
        }

        // Redirection Utilisateur normal
        NavigationFrame.getInstance().showPage("Stationnement", () -> new ChoixTypeStationnement(user.getId()),
                "Stationnement");
    }
}