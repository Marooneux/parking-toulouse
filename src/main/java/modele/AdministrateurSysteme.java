package modele;

public class AdministrateurSysteme extends Compte {
	private EnsembleParking parkings;

	public AdministrateurSysteme(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
		this.parkings = new EnsembleParking();
	}

	public AdministrateurParking creerCompteAdminParking(String nom, String prenom, String email,
			String motDePasse) {
		return new AdministrateurParking(nom, prenom, email, motDePasse);
	}

	public void ajouterParking(Parking nouveauParking) {
		this.parkings.ajouterParking(nouveauParking);
	}

	public void supprimerParking(Parking parking) {
		this.parkings.retirerParking(parking);
	}

}