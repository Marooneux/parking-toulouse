package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteInsertAdminParking extends Requete<RequeteInsertAdminParking.Association> {

    public static class Association {
        public int idUtilisateur;
        public int idParking;

        public Association(int idUtilisateur, int idParking) {
            this.idUtilisateur = idUtilisateur;
            this.idParking = idParking;
        }
    }

    @Override
    public String requete() {
        return "INSERT INTO admins_parkings (id_utilisateur, id_parking) VALUES (?, ?)";
    }

    @Override
    public void parametres(PreparedStatement statement, Association donnee) throws SQLException {
        statement.setInt(1, donnee.idUtilisateur);
        statement.setInt(2, donnee.idParking);
    }
}