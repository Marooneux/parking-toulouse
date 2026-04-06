package modele;

import java.time.LocalDateTime;

public class Paiement {
	private enum OptionsPaiement {
		CB, VIREMENT
	};

	private ReservationParking reservation;
	private OptionsPaiement moyenPaiement;
	private double montant;
	private LocalDateTime datePaiement;

	public Paiement(ReservationParking reservation, String moyenPaiement) {
		this.reservation = reservation;
		this.moyenPaiement = OptionsPaiement.CB;
		this.montant = reservation.calculerPrixTotal();
		this.datePaiement = LocalDateTime.now();
	}

	public ReservationParking getReservation() {
		return this.reservation;
	}

	public OptionsPaiement getMoyenPaiement() {
		return this.moyenPaiement;
	}

	public void setMoyenPaiement(OptionsPaiement moyenPaiement) {
		this.moyenPaiement = moyenPaiement;
	}

	public double getMontant() {
		return this.montant;
	}

	public LocalDateTime getDatePaiement() {
		return this.datePaiement;
	}

	public boolean estValide() {
		return this.reservation.getPrixPaye() > 0;
	}

	public boolean effectuerPaiement() {
		return estValide();
	}
}
