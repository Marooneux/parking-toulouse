package modele;

public class Utilisateur extends Compte {
	private String abonnement;

	public Utilisateur(String nom, String prenom, String email, String mdp, String abonnement) {
		super(nom, prenom, email, mdp);
		this.abonnement = abonnement;
	}

	public String getAbonnement() {
		return this.abonnement;
	}

	public void setAbonnement(String abonnement) {
		this.abonnement = abonnement;
	}

}
