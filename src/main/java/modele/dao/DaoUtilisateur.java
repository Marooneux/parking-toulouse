package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Utilisateur;
import modele.dao.requetes.RequeteSelectUtilisateurs;

public class DaoUtilisateur extends DaoModele<Utilisateur> {

	@Override
	public void create(Utilisateur donnees) throws SQLException {
		this.miseAJour(new modele.dao.requetes.RequeteInsertUtilisateur(), donnees);

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
		return this.find(new RequeteSelectUtilisateurs());
	}

	@Override
	protected Utilisateur creerInstance(ResultSet curseur) throws SQLException {
		int id = curseur.getInt("id");
		String nom = curseur.getString("nom");
		String prenom = curseur.getString("prenom");
		String email = curseur.getString("email");
		String mdp = curseur.getString("mot_de_passe");
		int idAb = curseur.getInt("id_abonnement");
		return new Utilisateur(id, nom, prenom, email, mdp, String.valueOf(idAb));
	}
}
