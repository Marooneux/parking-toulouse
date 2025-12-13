package modele.dao.requetes;

import modele.ReservationParking;

public class RequeteSelectReservation extends Requete<ReservationParking> {


    @Override
    public String requete() {
        return "SELECT * FROM Reservation";
    }
}
