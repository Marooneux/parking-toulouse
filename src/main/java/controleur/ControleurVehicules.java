package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modele.Utilisateur;
import modele.Vehicule;
import modele.Vehicule.TypeVehicule;
import modele.dao.DaoVehicule;
import modele.dao.MySQLDataSource;
import vue.VehiculesPanel;

public class ControleurVehicules implements ActionListener {

    private final Utilisateur utilisateur;
    private final VehiculesPanel vue;
    private final DaoVehicule daoVehicule = new DaoVehicule();
    private List<Vehicule> vehicules = new ArrayList<>();

    public ControleurVehicules(Utilisateur utilisateur, VehiculesPanel vue) {
        this.utilisateur = utilisateur;
        this.vue = vue;
        initListeners();
        rechargerVehicules();
    }

    private void initListeners() {
        vue.getBtnAjouter().addActionListener(this);
        vue.getBtnSupprimer().addActionListener(this);
        vue.getBtnRafraichir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == vue.getBtnAjouter()) {
            ajouterVehicule();
        } else if (src == vue.getBtnSupprimer()) {
            supprimerVehicule();
        } else if (src == vue.getBtnRafraichir()) {
            rechargerVehicules();
        }
    }

    private void rechargerVehicules() {
        try {
            MySQLDataSource.creerAcces();
            vehicules = daoVehicule.findByUserId(utilisateur.getId());
            vue.afficherVehicules(vehicules);
        } catch (Exception ex) {
            vue.afficherMessage("Impossible de charger les véhicules : " + ex.getMessage());
        }
    }

    private void ajouterVehicule() {
        String immat = vue.getImmatriculationInput().toUpperCase();
        TypeVehicule type = vue.getSelectedType();

        if (immat.isEmpty()) {
            vue.afficherMessage("Veuillez saisir une immatriculation.");
            return;
        }
        if (!Vehicule.immatriculationValide(immat)) {
            vue.afficherMessage("Format de plaque invalide. Exemple : AB-123-CD");
            return;
        }
        if (type == null) {
            vue.afficherMessage("Veuillez sélectionner un type de véhicule.");
            return;
        }

        try {
            MySQLDataSource.creerAcces();
            vehicules = daoVehicule.findByUserId(utilisateur.getId());
            if (!vehicules.isEmpty()) {
                Vehicule existant = vehicules.getFirst();
                existant.setImmatriculation(immat);
                existant.setType(type);
                daoVehicule.update(existant);
            } else {
                Vehicule vehicule = new Vehicule(0, immat, type, utilisateur);
                daoVehicule.create(vehicule);
            }
            vue.clearForm();
            rechargerVehicules();
        } catch (SQLException ex) {
            String message = ex.getMessage();
            if (message != null && message.contains("Duplicate")) {
                vue.afficherMessage("Cette immatriculation existe déjà.");
            } else {
                vue.afficherMessage("Erreur lors de l'ajout : " + ex.getMessage());
            }
        } catch (Exception ex) {
            vue.afficherMessage("Erreur lors de l'ajout : " + ex.getMessage());
        }
    }

    private void supprimerVehicule() {
        int index = vue.getSelectedIndex();
        Vehicule cible = vue.getVehiculeAt(index, vehicules);
        if (cible == null) {
            vue.afficherMessage("Sélectionnez un véhicule à supprimer.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(vue, "Supprimer ce véhicule ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            MySQLDataSource.creerAcces();
            daoVehicule.delete(cible);
            rechargerVehicules();
        } catch (Exception ex) {
            vue.afficherMessage("Erreur lors de la suppression : " + ex.getMessage());
        }
    }
}
