package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.requetes.RequeteDeleteUtilisateur;
import modele.dao.requetes.RequeteInsertUtilisateur;
import modele.dao.requetes.RequeteSelectUtilisateur;
import modele.dao.requetes.RequeteSelectUtilisateurByEmail;
import modele.dao.requetes.RequeteSelectUtilisateurById;
import modele.dao.requetes.RequeteUpdateUtilisateur;

public class DaoUtilisateur extends DaoModele<Utilisateur> {

	@Override
	public void create(Utilisateur donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(new RequeteInsertUtilisateur(), donnee);
		donnee.setId(id);
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

	public Utilisateur findById(int id) throws SQLException {
		return this.findById(new RequeteSelectUtilisateurById(), String.valueOf(id));
	}

	public Utilisateur findByEmail(String email) throws SQLException {
		return this.findById(new RequeteSelectUtilisateurByEmail(), email);
	}

	@Override
	protected Utilisateur creerInstance(ResultSet curseur) throws SQLException {
		int id = curseur.getInt("id");
		String nom = curseur.getString("nom");
		String prenom = curseur.getString("prenom");
		String email = curseur.getString("email");
		String mdpHash = curseur.getString("mdp");
		Type type = Type.valueOf(curseur.getString("user_type").toUpperCase());

		return new Utilisateur(id, nom, prenom, email, mdpHash, type);
	}
}
