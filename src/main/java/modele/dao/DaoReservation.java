package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import modele.Parking;
import modele.ReservationParking;
import modele.Utilisateur;

public class DaoReservation extends DaoModele<ReservationParking> {

	private final DaoParking daoParking = new DaoParking();
	private final DaoUtilisateur daoUtilisateur = new DaoUtilisateur();

	@Override
	public void create(ReservationParking donnees) throws SQLException {
		throw new UnsupportedOperationException("create not implemented");
	}

	@Override
	public void update(ReservationParking donnees) throws SQLException {
		throw new UnsupportedOperationException("update not implemented");
	}

	@Override
	public void delete(ReservationParking donnees) throws SQLException {
		throw new UnsupportedOperationException("delete not implemented");
	}

	@Override
	public List<ReservationParking> findAll() throws SQLException {
		throw new UnsupportedOperationException("findAll not implemented");
	}

	@Override
	protected ReservationParking creerInstance(ResultSet curseur) throws SQLException {
		int id = curseur.getInt("id");
		Timestamp tsArrivee = curseur.getTimestamp("date_arrivee");
		Timestamp tsDepart = curseur.getTimestamp("date_depart");
		int idParking = curseur.getInt("id_parking");
		int idUtilisateur = curseur.getInt("id_utilisateur");

		Parking parking = this.daoParking.findById(idParking);
		Utilisateur utilisateur = this.daoUtilisateur.findById(idUtilisateur);

		ReservationParking reservation = new ReservationParking(
				tsArrivee != null ? tsArrivee.toLocalDateTime() : null,
				tsDepart != null ? tsDepart.toLocalDateTime() : null,
				parking,
				utilisateur);
		reservation.setId(id);
		return reservation;
	}

}