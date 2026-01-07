package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import modele.Parking;
import modele.dao.requetes.RequeteDeleteParking;
import modele.dao.requetes.RequeteInsertParking;
import modele.dao.requetes.RequeteSelectParking;
import modele.dao.requetes.RequeteSelectParkingById;
import modele.dao.requetes.RequeteUpdateParking;

public class DaoParking extends DaoModele<Parking> {
	private static Iterateur<Parking> ite;

	@Override
	public void create(Parking donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(new RequeteInsertParking(), donnee);
		if (id > 0) {
			donnee.setId(id);
		}
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

	public Iterateur<Parking> findAllIte() throws SQLException {
		return DaoParking.ite;
	}

	@Override
	protected Parking creerInstance(ResultSet curseur) throws SQLException {
		Time tOuverture = curseur.getTime("horaire_ouverture");
		Time tFermeture = curseur.getTime("horaire_fermeture");

		LocalTime ouverture = (tOuverture != null) ? tOuverture.toLocalTime() : null;
		LocalTime fermeture = (tFermeture != null) ? tFermeture.toLocalTime() : null;

		Parking p = new Parking(
				curseur.getInt("id_parking"),
				curseur.getString("nom"),
				curseur.getString("adresse"),
				curseur.getInt("nombre_places_max"),
				curseur.getDouble("hauteur_max"),
				ouverture,
				fermeture,
				curseur.getBoolean("contient_places_moto"),
				curseur.getDouble("tarif"));

		p.setNbPlacesOccupees(curseur.getInt("nb_places_occupees"));

		return p;
	}

	public static boolean hasNext() {
		return ite.hasNext();
	}

	public static Parking next() {
		return ite.next();
	}

}
