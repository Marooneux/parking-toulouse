package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.LigneMetro;
import modele.dao.requetes.RequeteDeleteLigneMetro;
import modele.dao.requetes.RequeteInsertLigneMetro;
import modele.dao.requetes.RequeteSelectLignesMetro;
import modele.dao.requetes.RequeteUpdateLigneMetro;

public class DaoLigneMetro extends DaoModele<LigneMetro> {
	@Override
	public void create(LigneMetro donnee) throws SQLException {
		this.miseAJour(new RequeteInsertLigneMetro(), donnee);
	}

	@Override
	public void update(LigneMetro donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateLigneMetro(), donnee);
	}

	@Override
	public void delete(LigneMetro donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteLigneMetro(), donnee);
	}

	@Override
	public List<LigneMetro> findAll() throws SQLException {
		return this.find(new RequeteSelectLignesMetro());
	}

	@Override
	protected LigneMetro creerInstance(ResultSet curseur) throws SQLException {
		return new LigneMetro(
				curseur.getInt("id"),
				curseur.getString("nom"),
				curseur.getString("couleur"));
	}
}