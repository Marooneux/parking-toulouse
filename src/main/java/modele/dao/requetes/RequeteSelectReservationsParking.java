package modele.dao.requetes;

import modele.ReservationParking;

public class RequeteSelectReservationsParking extends Requete<ReservationParking> {


    @Override
    public String requete() {
        return "SELECT * FROM reservations_parking";
    }
}
