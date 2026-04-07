package controleur;

import java.awt.event.ActionListener;
import java.util.List;

import modele.Vehicule;
import modele.dao.DaoVehicule;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import vue.SaisirStationnementBase;

public abstract class ControleurSaisirStationnementBase<V extends SaisirStationnementBase> implements ActionListener {

    protected final V vue;

    protected ControleurSaisirStationnementBase(V vue) {
        this.vue = vue;
    }

    protected void prefillPlaque() {
        try {
            if (AuthManager.getCurrentUser() == null) {
                return;
            }
            MySQLDataSource.creerAcces();
            DaoVehicule daoVehicule = new DaoVehicule();
            int userId = AuthManager.getCurrentUser().getId();
            List<Vehicule> vehicules = daoVehicule.findByUserId(userId);
            Vehicule vehicule = vehicules.isEmpty() ? null : vehicules.getFirst();
            if (vehicule != null) {
                vue.getPlaque().setText(vehicule.getImmatriculation());
            }
        } catch (Exception ignored) {
            // Pré-remplissage non bloquant
        }
    }
}
