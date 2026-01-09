package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Utilisateur;
import modele.dao.requetes.RequeteDeleteUtilisateur;
import modele.dao.requetes.RequeteInsertUtilisateur;
import modele.dao.requetes.RequeteSelectUtilisateurs;

public class DaoUtilisateur extends DaoModele<Utilisateur> {

	@Override
	public void create(Utilisateur donnees) throws SQLException {
        int id = this.miseAJourAvecKeyGeneration(new RequeteInsertUtilisateur(), donnees);
        System.out.println("Generated id = " + id);
        if(id > 0) {
            donnees.setId(id);
        }
	}

	@Override
	public void update(Utilisateur donnees) throws SQLException {

	}

	@Override
	public void delete(Utilisateur donnees) throws SQLException {
		this.miseAJour(new RequeteDeleteUtilisateur(), donnees);
	}

	@Override
	public List<Utilisateur> findAll() throws SQLException {
		return this.find(new RequeteSelectUtilisateurs());
	}

	@Override
	protected Utilisateur creerInstance(ResultSet curseur) throws SQLException {
		String nom = curseur.getString("nom");
		String prenom = curseur.getString("prenom");
		String email = curseur.getString("email");
        String mdp = curseur.getString("mdp");
        String user_type = curseur.getString("user_type");
		return new Utilisateur(nom, prenom, email, mdp, user_type);
	}
}
