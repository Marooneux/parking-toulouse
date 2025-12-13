package modele.dao.requetes;

import modele.Parking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteDeleteParking extends Requete<Parking> {

	// TODO ajouter parametres
	@Override
	public String requete() {
		return "DELETE FROM parkings WHERE id_parking = ?";
	}

    @Override
    public void parametres(PreparedStatement statement, Parking donnee) throws SQLException {
		statement.setInt(1, donnee.getId());
    };

}
