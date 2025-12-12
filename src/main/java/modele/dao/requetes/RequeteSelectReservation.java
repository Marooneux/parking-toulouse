package modele.dao.requetes;

import modele.Reservation;

public class RequeteSelectReservation extends Requete<Reservation> {


    @Override
    public String requete() {
        return "SELECT * FROM Reservation";
    }
}
