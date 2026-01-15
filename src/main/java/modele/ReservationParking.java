package modele;

import java.time.LocalDateTime;

public class ReservationParking {

	private int id;
	private LocalDateTime dateArrivee;
	private LocalDateTime dateDepart;
	private Parking parking;
	private Utilisateur utilisateur;

	public ReservationParking(int id, LocalDateTime dateArrivee, LocalDateTime dateDepart,
			Parking parking, Utilisateur utilisateur) {
		this.id = id;
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
		this.parking = parking;
		this.utilisateur = utilisateur;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateArrivee() {
		return this.dateArrivee;
	}

	public void setDateArrivee(LocalDateTime dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public LocalDateTime getDateDepart() {
		return this.dateDepart;
	}

	public void setDateDepart(LocalDateTime dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Parking getParking() {
		return this.parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public static boolean immatriculationValide(String immatriculation) {
		return immatriculation.matches("[A-Z]{2}-[0-9]{3}-[A-Z]{2}");
	}

}