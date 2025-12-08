package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Parking;

public class DaoParking extends DaoModele<Parking> {

	@Override
	public void create(Parking donnee) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Parking donnee) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Parking donnee) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Parking> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
