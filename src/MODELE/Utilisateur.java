package MODELE;

public class Utilisateur extends Compte {

	public Utilisateur(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
	}

	public void modifierProfil(String nouveauNom, String nouveauprenom, String nouvelEmail, String ancienMdp, String nouveauMdp) {
		this.setNom(nouveauNom);
		this.setPrenom(nouveauprenom);
		this.setEmail(nouvelEmail);
		this.setMdp(ancienMdp, nouveauMdp);
	}

}
