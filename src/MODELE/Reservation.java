package MODELE;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reservation {
	private int id;
	private Utilisateur utilisateur;
	private Parking parking;
	private LocalDateTime dateArrivee;
	private LocalDateTime dateDepart;
	private boolean estPayee;

	public Reservation(int id, Utilisateur utilisateur, Parking parking, LocalDateTime dateArrivee, int dureeHeures) {
		this.id = id;
		this.utilisateur = utilisateur;
		this.parking = parking;
		this.dateArrivee = dateArrivee;
		this.dateDepart = null;
		this.estPayee = false;
	}

	
	// Getters & setters
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

	
	public LocalDateTime getDateDepart() {
		return this.dateArrivee;
	}
	public void setDateDepart(LocalDateTime dateDepart) {
		this.dateDepart = dateDepart;
	}

	
	public boolean isEstPayee() {
		return this.estPayee;
	}
	public void setEstPayee(boolean estPayee) {
		this.estPayee = estPayee;
	}

	
	public double calculerPrixTotal() {
			int nbQuartsHeures = 0;
			Duration duree = Duration.between(dateDepart, dateArrivee);
			long minutes = duree.toMinutes();
			// Appliquer la tarification au quart d'heure 
			if (minutes % 15 != 0) {
				nbQuartsHeures = (int) (minutes / 15 + 1);
			} else {
				nbQuartsHeures = (int) (minutes / 15);
			}
			return nbQuartsHeures * this.parking.getTarifHoraire();
	}

	public void confirmerReservation() {
		System.out.println(
				"Réservation confirmée pour " + this.utilisateur.getNom() + " au parking " + this.parking.getNom());
		System.out.println("Arrivée : " + this.dateArrivee + ". Prix horaire : " + this.parking.getTarifHoraire());
	}

}