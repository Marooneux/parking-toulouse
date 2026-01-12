package modele;

import java.time.LocalTime;

public class Parking {
	private int id;
	private String nom;
	private String adresse;
	private int nbPlacesMax;
	private int nbPlacesOccupees;
	private double hauteur;
	private LocalTime heureOuverture;
	private LocalTime heureFermeture;
	private boolean contientPlacesMoto;
	private double tarif;

	public Parking(int id, String nom, String adresse, int nbPlacesMax, int nbPlacesOccupees, double hauteur,
			LocalTime heureOuverture, LocalTime heureFermeture, boolean contientPlacesMoto, double tarif) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.nbPlacesMax = nbPlacesMax;
		this.nbPlacesOccupees = nbPlacesOccupees;
		this.hauteur = hauteur;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
		this.contientPlacesMoto = contientPlacesMoto;
		this.tarif = tarif;
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

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getNbPlacesMax() {
		return this.nbPlacesMax;
	}

	public void setNbPlacesMax(int nbPlacesMax) {
		this.nbPlacesMax = nbPlacesMax;
	}

	public int getNbPlacesOccupees() {
		return this.nbPlacesOccupees;
	}

	public void setNbPlacesOccupees(int nbPlacesOccupees) {
		this.nbPlacesOccupees = nbPlacesOccupees;
	}

	public double getHauteur() {
		return this.hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public LocalTime getHeureOuverture() {
		return this.heureOuverture;
	}

	public void setHeureOuverture(LocalTime heureOuverture) {
		this.heureOuverture = heureOuverture;
	}

	public LocalTime getHeureFermeture() {
		return this.heureFermeture;
	}

	public void setHeureFermeture(LocalTime heureFermeture) {
		this.heureFermeture = heureFermeture;
	}

	public boolean isContientPlacesMoto() {
		return this.contientPlacesMoto;
	}

	public void setContientPlacesMoto(boolean contientPlacesMoto) {
		this.contientPlacesMoto = contientPlacesMoto;
	}

	public double getTarif() {
		return this.tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public Boolean estOuvert(LocalTime heure) {
		return (heure.isAfter(this.heureOuverture) && heure.isBefore(this.heureFermeture));
	}

	public void ajouterNbPlacesOccupes(int nb) {
		if (this.nbPlacesOccupees + nb < this.nbPlacesMax && this.nbPlacesOccupees + nb > 0) {
			this.nbPlacesOccupees += nb;
		}
	}

	public void enleverNbPlacesOccupes(int nb) {
		this.ajouterNbPlacesOccupes(-nb);
	}
}
