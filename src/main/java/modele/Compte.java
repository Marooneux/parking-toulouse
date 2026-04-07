package modele;

public abstract class Compte {
	private String nom;
	private String prenom;
	protected String motDePasse;
	private String email;

	public Compte(String nom, String prenom, String email, String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
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
		return this.motDePasse;
	}

	public void setMdp(String ancienMdp, String nouveauMdp) {
		if (this.motDePasse.equals(ancienMdp)) {
			this.motDePasse = nouveauMdp;
		}
	}

}
