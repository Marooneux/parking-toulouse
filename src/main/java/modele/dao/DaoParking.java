package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import modele.Parking;
import modele.dao.requetes.*;

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
	
	public List<Parking> findByAdminId(int adminId) throws SQLException {
		return this.find(new RequeteSelectParkingByAdminId(), String.valueOf(adminId));
	}

	public Iterateur<Parking> findAllIte() throws SQLException {
		return DaoParking.ite;
	}

	@Override
	protected Parking creerInstance(ResultSet curseur) throws SQLException {
		int id = curseur.getInt("id_parking");
		String nom = curseur.getString("nom");
		String adresse = curseur.getString("adresse");
		double tarif = curseur.getDouble("tarif");
		int nbMax = curseur.getInt("nombre_places_max");
		double hauteur = curseur.getDouble("hauteur_max");
		Time ouv = curseur.getTime("horaire_ouverture");
		Time ferm = curseur.getTime("horaire_fermeture");
		boolean moto = curseur.getBoolean("contient_places_moto");
		Parking p = new Parking(nom, adresse, tarif, nbMax, 
				hauteur,
				ouv.toLocalTime(), ferm.toLocalTime(), moto);
		p.setId(id);
		return p;
	}

	public static boolean hasNext() {
		return ite.hasNext();
	}

	public static Parking next() {
		return ite.next();
	}

}
