package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Zone;
import modele.dao.requetes.RequeteDeleteZoneVoirie;
import modele.dao.requetes.RequeteInsertZoneVoirie;
import modele.dao.requetes.RequeteSelectZoneVoirie;
import modele.dao.requetes.RequeteUpdateZoneVoirie;

public class DaoZonesVoirie extends DaoModele<Zone> {
	@Override
	public void create(Zone donnee) throws SQLException {
		this.miseAJour(new RequeteInsertZoneVoirie(), donnee);
	}

	@Override
	public void update(Zone donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateZoneVoirie(), donnee);
	}

	@Override
	public void delete(Zone donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteZoneVoirie(), donnee);
	}

	@Override
	public List<Zone> findAll() throws SQLException {
		return this.find(new RequeteSelectZoneVoirie());
	}

	@Override
	protected Zone creerInstance(ResultSet curseur) throws SQLException {
		return new Zone(curseur.getInt("id_zone"),
				curseur.getString("nom"),
				curseur.getDouble("tarif_horaire"));
	}
}