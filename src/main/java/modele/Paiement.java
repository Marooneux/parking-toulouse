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
		return this.reservation.estPayee();
	}

	public boolean effectuerPaiement() {
		if (this.montant > 0) {
			this.reservation.setEstPayee(true);
			System.out.println("Paiement effectué avec succès : " + this.montant + " € via " + this.moyenPaiement);
			return true;
		} else {
			System.out.println("Échec du paiement : le montant doit être superieur à 0 €.");
			return false;
		}
	}

	@Override
	public String toString() {
		return "Paiement [reservation=" + this.reservation + ", moyenPaiement=" + this.moyenPaiement
				+ ", montant=" + this.montant + ", datePaiement=" + this.datePaiement + "]";
	}

}
