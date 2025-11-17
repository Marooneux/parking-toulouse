package MODELE;

public abstract class Compte {
	private String nom;
	private String prenom;
	private String mdp;
	private String email;

	public Compte(String nom, String prenom, String email, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
	}

	public boolean connectionValide(String email, String motDePasse) {
		return this.email.equals(email) && this.mdp.equals(motDePasse);
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String ancienMdp, String nouveauMdp) {
		if (this.mdp == ancienMdp) {
			this.mdp = nouveauMdp;
		}
	}

}
