package modele;

import utils.PasswordUtil;

public class Utilisateur extends Compte {
	public enum Type {
		SYSADMIN, PARKINGADMIN, CLIENT
	}

	private int id;
	private Type type;
	private Abonnement abonnement;

	public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, Type type) {
		super(nom, prenom, email, motDePasse);
		this.id = id;
		this.type = type;
	}

	public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, Abonnement abonnement, Type type) {
		super(nom, prenom, email, motDePasse);
		this.id = id;
		this.type = type;
		this.abonnement = abonnement;
	}
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setMdp(String ignoredAncienMdp, String nouveauMdp) {
		if (nouveauMdp != null && !nouveauMdp.isBlank()) {
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
