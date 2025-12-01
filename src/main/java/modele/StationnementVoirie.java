package main.java.modele;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class StationnementVoirie {
	public enum Couleur {
		JAUNE, ORANGE, ROUGE, VERTE
	}

	private Couleur couleur;
	private double tarifHoraire;
	private double dureeMax;
	private LocalTime HorairePayantDebut; // Stationnement gratuit de minuit à 9h dans toutes les zones sauf bleus
	private LocalTime HorairePayantFin; // Stationnement gratuit de 19h/20h à minuit dans toutes les zones sauf bleus
	private Boolean isDimanche; // Stationnement gratuit le dimanche dans toutes les zones

	public StationnementVoirie(Couleur couleur, double tarifHoraire, double dureeMax, LocalTime horairePayantFin) {
		this.couleur = couleur;
		this.tarifHoraire = tarifHoraire;
		this.dureeMax = dureeMax;
		this.HorairePayantDebut = LocalTime.of(9, 0);
		this.HorairePayantFin = horairePayantFin;
		this.isDimanche = this.isDimanche();
	}

	public Boolean isDimanche() {
		return (LocalDateTime.now().getDayOfWeek() == DayOfWeek.SUNDAY);
	}

	// Getters & Setters
	public double getTarifHoraire() {
		return this.tarifHoraire;
	}

	public void setTarifHoraire(int tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}

	public double getDureeMax() {
		return this.dureeMax;
	}

	public void setDureeMax(double dureeMax) {
		this.dureeMax = dureeMax;
	}

	public LocalTime getHorairePayantDebut() {
		return this.HorairePayantDebut;
	}

	public void setHorairePayantDebut(LocalTime horairePayantDebut) {
		this.HorairePayantDebut = horairePayantDebut;
	}

	public LocalTime getHorairePayantFin() {
		return this.HorairePayantFin;
	}

	public void setHorairePayantFin(LocalTime horairePayantFin) {
		this.HorairePayantFin = horairePayantFin;
	}

	public Boolean getIsDimanche() {
		return this.isDimanche;
	}

	public void setIsDimanche(Boolean isDimanche) {
		this.isDimanche = isDimanche;
	}

}
