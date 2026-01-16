package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import modele.Parking;
import modele.ReservationParking;
import modele.Utilisateur;
import modele.dao.requetes.RequeteDeleteReservationParking;
import modele.dao.requetes.RequeteInsertReservationParking;
import modele.dao.requetes.RequeteSelectReservationParking;
import modele.dao.requetes.RequeteSelectReservationParkingById;
import modele.dao.requetes.RequeteSelectReservationsParkingByUserId;
import modele.dao.requetes.RequeteUpdateReservationParking;

public class DaoReservationParking extends DaoModele<ReservationParking> {

	private final DaoParking daoParking = new DaoParking();
	private final DaoUtilisateur daoUtilisateur = new DaoUtilisateur();

	@Override
	public void create(ReservationParking donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(new RequeteInsertReservationParking(), donnee);
		donnee.setId(id);
	}

	@Override
	public void update(ReservationParking donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateReservationParking(), donnee);
	}

	@Override
	public void delete(ReservationParking donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteReservationParking(), donnee);
	}

	@Override
	public List<ReservationParking> findAll() throws SQLException {
		return this.find(new RequeteSelectReservationParking());
	}

	public List<ReservationParking> findByUserId(int userId) throws SQLException {
		return this.find(new RequeteSelectReservationsParkingByUserId(), String.valueOf(userId));
	}

	public ReservationParking findById(int id) throws SQLException {
		return this.findById(new RequeteSelectReservationParkingById(), String.valueOf(id));
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
