package modele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Reservation;
import modele.dao.requetes.RequeteInsertReservation;
import modele.dao.requetes.RequeteUpdateParking;

public class DaoReservation extends DaoModele<Reservation> {

	@Override
	public void create(Reservation donnees) throws SQLException {
		Connection cn = MySQLDataSource.getConnexion();
        RequeteInsertReservation req = new RequeteInsertReservation();
        this.miseAJour(req, donnees);
        cn.close();
	}

	@Override
	public void update(Reservation donnees) throws SQLException {
        Connection cn = MySQLDataSource.getConnexion();
        RequeteUpdateReservation req = new RequeteUpdateParking();
        this.miseAJour(req, donnees);
        cn.close();

	}

	@Override
	public void delete(Reservation donnees) throws SQLException {
        Connection cn = MySQLDataSource.getConnexion();
        RequeteInsertReservation req = new RequeteInsertReservation();
        this.miseAJour(req, donnees);
        cn.close();
	}

	@Override
	public List<Reservation> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Reservation creerInstance(ResultSet curseur) throws SQLException {
		return new Reservation(curseur.getString(0),
				new DaoParking().findById(new RequeteSelectParking(), curseur.getString(1)),
				curseur.getTimestamp(2).toLocalDateTime());
	}

}