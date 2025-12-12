package modele.dao.requetes;

import modele.Parking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteDeleteParking extends Requete<Parking> {

	// TODO ajouter parametres
	@Override
	public String requete() {
		return "delete from parkings where idparkings = ?";
	}

    @Override
    public void parametres(PreparedStatement statement, Parking donnee) throws SQLException {
        statement.setInt(1,111);
    };

}
