package modele;

import java.time.LocalTime;

public class Parking {

	private int id;
	private String nom;
	private int capacite;
	private double hauteurMax;
	private LocalTime horaireOuverture;
	private LocalTime horaireFermeture;
	private boolean contientPlacesMoto;
	private Adresse adresse;

	public Parking(int id, String nom, int capacite, double hauteurMax,
			LocalTime horaireOuverture, LocalTime horaireFermeture,
			boolean contientPlacesMoto, Adresse adresse) {
		this.id = id;
		this.nom = nom;
		this.capacite = capacite;
		this.hauteurMax = hauteurMax;
		this.horaireOuverture = horaireOuverture;
		this.horaireFermeture = horaireFermeture;
		this.contientPlacesMoto = contientPlacesMoto;
		this.adresse = adresse;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapacite() {
		return this.capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public double getHauteurMax() {
		return this.hauteurMax;
	}

	public void setHauteurMax(double hauteurMax) {
		this.hauteurMax = hauteurMax;
	}

	public LocalTime getHoraireOuverture() {
		return this.horaireOuverture;
	}

	public void setHoraireOuverture(LocalTime horaireOuverture) {
		this.horaireOuverture = horaireOuverture;
	}

	public LocalTime getHoraireFermeture() {
		return this.horaireFermeture;
	}

	public void setHoraireFermeture(LocalTime horaireFermeture) {
		this.horaireFermeture = horaireFermeture;
	}

	public boolean isContientPlacesMoto() {
		return this.contientPlacesMoto;
	}

	public void setContientPlacesMoto(boolean contientPlacesMoto) {
		this.contientPlacesMoto = contientPlacesMoto;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Boolean estOuvertApres(LocalTime heure) {
		return (heure.isAfter(this.horaireOuverture) && heure.isBefore(this.horaireFermeture));
	}

//	public void ajouterNbPlacesOccupes(int nb) {
//		if (this.nbPlacesOccupees + nb < this.capacite && this.nbPlacesOccupees + nb > 0) {
//			this.nbPlacesOccupees += nb;
//		}
//	}
//
//	public void enleverNbPlacesOccupes(int nb) {
//		this.ajouterNbPlacesOccupes(-nb);
//	}
}
