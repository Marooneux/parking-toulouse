package modele;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class StationnementVoirie {
	private ZoneVoirie zone;
	private LocalTime HorairePayantDebut; // Stationnement gratuit de minuit à 9h dans toutes les zones sauf bleus
	private LocalTime HorairePayantFin; // Stationnement gratuit de 19h/20h à minuit dans toutes les zones sauf bleus

	public StationnementVoirie(ZoneVoirie zone, LocalTime horaireDebut, LocalTime horaireFin) {
		this.zone = zone;
		this.HorairePayantDebut = horaireDebut;
		this.HorairePayantFin = horaireFin;
	}

	public double calculerPrixTotal(int duree) {
		LocalTime actuel = LocalTime.of(6, 0);
		if ((actuel.isBefore(this.HorairePayantFin) && actuel.isAfter(this.HorairePayantDebut))
				|| LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
			return 0;
		}

		double prixTotal = 0;
		int heures = duree / 60;
		int minutes = duree % 60;
		if (minutes > 0) {
			prixTotal += this.zone.getTarifHoraire();
		}
		prixTotal += heures * this.zone.getTarifHoraire();
		if (this.zone.getCouleur() == "orange") {
			if (duree > 180 && duree < 240) {
				prixTotal = 4;
			} else if (duree > 240) {
				prixTotal = 6;
			}
		}
		return prixTotal;
	}

	public String horairesToString() {
		return (this.HorairePayantDebut.toString() + " - " + this.HorairePayantFin.toString());
	}

	public String couleurZoneToString() {
		return "Zone " + (this.zone.getCouleur().toString());
	}

	public String dureeMaxToString() {
		String duree = " heures";
		double heures = this.zone.getDureeMax() / 60.0;
		double minutes = this.zone.getDureeMax() % 60;
		if (heures <= 1.0) {
			duree = duree.substring(0, duree.length() - 1);
		}
		if (minutes > 0.0) {
			duree = duree + " " + minutes + " minutes";
		}
		return (heures + duree);
	}

	public String tarifToString() {
		if (this.zone.getTarifHoraire() > 0) {
			return this.zone.getTarifHoraire() + "€/h";
		}
		return "Gratuit";
	}

	public Boolean isDimanche() {
		return (LocalDateTime.now().getDayOfWeek() == DayOfWeek.SUNDAY);
	}

	public ZoneVoirie getZone() {
		return this.zone;
	}

	public void setZone(ZoneVoirie zone) {
		this.zone = zone;
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

}
