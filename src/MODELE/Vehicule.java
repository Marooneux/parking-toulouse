package MODELE;

public abstract class Vehicule {
<<<<<<< HEAD
	private String type; // Voiture ou moto
	private int hauteur; // en cm
	private String immatriculation; // format AA-000-AA
	private Boolean electrique; // true pour les véhicules éléctriques
	private Boolean disqueBleu; // true si possède le disque bleu pour stationner en zone bleu

	
	public Vehicule(String type, int hauteur, String immatriculation, Boolean electrique, Boolean disqueBleu) {
=======
	private String type;
	private int hauteur;
	private String immatriculation;
	private Boolean electrique;

	public Vehicule(String type, int hauteur, String immatriculation, Boolean electrique) {
>>>>>>> f49a63c9b12e170da52ee5bb36a4a6896e33167b
		this.type = type;
		this.hauteur = hauteur;
		this.immatriculation = immatriculation;
		this.electrique = electrique;
		this.disqueBleu = disqueBleu;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHauteur() {
		return this.hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Boolean getElectrique() {
		return this.electrique;
	}

	public void setElectrique(Boolean electrique) {
		this.electrique = electrique;
	}
<<<<<<< HEAD
	
	
	public Boolean getDisqueBleu() {
		return disqueBleu;
	}
	public void setDisqueBleu(Boolean disqueBleu) {
		this.disqueBleu = disqueBleu;
	}
=======

>>>>>>> f49a63c9b12e170da52ee5bb36a4a6896e33167b
}
