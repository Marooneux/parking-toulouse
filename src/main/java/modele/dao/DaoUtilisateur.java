package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Utilisateur;

public class DaoUtilisateur extends DaoModele<Utilisateur> {

	@Override
	public void create(Utilisateur donnees) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Utilisateur donnees) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Utilisateur donnees) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Utilisateur> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Utilisateur creerInstance(ResultSet curseur) throws SQLException {
		return new Utilisateur(curseur.getString(0),
				curseur.getString(1),
				curseur.getString(2),
				curseur.getString(3));
	}

}
