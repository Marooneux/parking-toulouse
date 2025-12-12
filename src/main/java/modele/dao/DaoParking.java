package modele.dao;

import java.sql.*;
import java.util.List;

import modele.Parking;
import modele.dao.requetes.*;

public class DaoParking extends DaoModele<Parking> {

	public static void main(String[] args) throws SQLException {
		MySQLDataSource.creerAcces("root", "claudio");
		DaoParking dao = new DaoParking();
        Parking p = new Parking("Claudio",
                "Noam",
                5.0,
                120,
                10,
                12.5,
                Time.valueOf("08:00:00").toLocalTime(),
                Time.valueOf("18:30:00").toLocalTime());
		//dao.create(p);
        int nb = 0;
        List<Parking> ps = dao.findAll();
        for (Parking p1 : ps) {
            System.out.println(p1);
            if(nb >= 4) {
                break;
            }
            nb++;
        }

        //dao.delete(p);
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
		return new Parking(curseur.getString(2),
				curseur.getString(3),
				curseur.getDouble(4),
				curseur.getInt(5),
				curseur.getInt(6),
                curseur.getDouble(7),
				curseur.getTime(8).toLocalTime(),
				curseur.getTime(9).toLocalTime());
	}
}
