package MODELE;

import java.util.LinkedList;
import java.util.List;

public class Parking {
	private String nom;
	private String adresse;
	private double tarif; // prix pour 15mins de stationnement
	private int nbPlacesDisponibles;
	private double hauteur; // hauteur max en cm
	private boolean ouvert;
	private List<Voiture> vehicules;

	public Parking(String nom, String adresse, double tarifHoraire, int nbPlacesDisponibles, double hauteur) {
		this.nom = nom;
		this.adresse = adresse;
		this.tarif = tarifHoraire;
		this.nbPlacesDisponibles = nbPlacesDisponibles;
		this.hauteur = hauteur;
		this.ouvert = false;
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

	public boolean getOuvert() {
		return this.ouvert;
	}

	public void setOuvert(boolean etat) {
		this.ouvert = etat;
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
