package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import modele.ReservationVoirie;
import modele.dao.requetes.RequeteDeleteReservationVoirie;
import modele.dao.requetes.RequeteInsertReservationVoirie;
import modele.dao.requetes.RequeteSelectReservationVoirie;
import modele.dao.requetes.RequeteSelectReservationVoirieById;
import modele.dao.requetes.RequeteUpdateReservationVoirie;

public class DaoReservationVoirie extends DaoModele<ReservationVoirie> {
	@Override
	public void create(ReservationVoirie donnee) throws SQLException {
		this.miseAJour(new RequeteInsertReservationVoirie(), donnee);
	}

	@Override
	public void update(ReservationVoirie donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateReservationVoirie(), donnee);
	}

	@Override
	public void delete(ReservationVoirie donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteReservationVoirie(), donnee);
	}

	@Override
	public List<ReservationVoirie> findAll() throws SQLException {
		return this.find(new RequeteSelectReservationVoirie());
	}

	public ReservationVoirie findById(String immatriculation) throws SQLException {
		return this.findById(new RequeteSelectReservationVoirieById(), immatriculation);
	}

	@Override
	protected ReservationVoirie creerInstance(ResultSet curseur) throws SQLException {
		String immatriculation = curseur.getString("immatriculation");
		String type = curseur.getString("type_vehicule");
		LocalDateTime debut = curseur.getTimestamp("date_debut").toLocalDateTime();
		int duree = curseur.getInt("duree_minutes");
		int idZone = curseur.getInt("id_zone");
		int idUser = curseur.getInt("id_utilisateur");
		return new ReservationVoirie(immatriculation, type, debut, duree, idZone, idUser);
	}
}