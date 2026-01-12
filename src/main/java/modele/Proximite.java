package modele;

public class Proximite {
	private Parking parking;
	private LigneMetro ligneMetro;
	private int distanceMetres;

	public Proximite(Parking parking, LigneMetro ligneMetro, int distanceMetres) {
		this.parking = parking;
		this.ligneMetro = ligneMetro;
		this.distanceMetres = distanceMetres;
	}

	public Parking getParking() {
		return this.parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public LigneMetro getLigneMetro() {
		return this.ligneMetro;
	}

	public void setLigneMetro(LigneMetro ligneMetro) {
		this.ligneMetro = ligneMetro;
	}

	public int getDistanceMetres() {
		return this.distanceMetres;
	}

	public void setDistanceMetres(int distanceMetres) {
		this.distanceMetres = distanceMetres;
	}
}