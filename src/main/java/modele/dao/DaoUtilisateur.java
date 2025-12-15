package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Utilisateur;
import modele.dao.requetes.RequeteDeleteUtilisateur;
import modele.dao.requetes.RequeteInsertUtilisateur;
import modele.dao.requetes.RequeteSelectUtilisateur;
import modele.dao.requetes.RequeteUpdateUtilisateur;

public class DaoUtilisateur extends DaoModele<Utilisateur> {

	@Override
	public void create(Utilisateur donnee) throws SQLException {
		this.miseAJour(new RequeteInsertUtilisateur(), donnee);
	}

	@Override
	public void update(Utilisateur donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateUtilisateur(), donnee);
	}

	@Override
	public void delete(Utilisateur donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteUtilisateur(), donnee);
	}

	@Override
	public List<Utilisateur> findAll() throws SQLException {
		return this.find(new RequeteSelectUtilisateur());
	}

	@Override
	protected Utilisateur creerInstance(ResultSet curseur) throws SQLException {
		return new Utilisateur(curseur.getInt("id_utilisateur"),
				curseur.getString("nom"),
				curseur.getString("prenom"),
				curseur.getString("email"),
				curseur.getString("mot_de_passe"),
				String.valueOf(curseur.getInt("id_abonnement")));
	}
}
