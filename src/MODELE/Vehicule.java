package MODELE;

public abstract class Vehicule {
	private String type;
	private int hauteur;
	private String immatriculation;
	private Boolean electrique;

	public Vehicule(String type, int hauteur, String immatriculation, Boolean electrique) {
		this.type = type;
		this.hauteur = hauteur;
		this.immatriculation = immatriculation;
		this.electrique = electrique;
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

}
