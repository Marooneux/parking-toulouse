package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Utilisateur;
import vue.ChoixTypeStationnement;
import vue.ModifierProfile;
// import dao.UtilisateurDAO; // N'oublie pas d'importer ton DAO
import vue.Profile;

public class ControleurProfile {

    private Utilisateur utilisateur;
    private Profile vue;
    // private UtilisateurDAO dao;

    public ControleurProfile(Utilisateur utilisateur, Profile vue) {
        this.utilisateur = utilisateur;
        this.vue = vue;
        // this.dao = new UtilisateurDAO();

        initController();
    }

    private void initController() {
        rafraichirVueInfos();
        vue.addEditListener(new ActionListener() {
            
        	@Override
            public void actionPerformed(ActionEvent e) {
            	vue.fillEditForm(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMdp());
            	vue.showEditionTab();
            }
        });

        vue.addSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sauvegarder();
            }
        });
        
        vue.setVisible(true);
    }
    
    
    public void modifierInfos() {
    	ModifierProfile pageModif = new ModifierProfile();
    	new ControleurModifierProfile(pageModif, utilisateur);
    	pageModif.setVisible(true);
    	vue.dispose();
    }
    
    public void ouvrirChoixStationnement() {
    	ChoixTypeStationnement accueil = new ChoixTypeStationnement(2);
    	accueil.setVisible(true);
    	vue.dispose();
    }
    

    private void rafraichirVueInfos() {
    	vue.updateInfoDisplay(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail());
    }

    private void sauvegarder() {
        // Récupérer données
        String nom = vue.getNomInput();
        String prenom = vue.getPrenomInput();
        String email = vue.getEmailInput();
        String mdp = vue.getMdpInput();

        // Validation basique
        if (nom.isEmpty() || email.isEmpty()) {
        	vue.afficherMessage("Erreur : Champs obligatoires manquants.");
            return;
        }

        // Mise à jour Modèle
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setMdp("temp", mdp);

        // Simulation appel DAO
        // boolean ok = dao.update(model);
        boolean ok = true; // Pour le test

        if (ok) {
        	vue.afficherMessage("Profil mis à jour avec succès !");
            rafraichirVueInfos(); // Mettre à jour l'affichage lecture seule
            vue.showInfosTab();  // Revenir sur l'onglet Infos
        } else {
        	vue.afficherMessage("Erreur BDD");
        }
    }
    
}