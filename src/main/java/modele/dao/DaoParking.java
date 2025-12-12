package modele.dao;

<<<<<<<HEAD

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;=======
import java.sql.*;>>>>>>>cb88c7a39b34d8f4f5fc279c787fb41fa90e7401
import java.util.List;

import modele.Parking;
import modele.dao.requetes.*;

public class DaoParking extends DaoModele<Parking> {
	private static Iterateur<Parking> ite;

	public static void main(String[] args) throws SQLException {
		MySQLDataSource.creerAcces("root", "claudio");
		DaoParking dao = new DaoParking();
<<<<<<< HEAD
		dao.create(new Parking("Claudio",
				"Noam",
				300,
				120,
				2.0,
				Time.valueOf("08:00:00").toLocalTime(),
				Time.valueOf("18:30:00").toLocalTime(),
				false));
=======
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
>>>>>>> cb88c7a39b34d8f4f5fc279c787fb41fa90e7401
	}

	@Override
	public void create(Parking donnee) throws SQLException {
		this.miseAJour(new RequeteInsertParking(), donnee);
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

	public Iterateur<Parking> findAllIte() throws SQLException {
		return this.ite;
	}

	@Override
	protected Parking creerInstance(ResultSet curseur) throws SQLException {
		return new Parking(curseur.getString(0),
				curseur.getString(1),
				curseur.getInt(2),
				curseur.getInt(3),
				curseur.getDouble(4),
				curseur.getTime(5).toLocalTime(),
				curseur.getTime(6).toLocalTime(),
				curseur.getBoolean(7));
	}

	public static boolean hasNext() {
		return ite.hasNext();
	}

	public static Parking next() {
		return ite.next();
	}

}
