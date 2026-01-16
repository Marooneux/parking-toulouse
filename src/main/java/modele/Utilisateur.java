package modele;

import utils.PasswordUtil;

public class Utilisateur {
	public enum Type {
		SYSADMIN, PARKINGADMIN, CLIENT
	}

	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private Type type;
	private Abonnement abonnement;

	public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, Type type) {
		this(id, nom, prenom, email, motDePasse, null, type);
	}

	public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, Abonnement abonnement, Type type) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.type = type;
		this.abonnement = abonnement;
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

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean verifierMdp(String mdpAVerifier) {
		return PasswordUtil.checkMdp(mdpAVerifier, this.motDePasse);
	}

	public String getMdp() {
		return this.motDePasse;
	}

	public void setMdp(String ancienMdp, String nouveauMdp) {
		if (PasswordUtil.checkMdp(nouveauMdp, ancienMdp)) {
			this.motDePasse = PasswordUtil.hashMdp(nouveauMdp);
		}
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

	public Abonnement getAbonnement() {
		return this.abonnement;
	}

	public boolean estAbonne() {
		return this.abonnement != null;
	}
}
