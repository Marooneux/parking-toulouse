package modele;

public class ZoneVoirie {

	private int id;
	private String nom;
	private double tarifHoraire;
	private int dureeMax;

	public ZoneVoirie(int id, String nom, double tarifHoraire, int dureeMax) {
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

	public int getDureeMax() {
		return this.dureeMax;
	}

	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}

}
