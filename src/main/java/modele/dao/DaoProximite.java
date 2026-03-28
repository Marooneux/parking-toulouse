package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.LigneMetro;
import modele.Parking;
import modele.Proximite;
import modele.dao.requetes.RequeteDeleteProximite;
import modele.dao.requetes.RequeteInsertProximite;
import modele.dao.requetes.RequeteSelectProximite;
import modele.dao.requetes.RequeteSelectProximiteById;
import modele.dao.requetes.RequeteUpdateProximite;

public class DaoProximite extends DaoModele<Proximite> {

	private DaoParking daoParking = new DaoParking();
	private DaoLigneMetro daoLigneMetro = new DaoLigneMetro();

	@Override
	public void create(Proximite donnee) throws SQLException {
		this.miseAJour(new RequeteInsertProximite(), donnee);
	}

	@Override
	public void update(Proximite donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateProximite(), donnee);
	}

	@Override
	public void delete(Proximite donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteProximite(), donnee);
	}

	@Override
	public List<Proximite> findAll() throws SQLException {
		return this.find(new RequeteSelectProximite());
	}

	public Proximite findById(int idParking, int idLigneMetro) throws SQLException {
		return this.findById(new RequeteSelectProximiteById(),
				String.valueOf(idParking), String.valueOf(idLigneMetro));
	}

	@Override
	protected Proximite creerInstance(ResultSet curseur) throws SQLException {
		int idParking = curseur.getInt("id_parking");
		int idLigne = curseur.getInt("id_ligne_metro");
		int distance = curseur.getInt("distance_metres");

		Parking parking = this.daoParking.findById(idParking);
		if (parking == null) throw new SQLException("Parking introuvable id=" + idParking);
		LigneMetro ligneMetro = this.daoLigneMetro.findById(idLigne);
		if (ligneMetro == null) throw new SQLException("Ligne metro introuvable id=" + idLigne);

		return new Proximite(parking, ligneMetro, distance);
	}
}