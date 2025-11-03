package MODELE;

import java.time.LocalDateTime;

public class Paiement {
	private int id;
	private Reservation reservation;
	private String moyenPaiement; // "CB", "PayPal", "ApplePay", etc.
	private double montant;
	private LocalDateTime datePaiement;
	private boolean estValide;

	public Paiement(int id, Reservation reservation, String moyenPaiement) {
		this.id = id;
		this.reservation = reservation;
		this.moyenPaiement = moyenPaiement;
		this.montant = reservation.calculerPrixTotal();
		this.datePaiement = LocalDateTime.now();
		this.estValide = false;
	}

	// 📤 Getters
	public int getId() {
		return this.id;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public String getMoyenPaiement() {
		return this.moyenPaiement;
	}

	public double getMontant() {
		return this.montant;
	}

	public LocalDateTime getDatePaiement() {
		return this.datePaiement;
	}

	public boolean isEstValide() {
		return this.estValide;
	}

	public boolean effectuerPaiement() {
		if (this.montant > 0 && this.moyenPaiement != null && !this.moyenPaiement.isEmpty()) {
			this.estValide = true;
			this.reservation.setEstPayee(true);
			System.out.println("Paiement effectué avec succès : " + this.montant + " € via " + this.moyenPaiement);
			return true;
		} else {
			System.out.println("Échec du paiement.");
			return false;
		}
	}

	public void afficherDetails() {
		System.out.println("Paiement #" + this.id);
		System.out.println("Montant : " + this.montant + " €");
		System.out.println("Moyen : " + this.moyenPaiement);
		System.out.println("Date : " + this.datePaiement);
		System.out.println("Statut : " + (this.estValide ? "Validé" : "Non validé"));
	}
}
