package MODELE;

import java.time.LocalDateTime;

public class Reservation {
	private int id;
	private Utilisateur utilisateur;
	private Parking parking;
	private LocalDateTime dateArrivee;
	private int duree;
	private boolean estPayee;

	public Reservation(int id, Utilisateur utilisateur, Parking parking, LocalDateTime dateArrivee, int dureeHeures) {
		this.id = id;
		this.utilisateur = utilisateur;
		this.parking = parking;
		this.dateArrivee = dateArrivee;
		this.duree = dureeHeures;
		this.estPayee = false;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Parking getParking() {
		return this.parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public LocalDateTime getDateArrivee() {
		return this.dateArrivee;
	}

	public void setDateArrivee(LocalDateTime dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public boolean isEstPayee() {
		return this.estPayee;
	}

	public void setEstPayee(boolean estPayee) {
		this.estPayee = estPayee;
	}

	public double calculerPrixTotal() {
		return this.parking.calculerPrix(this.duree);
	}

	public void confirmerReservation() {
		System.out.println(
				"Réservation confirmée pour " + this.utilisateur.getNom() + " au parking " + this.parking.getNom());
		System.out.println("Arrivée : " + this.dateArrivee + " | Durée : " + this.duree + "h | Prix : "
				+ this.calculerPrixTotal() + " €");
	}

}