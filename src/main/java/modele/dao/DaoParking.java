package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		return new Parking(curseur.getInt("id_parking"),
				curseur.getString("nom"),
				curseur.getString("adresse"),
				curseur.getInt("nombre_places_max"),
				curseur.getDouble("hauteur_max"),
				curseur.getTime("horaire_ouverture").toLocalTime(),
				curseur.getTime("horaire_fermeture").toLocalTime(),
				curseur.getBoolean("contient_places_moto"),
				curseur.getDouble("tarif"));
	}

	public static boolean hasNext() {
		return ite.hasNext();
	}

	public static Parking next() {
		return ite.next();
	}

}
