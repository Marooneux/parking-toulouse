package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import modele.Parking;
import modele.ReservationParking;
import modele.dao.requetes.RequeteDeleteReservationParking;
import modele.dao.requetes.RequeteInsertReservationParking;
import modele.dao.requetes.RequeteSelectReservationParking;
import modele.dao.requetes.RequeteUpdateReservationParking;

public class DaoReservationParking extends DaoModele<ReservationParking> {
    @Override
    public void create(ReservationParking donnee) throws SQLException {
        this.miseAJour(new RequeteInsertReservationParking(), donnee);
    }

    @Override
    public void update(ReservationParking donnee) throws SQLException {
        this.miseAJour(new RequeteUpdateReservationParking(), donnee);
    }

    @Override
    public void delete(ReservationParking donnee) throws SQLException {
        this.miseAJour(new RequeteDeleteReservationParking(), donnee);
    }

    @Override
    public List<ReservationParking> findAll() throws SQLException {
        return this.find(new RequeteSelectReservationParking());
    }

    @Override
    protected ReservationParking creerInstance(ResultSet curseur) throws SQLException {
        String immatriculation = curseur.getString("immatriculation");
        Timestamp debut = curseur.getTimestamp("date_arrivee");
        Timestamp fin = curseur.getTimestamp("date_depart");
        int idParking = curseur.getInt("id_parking");

        // Resolve parking by id using DaoParking
        Parking parking = new DaoParking().findById(idParking);

        ReservationParking r = new ReservationParking(
                immatriculation,
                parking,
                debut.toLocalDateTime()
        );
        if (fin != null) {
            r.setDateDepart(fin.toLocalDateTime());
        }
        return r;
    }
}
