package controleur;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

import modele.Adresse;
import modele.Parking;
import modele.dao.DaoAdminParking;
import modele.dao.DaoAdresse;
import modele.dao.DaoParking;
import modele.dao.requetes.RequeteInsertAdminParking;
import vue.NavigationFrame;
import vue.adminParking.Accueil;
import vue.adminParking.AjouterParking;

public class ControleurAjouterParking {

    private AjouterParking vue;
    private DaoParking daoParking;
    private DaoAdresse daoAdresse;
    private DaoAdminParking daoAdminParking;
    private int idAdmin;

    public ControleurAjouterParking(AjouterParking vue, int idAdmin) {
        this.vue = vue;
        this.idAdmin = idAdmin;
        this.daoParking = new DaoParking();
        this.daoAdresse = new DaoAdresse();
        this.daoAdminParking = new DaoAdminParking();
        initListeners();
    }

    private void initListeners() {
        vue.getBtnAnnuler().addActionListener(e -> fermer());
        vue.getBtnValider().addActionListener(e -> validerAjout());
    }

    private void fermer() {
        Accueil vueChoix = new Accueil(idAdmin);
        new ControleurAccueilAdminParking(vueChoix, idAdmin);
        NavigationFrame.getInstance().showPage("admin-home", () -> vueChoix, "Administration");
    }

    private void validerAjout() {
        try {
            Adresse adresse = new Adresse(0, null, 0, null);
            
            try {
                adresse.setNumero(Integer.parseInt(vue.getNumero()));
                adresse.setCodePostal(Integer.parseInt(vue.getCodePostal()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vue, "Numéro de rue et Code Postal doivent être des entiers.");
                return;
            }
            
            adresse.setRue(vue.getRue());
            adresse.setVille(vue.getVille());

            Parking parking = new Parking(null, 0, 0, null, null, false, adresse, 0);
            parking.setNom(vue.getNom());
            parking.setAdresse(adresse);
            
            try {
                parking.setTarif(Double.parseDouble(vue.getTarif()));
                parking.setHauteurMax(Double.parseDouble(vue.getHauteur()));
                parking.setCapacite(Integer.parseInt(vue.getPlacesMax()));
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vue, "Vérifiez les champs numériques (Tarif, Hauteur, Places).");
                return;
            }

            try {
                String sOuv = vue.getHeureOuverture();
                String sFerm = vue.getHeureFermeture();
                
                if(sOuv != null && !sOuv.isBlank()) parking.setHoraireOuverture(LocalTime.parse(sOuv));
                if(sFerm != null && !sFerm.isBlank()) parking.setHoraireFermeture(LocalTime.parse(sFerm));
                
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(vue, "Format d'heure invalide. Utilisez HH:mm (ex: 08:00)");
                return;
            }
            
            parking.setContientPlacesMoto(vue.isContientPlacesMoto());

            daoAdresse.create(adresse);
            daoParking.create(parking);
            
            RequeteInsertAdminParking.Association asso = 
            		new modele.dao.requetes.RequeteInsertAdminParking.Association(idAdmin, parking.getId());
            daoAdminParking.create(asso);
            
            JOptionPane.showMessageDialog(vue, "Parking ajouté avec succès !");
            fermer();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ajout : " + ex.getMessage());
        }
    }
}