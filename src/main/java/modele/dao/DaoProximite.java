package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Proximite;
import modele.dao.requetes.RequeteDeleteProximite;
import modele.dao.requetes.RequeteInsertProximite;
import modele.dao.requetes.RequeteSelectProximite;
import modele.dao.requetes.RequeteUpdateProximite;

public class DaoProximite extends DaoModele<Proximite> {
	@Override
	public void create(Proximite donnee) throws SQLException {
		this.miseAJour(new RequeteInsertProximite(), donnee);
	}

	@Override
	public void update(Proximite donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateProximite(), donnee);
	}

	@Override
	public void delete(Proximite donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteProximite(), donnee);
	}

	@Override
	public List<Proximite> findAll() throws SQLException {
		return this.find(new RequeteSelectProximite());
	}

	@Override
	protected Proximite creerInstance(ResultSet c) throws SQLException {
		return new Proximite(
				c.getInt("id_parking"),
				c.getInt("id_ligne_metro"),
				c.getInt("distance_metres"));
	}
}