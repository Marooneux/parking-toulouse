package controleur;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

import modele.Adresse;
import modele.Parking;
import modele.dao.DaoAdresse;
import modele.dao.DaoParking;
import vue.NavigationFrame;
import vue.adminParking.Accueil;
import vue.adminParking.ModifierParking;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurModifierParking {

    private ModifierParking vue;
    private Parking parking;
    private DaoParking daoParking;
    private DaoAdresse daoAdresse;
    private int idAdmin;

    public ControleurModifierParking(ModifierParking vue, Parking parking, int idAdmin) {
        this.vue = vue;
        this.parking = parking;
        this.daoParking = new DaoParking();
        this.daoAdresse = new DaoAdresse();
        this.idAdmin = idAdmin;
        initListeners();
    }

    private void initListeners() {
        vue.getBtnAnnuler().addActionListener(e -> fermer());
        vue.getBtnValider().addActionListener(e -> valider());
    }

    private void fermer() {
        // Retour à l'accueil administrateur
        Accueil vueChoix = new Accueil(idAdmin);
        new ControleurAccueilAdminParking(vueChoix, idAdmin);
        NavigationFrame.getInstance().showPage("admin-home", () -> vueChoix, "Administration");
    }

    private void valider() {
        try {
            // --- 1. Mise à jour des informations générales ---
            parking.setNom(vue.getNom());
            parking.setTarif(vue.getTarif());
            parking.setCapacite(vue.getPlacesMax());
            parking.setHauteurMax(vue.getHauteur());
            parking.setContientPlacesMoto(vue.isContientPlacesMoto());

            // --- 2. Mise à jour de l'Adresse ---
            Adresse adr = parking.getAdresse();
            if (adr == null) {
                adr = new Adresse(0, null, 0, null); // Création si elle n'existait pas
            }

            try {
                // Conversion des champs numériques de l'adresse
                adr.setNumero(Integer.parseInt(vue.getNumero()));
                adr.setCodePostal(Integer.parseInt(vue.getCodePostal()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vue, "Le numéro de rue et le code postal doivent être des nombres entiers valides.");
                return; // On arrête la validation ici
            }

            adr.setRue(vue.getRue());
            adr.setVille(vue.getVille());
            
            // On rattache l'adresse mise à jour au parking
            parking.setAdresse(adr);

            // --- 3. Mise à jour des Horaires ---
            try {
                String sOuverture = vue.getHeureOuverture();
                String sFermeture = vue.getHeureFermeture();

                // On ne parse que si le champ n'est pas vide
                if (sOuverture != null && !sOuverture.isBlank()) {
                    parking.setHoraireOuverture(LocalTime.parse(sOuverture));
                }
                
                if (sFermeture != null && !sFermeture.isBlank()) {
                    parking.setHoraireFermeture(LocalTime.parse(sFermeture));
                }
            } catch (DateTimeParseException dtpe) {
                JOptionPane.showMessageDialog(vue, "Format d'heure incorrect. Veuillez utiliser le format HH:mm (exemple : 08:30).");
                return; // On arrête la validation ici
            }

            // --- 4. Enregistrement en base de données ---
            daoParking.update(parking);
            daoAdresse.update(adr);

            // Message de succès et navigation
            JOptionPane.showMessageDialog(vue, "Parking modifié avec succès");
            fermer();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JOptionPane.showMessageDialog(vue, "Erreur critique lors de la modification : " + ex.getMessage());
        }
    }
}