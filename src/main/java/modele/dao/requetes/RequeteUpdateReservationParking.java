package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationParking;

public class RequeteUpdateReservationParking extends Requete<ReservationParking> {

	@Override
	public String requete() {
		return "UPDATE reservations_parking SET date_depart = ?, est_payee = ? WHERE immatriculation = ? AND id_parking = ?";
	}

	@Override
	public void parametres(PreparedStatement ps, ReservationParking r) throws SQLException {
		if (r.getDateDepart() != null) {
			ps.setTimestamp(1, java.sql.Timestamp.valueOf(r.getDateDepart()));
		} else {
			ps.setTimestamp(1, null);
		}
		ps.setBoolean(2, r.estPayee());
		ps.setString(3, r.getImmatriculation());
		ps.setInt(4, r.getParking().getId());
	}
}
