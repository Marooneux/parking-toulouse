package MODELE;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reservation {
	private int id;
	private String immatriculation;
	private Parking parking;
	private LocalDateTime dateArrivee;
	private LocalDateTime dateDepart;
	private boolean estPayee;

	public Reservation(int id, String immatriculation, Parking parking, LocalDateTime dateArrivee) {
		this.id = id;
		this.immatriculation = immatriculation;
		this.parking = parking;
		this.dateArrivee = dateArrivee;
		this.dateDepart = null;
		this.estPayee = false;
	}

	


	
	public double calculerPrixTotal() {
			int nbQuartsHeures = 0;
			Duration duree = Duration.between(dateArrivee, dateDepart);
			long minutes = duree.toMinutes();
			// Appliquer la tarrification au quart d'heure 
			System.out.println(duree);
			if (minutes % 15 != 0) {
				nbQuartsHeures = (int) (minutes / 15 + 1);
			} else {
				nbQuartsHeures = (int) (minutes / 15);
			}
			return nbQuartsHeures * this.parking.getTarifHoraire();
	}

	public String confirmerReservation() {
		return "Réservation confirmée au parking " + this.parking.getNom() +
		". Arrivée : " + this.dateArrivee + ". Prix horaire : " + this.parking.getTarifHoraire() + "€";
	}
	
	
	
	
	// Getters & setters
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getImmatriculation() {
		return this.immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
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
		return this.dateDepart;
	}
	public void setDateDepart(LocalDateTime dateDepart) {
		if (dateDepart == null) {
			throw new IllegalArgumentException("La date de départ ne doit pas être 'null'");
		} else if (dateDepart.isBefore(this.getDateArrivee())) {

			throw new IllegalArgumentException("La date de départ doit être postérieure à la date d'arrivée");
		}
		
		this.dateDepart = dateDepart;
	}

	
	public boolean getEstPayee() {
		return this.estPayee;
	}
	public void setEstPayee(boolean estPayee) {
		this.estPayee = estPayee;
	}

}