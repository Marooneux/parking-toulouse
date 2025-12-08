package modele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import modele.Parking;
import modele.dao.requetes.RequeteDeleteParking;
import modele.dao.requetes.RequeteInsertParking;
import modele.dao.requetes.RequeteSelectParking;
import modele.dao.requetes.RequeteUpdateParking;

public class DaoParking extends DaoModele<Parking> {

	public static void main(String[] args) throws SQLException {
		MySQLDataSource.creerAcces("root", "claudio");
		DaoParking dao = new DaoParking();
		dao.create(new Parking("Claudio",
				"Noam",
				5.0,
				120,
				2.0,
				Time.valueOf("08:00:00").toLocalTime(),
				Time.valueOf("18:30:00").toLocalTime()));
	}

	@Override
	public void create(Parking donnee) throws SQLException {
		Connection cn = MySQLDataSource.getConnexion();
		RequeteInsertParking req = new RequeteInsertParking();
		this.miseAJour(req, donnee);
		cn.close();
	}

	@Override
	public void update(Parking donnee) throws SQLException {
		Connection cn = MySQLDataSource.getConnexion();
		RequeteUpdateParking req = new RequeteUpdateParking();
		this.miseAJour(req, donnee);
		cn.close();
	}

	@Override
	public void delete(Parking donnee) throws SQLException {
		Connection cn = MySQLDataSource.getConnexion();
		RequeteDeleteParking req = new RequeteDeleteParking();
		this.miseAJour(req, donnee);
		cn.close();
	}

	@Override
	public List<Parking> findAll() throws SQLException {
		return this.find(new RequeteSelectParking());
	}

	@Override
	protected Parking creerInstance(ResultSet curseur) throws SQLException {
		return new Parking(curseur.getString(0),
				curseur.getString(1),
				curseur.getDouble(2),
				curseur.getInt(3),
				curseur.getDouble(4),
				curseur.getTime(5).toLocalTime(),
				curseur.getTime(6).toLocalTime());
	}
}
