package modele.dao.requetes;

import modele.Reservation;

public class RequeteUpdateReservation extends Requete<Reservation> {
    @Override
    public String requete() {
        return "UPDATE reservations" +
                "SET ";
    }

    public void parametres()
}
