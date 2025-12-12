package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.ReservationParking;
import modele.dao.requetes.RequeteSelectParking;

public class DaoReservation extends DaoModele<ReservationParking> {

	@Override
	public void create(ReservationParking donnees) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ReservationParking donnees) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ReservationParking donnees) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ReservationParking> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ReservationParking creerInstance(ResultSet curseur) throws SQLException {
		return new ReservationParking(curseur.getString(0),
				new DaoParking().findById(new RequeteSelectParking(), curseur.getString(1)),
				curseur.getTimestamp(2).toLocalDateTime());
	}

}