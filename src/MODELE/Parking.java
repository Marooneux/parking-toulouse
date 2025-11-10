package MODELE;

import java.util.List;
import java.util.LinkedList;

public class Parking {
	private int id;
	private String nom;
	private String adresse;
	private double tarifHoraire;
	private int nombrePlaces;
	private List<Voiture> listeVoitures;

	public Parking(int id, String nom, String adresse, double tarifHoraire, int nombrePlaces) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.tarifHoraire = tarifHoraire;
		this.nombrePlaces = nombrePlaces;
		this.listeVoitures = new LinkedList<>();
	}

	
	// Getters & setters
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
	public void setAdresse(String localisation) {
		this.adresse = localisation;
	}

	
	public double getTarifHoraire() {
		return this.tarifHoraire;
	}
	public void setTarifHoraire(double tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}

	
	public int getNombrePlaces() {
		return this.nombrePlaces;
	}
	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}



	public boolean correspond(String critere) {
		return this.nom.toLowerCase().contains(critere.toLowerCase())
				|| this.adresse.toLowerCase().contains(critere.toLowerCase());
	}

	public void afficherInfos() {
		System.out.println("Parking :" + this.nom);
		System.out.println("Adresse :" + this.adresse);
		System.out.println("Tarif :" + this.tarifHoraire + "euro/h");
		System.out.println("Places disponibles :" + this.nombrePlaces);
	}

	// Il faut pouvoir ajouter et supprimer des voitures du parking
	public void ajouterVoiture(Voiture voiture) {
		this.listeVoitures.add(voiture);
	}

	public void enleverVoiture(Voiture voiture) {
		this.listeVoitures.remove(voiture);
	}
}
