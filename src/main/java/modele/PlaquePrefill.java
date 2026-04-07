package modele;
import modele.dao.DaoVehicule;
import modele.dao.MySQLDataSource;
import utils.AuthManager;

import javax.swing.text.JTextComponent;
import java.util.List;

public class PlaquePrefill {

    public static void prefill(JTextComponent field) {
        try {
            if (AuthManager.getCurrentUser() == null) return;

            MySQLDataSource.creerAcces();
            DaoVehicule dao = new DaoVehicule();
            int userId = AuthManager.getCurrentUser().getId();

            List<Vehicule> vehicules = dao.findByUserId(userId);
            if (!vehicules.isEmpty()) {
                field.setText(vehicules.getFirst().getImmatriculation());
            }
        } catch (Exception ignored) {}
    }
}
