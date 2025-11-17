package MODELE;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Parking {
	private String nom;
	private String adresse;
	private double tarif; // prix pour 15mins de stationnement
	private int nbPlacesDisponibles;
	private int nbPlacesTotales;
	private double hauteur; // hauteur max en cm
	private LocalDateTime heureOuverture;
	private LocalDateTime heureFermeture;
	private List<Voiture> vehicules;

	public Parking(String nom, String adresse, double tarifHoraire, int nbPlacesTotales, double hauteur, 
			LocalDateTime heureOuverture, LocalDateTime heureFermeture) {
		this.nom = nom;
		this.adresse = adresse;
		this.tarif = tarifHoraire;
		this.nbPlacesDisponibles = nbPlacesTotales;
		this.nbPlacesTotales= nbPlacesTotales;
		this.hauteur = hauteur;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
		this.vehicules = new LinkedList<>();
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

	public void setAdresse(String localisation) {
		this.adresse = localisation;
	}

	public double getTarif() {
		return this.tarif;
	}

	public void setTarif(double tarifHoraire) {
		this.tarif = tarifHoraire;
	}

	public int getNbPlacesDisponibles() {
		return this.nbPlacesDisponibles;
	}

	public void setNbPlacesDisponibles(int nbPlacesDisponibles) {
		this.nbPlacesDisponibles = nbPlacesDisponibles;
	}
	
	public double getHauteur() {
		return this.hauteur;
	}
	
	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public LocalDateTime getHeureOuverture() {
		return this.heureOuverture;
	}
	
	public void setHeureOuverture(LocalDateTime heureOuverture) {
		this.heureOuverture = heureOuverture;
	}
	
	public LocalDateTime getHeureFermeture() {
		return this.heureFermeture;
	}
	
	public void setHeureFermeture(LocalDateTime heureFermeture) {
		this.heureFermeture = heureFermeture;
	}
	
	public Boolean estOuvert(LocalDateTime heure) {
		return (heure.isAfter(heureOuverture) && heure.isBefore(heureFermeture));
	}


	public Vehicule getVehicule(String immatriculation) {
		for (Vehicule v : this.vehicules) {
			if (v.getImmatriculation() == immatriculation) {
				return v;
			}
		}
		return null;
	}

	public void ajouterVoiture(Voiture voiture) {
		this.vehicules.add(voiture);
	}

	public void enleverVoiture(Voiture voiture) {
		this.vehicules.remove(voiture);
	}

}
