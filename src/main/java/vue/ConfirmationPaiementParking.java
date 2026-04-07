package vue;

import modele.ReservationParking;

public class ConfirmationPaiementParking extends ConfirmationPaiement {


	public ConfirmationPaiementParking(ReservationParking reservation) {
		super(reservation.getPrixPaye(), "Terminer et Quitter", "Merci de votre visite");
	}
}
