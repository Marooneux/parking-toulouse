package MODELE;

public class Utilisateur extends Compte {

	public Utilisateur(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
	}

	public void consulterProfil() {
		System.out.println("Nom : " + getNom());
		System.out.println("Prénom : " + getPrenom());
		System.out.println("Email : " + getEmail());
	}

	public void modifierProfil(String nouveauNom, String nouveauprenom, String nouvelEmail, String nouveaumotDepasse) {
		setNom(nouveauNom);
		setPrenom(nouveauprenom);
		setEmail(nouvelEmail);
		setMdp(nouveaumotDepasse);
	}


}
