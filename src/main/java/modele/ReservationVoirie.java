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

	public String getTypeVehicule() {
		return this.typeVehicule;
	}

	public LocalDateTime getDateDebut() {
		return this.dateDebut;
	}

	public int getDureeMinutes() {
		return this.dureeMinutes;
	}

	public int getIdZone() {
		return this.idZone;
	}

	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}
}