package MODELE;

import java.awt.List;

public class Parking {
	private int id;
	private String nom;
	private String adresse;
	private double tarifHoraire;
	private int nombrePlaces;
	private Voiture[] listeVoitures;

	public Parking(int id, String nom, String adresse, double tarifHoraire, int nombrePlaces) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.tarifHoraire = tarifHoraire;
		this.nombrePlaces = nombrePlaces;
		
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
}
