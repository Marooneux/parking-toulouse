package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Utilisateur;
import modele.dao.DaoUtilisateur;
import vue.ModifierProfile;
import vue.Profile;
import vue.NavigationFrame;

public class ControleurModifierProfile {

    private ModifierProfile vue;
    private Utilisateur utilisateur;

    public ControleurModifierProfile(ModifierProfile vue, Utilisateur utilisateur) {
        this.vue = vue;
        this.utilisateur = utilisateur;

        // 1. Pré-remplissage immédiat à l'ouverture
        this.vue.afficherUtilisateur(utilisateur);

        // 2. Gestion du bouton Enregistrer
        this.vue.addEnregistrerListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterEnregistrement();
            }
        });

        // 3. Gestion du bouton Annuler
        this.vue.addAnnulerListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherProfil();
            }
        });
    }

    private void traiterEnregistrement() {
        String nouveauNom = vue.getNomInput();
        String nouveauPrenom = vue.getPrenomInput();
        String nouvelEmail = vue.getEmailInput();
        String ancienMdp = vue.getAncienMdpInput();
        String nouveauMdp = vue.getNouveauMdpInput();

        // Validation basique
        if (nouveauNom.isEmpty() || nouvelEmail.isEmpty()) {
            vue.afficherMessage("Erreur : Le nom et l'email ne peuvent pas être vides.");
            return;
        }

        if (!nouveauMdp.isEmpty()) {
            
            // 1. On utilise PasswordUtil pour comparer le mot de passe en clair avec le hash
            if (!utils.PasswordUtil.checkMdp(ancienMdp, utilisateur.getMdp())) {
                vue.afficherMessage("Erreur : L'ancien mot de passe est incorrect.");
                return;
            }
            
            String nouveauMdpHash = utils.PasswordUtil.hashMdp(nouveauMdp);
            utilisateur.setMdp(ancienMdp, nouveauMdpHash);
        }

        // Mise à jour des autres infos dans l'objet
        utilisateur.setNom(nouveauNom);
        utilisateur.setPrenom(nouveauPrenom);
        utilisateur.setEmail(nouvelEmail);

        try {
        	DaoUtilisateur daoUtilisateur = new DaoUtilisateur();
			daoUtilisateur.update(utilisateur);

        
            vue.afficherMessage("Succès : Vos informations ont été mises à jour.");
            afficherProfil();
        } catch (Exception e) {
        	System.out.println(e);
            vue.afficherMessage("Erreur : Impossible de mettre à jour la base de données.");
        }
    }

    private void afficherProfil() {
        Profile profile = new Profile(utilisateur);
        new ControleurProfile(utilisateur, profile);
        NavigationFrame.getInstance().showPage("profile", () -> profile, "Profil");
    }
}