package MODELE;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Parking {
	private String nom;
	private String adresse;
	private double tarif; // prix pour 15mins de stationnement
	private int nbPlacesOccupees;
	private int nbPlacesTotales;
	private double hauteur; // hauteur max en cm
	private LocalTime heureOuverture;
	private LocalTime heureFermeture;
	private List<Voiture> vehicules;

	public Parking(String nom, String adresse, double tarifHoraire, int nbPlacesTotales, double hauteur, 
			LocalTime heureOuverture, LocalTime heureFermeture) {
		this.nom = nom;
		this.adresse = adresse;
		this.tarif = tarifHoraire;
		this.nbPlacesOccupees = 0;
		this.nbPlacesTotales= nbPlacesTotales;
		this.hauteur = hauteur;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
		this.vehicules = new LinkedList<>();
	}
	
	
	


	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}


	public int getNbPlacesOccupees() {
		return nbPlacesOccupees;
	}

	public void setNbPlacesOccupees(int nbPlacesOccupees) {
		this.nbPlacesOccupees = nbPlacesOccupees;
	}


	public int getNbPlacesTotales() {
		return nbPlacesTotales;
	}

	public void setNbPlacesTotales(int nbPlacesTotales) {
		this.nbPlacesTotales = nbPlacesTotales;
	}


	public double getHauteur() {
		return hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}


	public LocalTime getHeureOuverture() {
		return heureOuverture;
	}

	public void setHeureOuverture(LocalTime heureOuverture) {
		this.heureOuverture = heureOuverture;
	}


	public LocalTime getHeureFermeture() {
		return heureFermeture;
	}

	public void setHeureFermeture(LocalTime heureFermeture) {
		this.heureFermeture = heureFermeture;
	}


	public List<Voiture> getVehicules() {
		return vehicules;
	}

	public void setVehicules(List<Voiture> vehicules) {
		this.vehicules = vehicules;
	}






	public Boolean estOuvert(LocalTime heure) {
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
