package modele;

import java.time.LocalDateTime;

public class ReservationVoirie {

	private int id;
	private LocalDateTime dateDebut;
	private int dureeMinutes;
	private ZoneVoirie zone;
	private Utilisateur utilisateur;

	public ReservationVoirie(int id, LocalDateTime dateDebut, int dureeMinutes,
			ZoneVoirie zone, Utilisateur utilisateur) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dureeMinutes = dureeMinutes;
		this.zone = zone;
		this.utilisateur = utilisateur;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ZoneVoirie getZone() {
		return this.zone;
	}

	public void setZone(ZoneVoirie zone) {
		this.zone = zone;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
