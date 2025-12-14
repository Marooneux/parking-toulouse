package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import modele.ReservationVoirie;
import modele.dao.requetes.RequeteDeleteReservationVoirie;
import modele.dao.requetes.RequeteInsertReservationVoirie;
import modele.dao.requetes.RequeteSelectReservationVoirie;
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

	@Override
	protected ReservationVoirie creerInstance(ResultSet c) throws SQLException {
		int id = c.getInt("id");
		String imm = c.getString("immatriculation");
		String type = c.getString("type_vehicule");
		Timestamp ts = c.getTimestamp("date_debut");
		LocalDateTime debut = ts != null ? ts.toLocalDateTime() : null;
		int duree = c.getInt("duree_minutes");
		int idZone = c.getInt("id_zone");
		int idUser = c.getInt("id_utilisateur");
		return new ReservationVoirie(id, imm, type, debut, duree, idZone, idUser);
	}
}