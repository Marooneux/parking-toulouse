package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import modele.Adresse;
import modele.Parking;
import modele.dao.requetes.RequeteDeleteParking;
import modele.dao.requetes.RequeteInsertParking;
import modele.dao.requetes.RequeteSelectParking;
import modele.dao.requetes.RequeteSelectParkingById;
import modele.dao.requetes.RequeteUpdateParking;

public class DaoParking extends DaoModele<Parking> {

	private DaoAdresse daoAdresse = new DaoAdresse();

	@Override
	public void create(Parking donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(new RequeteInsertParking(), donnee);
		donnee.setId(id);
	}

	@Override
	public void update(Parking donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateParking(), donnee);
	}

	@Override
	public void delete(Parking donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteParking(), donnee);
	}

	@Override
	public List<Parking> findAll() throws SQLException {
		return this.find(new RequeteSelectParking());
	}

	public Parking findById(int id) throws SQLException {
		return this.findById(new RequeteSelectParkingById(), String.valueOf(id));
	}

	@Override
	protected Parking creerInstance(ResultSet rs) throws SQLException {

		int id = rs.getInt("id");
		String nom = rs.getString("nom");
		int capacite = rs.getInt("capacite");

		Double hauteurMax = rs.getDouble("hauteur_max");
		if (rs.wasNull()) {
			hauteurMax = null;
		}

		Time ouverture = rs.getTime("horaire_ouverture");
		Time fermeture = rs.getTime("horaire_fermeture");

		boolean contientPlacesMoto = rs.getBoolean("contient_places_moto");

		int idAdresse = rs.getInt("id_adresse");
		Adresse adresse = this.daoAdresse.findById(idAdresse);

		return new Parking(
				id,
				nom,
				capacite,
				hauteurMax,
				ouverture != null ? ouverture.toLocalTime() : null,
				fermeture != null ? fermeture.toLocalTime() : null,
				contientPlacesMoto,
				adresse);
	}
}
