package modele;

public class Utilisateur {
	public enum Type {
		PARKINGADMIN, SYSADMIN, CLIENT
	}

	private int id;
	private String nom;
	private String prenom;
	private String mdpHash;
	private String email;
	private Abonnement abonnement;
	private Type type;

	public Utilisateur(int id, String nom, String prenom, String email, String mdpHash, Abonnement abonnement,
			Type type) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdpHash = mdpHash;
		this.abonnement = abonnement;
		this.type = type;
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

	public Abonnement getAbonnement() {
		return this.abonnement;
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

	public boolean estAbonne() {
		return this.abonnement != null && this.abonnement.estValide();
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
