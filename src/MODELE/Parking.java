package MODELE;

import java.util.LinkedList;
import java.util.List;

public class Parking {
	private String nom;
	private String adresse;
	private double tarifHoraire;
	private int nbPlacesDisponibles;
	private boolean ouvert;
	private List<Voiture> vehicules;

	public Parking(String nom, String adresse, double tarifHoraire, int nbPlacesDisponibles) {
		this.nom = nom;
		this.adresse = adresse;
		this.tarifHoraire = tarifHoraire;
		this.nbPlacesDisponibles = nbPlacesDisponibles;
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

	public double getTarifHoraire() {
		return this.tarifHoraire;
	}

	public void setTarifHoraire(double tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}

	public int getNbPlacesDisponibles() {
		return this.nbPlacesDisponibles;
	}

	public void setNbPlacesDisponibles(int nbPlacesDisponibles) {
		this.nbPlacesDisponibles = nbPlacesDisponibles;
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
