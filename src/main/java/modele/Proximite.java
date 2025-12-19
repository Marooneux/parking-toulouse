package modele;

public class Proximite {
	private int idParking;
	private int idLigneMetro;
	private int distanceMetres;

	public Proximite(int idParking, int idLigneMetro, int distanceMetres) {
		this.idParking = idParking;
		this.idLigneMetro = idLigneMetro;
		this.distanceMetres = distanceMetres;
	}

	public int getIdParking() {
		return this.idParking;
	}

	public void setIdParking(int idParking) {
		this.idParking = idParking;
	}

	public int getIdLigneMetro() {
		return this.idLigneMetro;
	}

	public void setIdLigneMetro(int idLigneMetro) {
		this.idLigneMetro = idLigneMetro;
	}

	public int getDistanceMetres() {
		return this.distanceMetres;
	}

	public void setDistanceMetres(int distanceMetres) {
		this.distanceMetres = distanceMetres;
	}

}