package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import modele.ReservationVoirie;
import modele.Utilisateur;
import modele.ZoneVoirie;
import modele.dao.requetes.RequeteDeleteReservationVoirie;
import modele.dao.requetes.RequeteInsertReservationVoirie;
import modele.dao.requetes.RequeteSelectReservationVoirie;
import modele.dao.requetes.RequeteSelectReservationVoirieById;
import modele.dao.requetes.RequeteSelectReservationsVoirieByUserId;
import modele.dao.requetes.RequeteUpdateReservationVoirie;

public class DaoReservationVoirie extends DaoModele<ReservationVoirie> {

	private DaoZoneVoirie daoZone = new DaoZoneVoirie();
	private DaoUtilisateur daoUtilisateur = new DaoUtilisateur();

	@Override
	public void create(ReservationVoirie donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(
				new RequeteInsertReservationVoirie(), donnee);
		donnee.setId(id);
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

		public List<ReservationVoirie> findByUserId(int userId) throws SQLException {
			return this.find(new RequeteSelectReservationsVoirieByUserId(), String.valueOf(userId));
		}

	public ReservationVoirie findById(int id) throws SQLException {
		return this.findById(
				new RequeteSelectReservationVoirieById(),
				String.valueOf(id));
	}

	@Override
	protected ReservationVoirie creerInstance(ResultSet curseur) throws SQLException {

		int id = curseur.getInt("id");
		Timestamp tsDebut = curseur.getTimestamp("date_debut");
		int duree = curseur.getInt("duree_minutes");
		int idZone = curseur.getInt("id_zone");
		int idUtilisateur = curseur.getInt("id_utilisateur");

		ZoneVoirie zone = this.daoZone.findById(idZone);
		Utilisateur utilisateur = this.daoUtilisateur.findById(idUtilisateur);

		return new ReservationVoirie(
				id,
				tsDebut.toLocalDateTime(),
				duree,
				zone,
				utilisateur);
	}
}