package modele;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	public String dateArriveeToString() {
		LocalDateTime dateTime = this.dateArrivee;
        if (dateTime == null) {
            return ""; 
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
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
	    
	    if (this.dateArrivee == null) return 0.0;
	    LocalDateTime fin = (this.dateDepart != null) ? this.dateDepart : LocalDateTime.now();
	    long minutes = Math.max(0, ChronoUnit.MINUTES.between(this.dateArrivee, fin));
	    double quartsDHeure = Math.ceil(minutes / 15.0);
	    quartsDHeure = Math.max(1.0, quartsDHeure);
	    double heuresFacturees = quartsDHeure / 4.0;
	    
	    return heuresFacturees * parking.getTarif();
	}

}