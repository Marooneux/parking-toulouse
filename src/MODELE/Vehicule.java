package MODELE;

public abstract class Vehicule {
	private String type; // Voiture ou moto
	private int hauteur; // en cm
	private String immatriculation; // format AA-000-AA
	private Boolean electrique; // true pour les véhicules éléctriques
	private Boolean disqueBleu; // true si possède le disque bleu pour stationner en zone bleu

	
	public Vehicule(String type, int hauteur, String immatriculation, Boolean electrique, Boolean disqueBleu) {
		this.type = type;
		this.hauteur = hauteur;
		this.immatriculation = immatriculation;
		this.electrique = electrique;
		this.disqueBleu = disqueBleu;
	}


	// Getters & Setters
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	
	public Boolean getElectrique() {
		return electrique;
	}
	public void setElectrique(Boolean electrique) {
		this.electrique = electrique;
	}
	
	
	public Boolean getDisqueBleu() {
		return disqueBleu;
	}
	public void setDisqueBleu(Boolean disqueBleu) {
		this.disqueBleu = disqueBleu;
	}
}
