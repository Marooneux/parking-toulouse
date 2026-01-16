package modele;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ReservationParking {

	private int id;
	private LocalDateTime dateArrivee;
	private LocalDateTime dateDepart;
	private Parking parking;
	private Utilisateur utilisateur;
	private double prixPaye;

	public ReservationParking(
			LocalDateTime dateArrivee,
			LocalDateTime dateDepart,
			Parking parking,
			Utilisateur utilisateur) {
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
		this.parking = parking;
		this.utilisateur = utilisateur;
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

	public int getId() {
		return this.id;
	}

	public void setDateDepart(LocalDateTime dateDepart) {
		this.dateDepart = dateDepart;
		this.prixPaye = calculerPrixTotal();
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

	public double getPrixPaye() {
		return this.prixPaye;
	}

	public void setPrixPaye(double ignored) {
		this.prixPaye = calculerPrixTotal();
	}

	public double calculerPrixTotal() {
		if (parking == null) {
			return 0.0;
		}
		LocalDateTime fin = (this.dateDepart != null) ? this.dateDepart : LocalDateTime.now();
		long minutes = ChronoUnit.MINUTES.between(this.dateArrivee, fin);
		double heures = Math.max(0.0, minutes / 60.0);
		return heures * parking.getTarif();
	}

}