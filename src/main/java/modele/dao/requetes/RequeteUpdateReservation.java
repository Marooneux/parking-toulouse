package modele.dao.requetes;

import modele.ReservationParking;

public class RequeteUpdateReservation extends Requete<ReservationParking> {
    @Override
    public String requete() {
        return "UPDATE reservations" +
                "SET ";
    }
}
