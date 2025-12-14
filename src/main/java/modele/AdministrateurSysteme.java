package modele;

public class AdministrateurSysteme extends Compte {

	public AdministrateurSysteme(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
	}

	public AdministrateurParking creerCompteAdminParking(String nom, String prenom, String email,
			String motDePasse) {
        return new AdministrateurParking(nom, prenom, email, motDePasse);
    }
}