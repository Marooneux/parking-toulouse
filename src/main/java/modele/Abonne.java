package modele;

public class Abonne {

	private Utilisateur utilisateur;
	private Abonnement abonnement;
	private boolean estActif;

	public Abonne(Utilisateur utilisateur, Abonnement abonnement, boolean estActif) {
		this.utilisateur = utilisateur;
		this.abonnement = abonnement;
		this.estActif = estActif;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Abonnement getAbonnement() {
		return this.abonnement;
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

	public boolean isEstActif() {
		return this.estActif;
	}

	public void setEstActif(boolean estActif) {
		this.estActif = estActif;
	}

}
