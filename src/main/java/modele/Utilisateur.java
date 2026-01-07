package modele;

public class Utilisateur extends Compte {
	private Abonnement abonnement;

	public Utilisateur(int id, String nom, String prenom, String email, String mdp, Abonnement abonnement) {
		super(id, nom, prenom, email, mdp);
		this.abonnement = abonnement;
	}

	public Abonnement getAbonnement() {
		return this.abonnement;
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

	public boolean estAbonne() {
		return this.abonnement != null && this.abonnement.estValide();
	}
}
