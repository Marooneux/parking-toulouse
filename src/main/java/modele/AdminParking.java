package modele;

public class AdminParking {

	private Utilisateur utilisateur;
	private Parking parking;

	public AdminParking(Utilisateur utilisateur, Parking parking) {
		this.utilisateur = utilisateur;
		this.parking = parking;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Parking getParking() {
		return this.parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

}
