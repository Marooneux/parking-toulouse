package modele;

public abstract class Compte {
	private int id;
	private String nom;
	private String prenom;
	private String mdpHash;
	private String email;

	public Compte(int id, String nom, String prenom, String email, String mdpHash) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdpHash = mdpHash;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean verifierMdp(String mdpAVerifier) {
		return GestionMotDePasse.verifierMdp(mdpAVerifier, this.mdpHash);
	}

	public String getMdp() {
		return this.mdpHash;
	}

	public void setMdp(String ancienMdp, String nouveauMdp) {
		if (GestionMotDePasse.verifierMdp(nouveauMdp, ancienMdp)) {
			this.mdpHash = GestionMotDePasse.hashMdp(nouveauMdp);
		}
	}

	@Override
	public String toString() {
		return "Compte [id=" + this.id + ", nom=" + this.nom + ", prenom=" + this.prenom + ", mdpHash=" + this.mdpHash
				+ ", email=" + this.email + "]";
	}

}
