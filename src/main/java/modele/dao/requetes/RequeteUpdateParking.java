package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;

import modele.Parking;

public class RequeteUpdateParking extends Requete<Parking> {

	@Override
	public String requete() {
		return "UPDATE parkings SET nom = ?, capacite = ?, hauteur_max = ?, horaire_ouverture = ?, horaire_fermeture = ?, contient_places_moto = ?, id_adresse = ? WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement ps, Parking p) throws SQLException {

		ps.setString(1, p.getNom());
		ps.setInt(2, p.getCapacite());

		if (p.getHauteurMax() != 0.0) {
			ps.setDouble(3, p.getHauteurMax());
		} else {
			ps.setNull(3, Types.DECIMAL);
		}

		if (p.getHoraireOuverture() != null) {
			ps.setTime(4, Time.valueOf(p.getHoraireOuverture()));
		} else {
			ps.setNull(4, Types.TIME);
		}

		if (p.getHoraireFermeture() != null) {
			ps.setTime(5, Time.valueOf(p.getHoraireFermeture()));
		} else {
			ps.setNull(5, Types.TIME);
		}

		ps.setBoolean(6, p.isContientPlacesMoto());
		ps.setInt(7, p.getAdresse().getId());
		ps.setInt(8, p.getId());
	}
}
