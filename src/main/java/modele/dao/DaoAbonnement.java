package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Abonnement;
import modele.dao.requetes.RequeteDeleteAbonnement;
import modele.dao.requetes.RequeteInsertAbonnement;
import modele.dao.requetes.RequeteSelectAbonnement;
import modele.dao.requetes.RequeteSelectAbonnementById;
import modele.dao.requetes.RequeteUpdateAbonnement;

public class DaoAbonnement extends DaoModele<Abonnement> {

	@Override
	public void create(Abonnement donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(new RequeteInsertAbonnement(), donnee);
		donnee.setId(id);
	}

	@Override
	public void update(Abonnement donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateAbonnement(), donnee);
	}

	@Override
	public void delete(Abonnement donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteAbonnement(), donnee);
	}

	@Override
	public List<Abonnement> findAll() throws SQLException {
		return this.find(new RequeteSelectAbonnement());
	}

	public Abonnement findById(int id) throws SQLException {
		return this.findById(new RequeteSelectAbonnementById(), String.valueOf(id));
	}

	@Override
	protected Abonnement creerInstance(ResultSet curseur) throws SQLException {
		return new Abonnement(
				curseur.getInt("id_abonnement"),
				curseur.getString("nom"),
				curseur.getString("description"));
	}
}
