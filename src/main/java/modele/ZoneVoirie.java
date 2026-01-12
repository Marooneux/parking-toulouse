package modele;

public class ZoneVoirie {
	private int id;
	private String nom;
	private double tarifHoraire;
	private double dureeMax;

	public ZoneVoirie(int id, String nom, double tarifHoraire, double dureeMax) {
		this.id = id;
		this.nom = nom;
		this.tarifHoraire = tarifHoraire;
		this.dureeMax = dureeMax;
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
