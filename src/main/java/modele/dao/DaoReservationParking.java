package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import modele.Parking;
import modele.ReservationParking;
import modele.dao.requetes.RequeteDeleteReservationParking;
import modele.dao.requetes.RequeteInsertReservationParking;
import modele.dao.requetes.RequeteSelectReservationParking;
import modele.dao.requetes.RequeteSelectReservationParkingById;
import modele.dao.requetes.RequeteUpdateReservationParking;

public class DaoReservationParking extends DaoModele<ReservationParking> {

	@Override
	public void create(ReservationParking donnee) throws SQLException {
		this.miseAJour(new RequeteInsertReservationParking(), donnee);
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

	public ReservationParking findById(String immatriculation, int idParking) throws SQLException {
		return this.findById(new RequeteSelectReservationParkingById(),
				immatriculation, String.valueOf(idParking));
	}

	@Override
	protected ReservationParking creerInstance(ResultSet curseur) throws SQLException {
		String immatriculation = curseur.getString("immatriculation");
		LocalDateTime dateArrivee = curseur.getTimestamp("date_arrivee").toLocalDateTime();
		LocalDateTime dateDepart = null;
		if (curseur.getTimestamp("date_depart") != null) {
			dateDepart = curseur.getTimestamp("date_depart").toLocalDateTime();
		}
		int idParking = curseur.getInt("id_parking");
		int idUtilisateur = curseur.getInt("id_utilisateur");

		Parking parking = new DaoParking().findById(idParking);
		ReservationParking r = new ReservationParking(immatriculation, parking, dateArrivee, idUtilisateur);
		if (dateDepart != null) {
			r.setDateDepart(dateDepart);
		}
		r.setEstPayee(curseur.getBoolean("est_payee"));
		return r;
	}
}
