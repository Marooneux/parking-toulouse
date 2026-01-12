package modele;

import java.time.LocalDateTime;

public class ReservationVoirie {
	private String immatriculation;
	private String typeVehicule;
	private LocalDateTime dateDebut;
	private int dureeMinutes;
	private int idZone;
	private int idUtilisateur;

	public ReservationVoirie(String immatriculation, String typeVehicule, LocalDateTime dateDebut,
			int dureeMinutes, int idZone, int idUtilisateur) {
		this.immatriculation = immatriculation;
		this.typeVehicule = typeVehicule;
		this.dateDebut = dateDebut;
		this.dureeMinutes = dureeMinutes;
		this.idZone = idZone;
		this.idUtilisateur = idUtilisateur;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getTypeVehicule() {
		return this.typeVehicule;
	}

	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

	public LocalDateTime getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public int getDureeMinutes() {
		return this.dureeMinutes;
	}

	public void setDureeMinutes(int dureeMinutes) {
		this.dureeMinutes = dureeMinutes;
	}

	public int getIdZone() {
		return this.idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public static boolean immatriculationValide(String immatriculation) {
		return immatriculation.matches("[A-Z]{2}-[0-9]{3}-[A-Z]{2}");
	}
}