package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
        
        // --- CORRECTION 1 : AJOUT DU LISTENER ---
        // Sans ça, le clic ne déclenche rien.
        // Assure-toi que ta vue a bien un getter getBtnConnexion()
        this.vue.getBtnConnexion().addActionListener(this);
        
        // Optionnel : Permet de valider avec "Entrée" dans le champ mot de passe
        // this.vue.getChampMotDePasse().addActionListener(this); 
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
            JOptionPane.showMessageDialog(vue, "Utilisateur ou mot de passe invalide");
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