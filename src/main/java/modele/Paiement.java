package modele;

import java.time.LocalDateTime;

public class Paiement {

	private ReservationParking reservation;
	private double montant;
	private LocalDateTime datePaiement;

	public Paiement(ReservationParking reservation, String moyenPaiement) {
		this.reservation = reservation;
		this.montant = reservation.calculerPrixTotal();
		this.datePaiement = LocalDateTime.now();
	}

	public ReservationParking getReservation() {
		return this.reservation;
	}

	public double getMontant() {
		return this.montant;
	}

	public LocalDateTime getDatePaiement() {
		return this.datePaiement;
	}

	public boolean estValide() {
		return this.reservation.estPayee();
	}

	public boolean effectuerPaiement() {
		if (this.montant > 0) {
			this.reservation.setEstPayee(true);
			System.out.println("Paiement effectué avec succès : " + this.montant + " € via carte bancaire.");
			return true;
		} else {
			System.out.println("Échec du paiement : le montant doit être superieur à 0 €.");
			return false;
		}
	}

	@Override
	public String toString() {
		return "Paiement [reservation=" + this.reservation + ", montant=" + this.montant + ", datePaiement="
				+ this.datePaiement + "]";
	}

}
