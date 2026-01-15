package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.ZoneVoirie;
import modele.dao.requetes.RequeteDeleteZoneVoirie;
import modele.dao.requetes.RequeteInsertZoneVoirie;
import modele.dao.requetes.RequeteSelectZoneVoirie;
import modele.dao.requetes.RequeteSelectZoneVoirieById;
import modele.dao.requetes.RequeteUpdateZoneVoirie;

public class DaoZoneVoirie extends DaoModele<ZoneVoirie> {

	@Override
	public void create(ZoneVoirie donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(new RequeteInsertZoneVoirie(), donnee);
		donnee.setId(id);
	}

	@Override
	public void update(ZoneVoirie donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateZoneVoirie(), donnee);
	}

	@Override
	public void delete(ZoneVoirie donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteZoneVoirie(), donnee);
	}

	@Override
	public List<ZoneVoirie> findAll() throws SQLException {
		return this.find(new RequeteSelectZoneVoirie());
	}

	public ZoneVoirie findById(int id) throws SQLException {
		return this.findById(new RequeteSelectZoneVoirieById(), String.valueOf(id));
	}

	@Override
	protected ZoneVoirie creerInstance(ResultSet curseur) throws SQLException {
		int id = curseur.getInt("id");
		String nom = curseur.getString("nom");
		double tarifHoraire = curseur.getDouble("tarif_horaire");
		int dureeMax = curseur.getInt("duree_max");

		return new ZoneVoirie(id, nom, tarifHoraire, dureeMax);
	}
}