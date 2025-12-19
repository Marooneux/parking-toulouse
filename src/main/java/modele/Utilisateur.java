package modele;

public class Utilisateur extends Compte {
	private String abonnement;

	public Utilisateur(int id, String nom, String prenom, String email, String mdp, String abonnement) {
		super(id, nom, prenom, email, mdp);
		this.abonnement = abonnement;
	}

	public String getAbonnement() {
		return this.abonnement;
	}

	public void setAbonnement(String abonnement) {
		this.abonnement = abonnement;
	}
}
