package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Zone;
import modele.dao.requetes.RequeteDeleteZone;
import modele.dao.requetes.RequeteInsertZone;
import modele.dao.requetes.RequeteSelectZonesVoirie;
import modele.dao.requetes.RequeteUpdateZone;

public class DaoZone extends DaoModele<Zone> {
	@Override
	public void create(Zone donnee) throws SQLException {
		this.miseAJour(new RequeteInsertZone(), donnee);
	}

	@Override
	public void update(Zone donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateZone(), donnee);
	}

	@Override
	public void delete(Zone donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteZone(), donnee);
	}

	@Override
	public List<Zone> findAll() throws SQLException {
		return this.find(new RequeteSelectZonesVoirie());
	}

	@Override
	protected Zone creerInstance(ResultSet curseur) throws SQLException {
		int id = curseur.getInt("id");
		String nom = curseur.getString("nom");
		double tarif = curseur.getDouble("tarif_horaire");
		return new Zone(id, nom, tarif);
	}
}