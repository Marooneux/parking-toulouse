package modele;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class StationnementVoirie {
	public enum Couleur {JAUNE, ORANGE, ROUGE, VERTE, BLEU}
	private Couleur couleur;
	private double tarifHoraire;
	private int dureeMax;
	private LocalTime HorairePayantDebut; // Stationnement gratuit de minuit à 9h dans toutes les zones sauf bleus
	private LocalTime HorairePayantFin; // Stationnement gratuit de 19h/20h à minuit dans toutes les zones sauf bleus
	
	
	public StationnementVoirie(Couleur couleur, double tarifHoraire, int dureeMax, LocalTime horairePayantFin) {
		this.couleur = couleur;
		this.tarifHoraire = tarifHoraire;
		this.dureeMax = dureeMax;
		this.HorairePayantDebut = LocalTime.of(9, 0);
		this.HorairePayantFin = horairePayantFin;
	}
	
	
	
	
	public double calculerPrixTotal(int duree) {
		LocalTime actuel = LocalTime.of(6, 0);
		if ((actuel.isBefore(HorairePayantFin) && actuel.isAfter(HorairePayantDebut)) || LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
			return 0;
		}
		
		double prixTotal = 0;
    	int heures = duree / 60;
    	int minutes = duree % 60;
    	if (minutes > 0) {
    		prixTotal += tarifHoraire;
    	}
    	prixTotal += heures*tarifHoraire;
    	if (couleur == Couleur.ORANGE) {
    		if (duree > 180 && duree < 240) {
    			prixTotal = 4;
    		} else if (duree > 240) {
    			prixTotal = 6;
    		}
    	}
    	return prixTotal;
	}
	
	
	
	
	
	
	public String horairesToString() {
		return (HorairePayantDebut.toString() + " - " + HorairePayantFin.toString());
	}
	
	public String couleurZoneToString() {
		return "Zone " + (couleur.toString().toLowerCase());
	}
	
    public String dureeMaxToString() {
    	String duree = " heures";
    	int heures = dureeMax / 60;
    	int minutes = dureeMax % 60;
    	if (heures <= 1) {
    		duree = duree.substring(0, duree.length() - 1);
    	}
    	if (minutes > 0) {
    		duree = duree + " " + minutes + " minutes";
    	}
    	return (heures + duree);
    }
    
    public String tarifToString() {
    	if (tarifHoraire > 0) {
    		return tarifHoraire + "€/h";
    	} return "Gratuit";
    }


	
	// Getters & Setters
	public Couleur getCouleur() {
		return couleur;
	}
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	public double getTarifHoraire() {
		return tarifHoraire;
	}
	public void setTarifHoraire(int tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}


	public int getDureeMax() {
		return dureeMax;
	}
	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}


	public LocalTime getHorairePayantDebut() {
		return HorairePayantDebut;
	}
	public void setHorairePayantDebut(LocalTime horairePayantDebut) {
		HorairePayantDebut = horairePayantDebut;
	}


	public LocalTime getHorairePayantFin() {
		return HorairePayantFin;
	}
	public void setHorairePayantFin(LocalTime horairePayantFin) {
		HorairePayantFin = horairePayantFin;
	}
	
}
