package vue;

import modele.ReservationParking;

public class PaiementParking extends PaiementCarte {

	private static final long serialVersionUID = -1573551118360617932L;

	private final transient ReservationParking reservation;

	public PaiementParking(ReservationParking reservation) {
		super(reservation.calculerPrixTotal());
		this.reservation = reservation;
	}

	public ReservationParking getReservation() {
		return this.reservation;
	}
}
