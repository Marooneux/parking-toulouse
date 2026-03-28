package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Abonne;
import modele.Abonnement;
import modele.Utilisateur;
import modele.dao.requetes.RequeteDeleteAbonne;
import modele.dao.requetes.RequeteInsertAbonne;
import modele.dao.requetes.RequeteSelectAbonne;
import modele.dao.requetes.RequeteSelectAbonneById;
import modele.dao.requetes.RequeteUpdateAbonne;

public class DaoAbonne extends DaoModele<Abonne> {

	private DaoUtilisateur daoUtilisateur = new DaoUtilisateur();
	private DaoAbonnement daoAbonnement = new DaoAbonnement();

	@Override
	public void create(Abonne donnee) throws SQLException {
		this.miseAJour(new RequeteInsertAbonne(), donnee);
	}

	@Override
	public void update(Abonne donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateAbonne(), donnee);
	}

	@Override
	public void delete(Abonne donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteAbonne(), donnee);
	}

	@Override
	public List<Abonne> findAll() throws SQLException {
		return this.find(new RequeteSelectAbonne());
	}

	public Abonne findById(int idUtilisateur, int idAbonnement) throws SQLException {
		return this.findById(
				new RequeteSelectAbonneById(),
				String.valueOf(idUtilisateur),
				String.valueOf(idAbonnement));
	}

	@Override
	protected Abonne creerInstance(ResultSet curseur) throws SQLException {
		int idUtilisateur = curseur.getInt("id_utilisateur");
		int idAbonnement = curseur.getInt("id_abonnement");
		boolean estActif = curseur.getBoolean("est_actif");

		Utilisateur utilisateur = this.daoUtilisateur.findById(idUtilisateur);
		if (utilisateur == null) throw new SQLException("Utilisateur introuvable id=" + idUtilisateur);
		Abonnement abonnement = this.daoAbonnement.findById(idAbonnement);
		if (abonnement == null) throw new SQLException("Abonnement introuvable id=" + idAbonnement);

		return new Abonne(utilisateur, abonnement, estActif);
	}

}
