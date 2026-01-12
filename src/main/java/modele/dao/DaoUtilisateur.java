package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Abonnement;
import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.requetes.RequeteDeleteUtilisateur;
import modele.dao.requetes.RequeteInsertUtilisateur;
import modele.dao.requetes.RequeteSelectUtilisateur;
import modele.dao.requetes.RequeteSelectUtilisateurById;
import modele.dao.requetes.RequeteUpdateUtilisateur;

public class DaoUtilisateur extends DaoModele<Utilisateur> {

	private DaoAbonnement daoAbonnement = new DaoAbonnement();

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

	@Override
	protected Utilisateur creerInstance(ResultSet curseur) throws SQLException {
		int id = curseur.getInt("id_utilisateur");
		String nom = curseur.getString("nom");
		String prenom = curseur.getString("prenom");
		String email = curseur.getString("email");
		String mdpHash = curseur.getString("mot_de_passe");
		int idAbonnement = curseur.getInt("id_abonnement");

		Abonnement abonnement = null;
		if (!curseur.wasNull()) {
			abonnement = this.daoAbonnement.findById(idAbonnement);
		}
		Type type = Type.valueOf(curseur.getString("type").toUpperCase());

		return new Utilisateur(id, nom, prenom, email, mdpHash, abonnement, type);
	}
}
