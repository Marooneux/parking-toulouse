package modele;

public class Zone {
	private int id;
	private String nom;
	private String zone;
	private double tarifHoraire;
	private double dureeMax; // Durée de stationnement max en minutes

	public Zone(String nom, String zone, double tarifHoraire, double dureeMax) {
		this.nom = nom;
		this.zone = zone;
		this.tarifHoraire = tarifHoraire;
		this.dureeMax = dureeMax;
	}

	public Zone(int id, String nom, double tarifHoraire) {
		this.id = id;
		this.nom = nom;
		this.tarifHoraire = tarifHoraire;
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

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public double getTarifHoraire() {
		return this.tarifHoraire;
	}

	public void setTarifHoraire(double tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}

	public double getDureeMax() {
		return this.dureeMax;
	}

	public void setDureeMax(double dureeMax) {
		this.dureeMax = dureeMax;
	}

}
