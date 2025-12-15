package modele;

public class Utilisateur extends Compte {
	private int id;
	private String abonnement;

	public Utilisateur(int id, String nom, String prenom, String email, String mdp, String abonnement) {
		super(nom, prenom, email, mdp);
		this.id = id;
		this.abonnement = abonnement;
	}

	public String getAbonnement() {
		return this.abonnement;
	}

	public void setAbonnement(String abonnement) {
		this.abonnement = abonnement;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
