package modele;

public class AdministrateurSysteme extends Compte {

	public AdministrateurSysteme(int id, String nom, String prenom, String email, String mdp) {
		super(id, nom, prenom, email, mdp);
	}

	public AdministrateurParking creerAdministrateurParking(int id, String nom, String prenom, String email,
			String motDePasse) {
		return new AdministrateurParking(id, nom, prenom, email, motDePasse);
	}
}