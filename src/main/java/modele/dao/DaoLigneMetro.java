package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.LigneMetro;
import modele.dao.requetes.RequeteDeleteLignesMetro;
import modele.dao.requetes.RequeteInsertLignesMetro;
import modele.dao.requetes.RequeteSelectLignesMetro;
import modele.dao.requetes.RequeteSelectLignesMetroById;
import modele.dao.requetes.RequeteUpdateLignesMetro;

public class DaoLigneMetro extends DaoModele<LigneMetro> {

	@Override
	public void create(LigneMetro donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(new RequeteInsertLignesMetro(), donnee);
		if (id > 0) {
			donnee.setId(id);
		}
	}

	@Override
	public void update(LigneMetro donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateLignesMetro(), donnee);
	}

	@Override
	public void delete(LigneMetro donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteLignesMetro(), donnee);
	}

	@Override
	public List<LigneMetro> findAll() throws SQLException {
		return this.find(new RequeteSelectLignesMetro());
	}

	public LigneMetro findById(int id) throws SQLException {
		return this.findById(new RequeteSelectLignesMetroById(), String.valueOf(id));
	}

	@Override
	protected LigneMetro creerInstance(ResultSet curseur) throws SQLException {
		return new LigneMetro(
				curseur.getInt("id_ligne_metro"),
				curseur.getString("nom"),
				curseur.getString("couleur"));
	}
}