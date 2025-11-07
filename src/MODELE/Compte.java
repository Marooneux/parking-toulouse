package MODELE;

public abstract class Compte {
	private String nom;
	private String prenom;
	private String mdp;
	private String email;
	
	public Compte(String nom, String prenom, String email, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
	}
	
	
	public boolean logIn(String email, String motDePasse) {
		return this.email.equals(email) && this.mdp.equals(motDePasse);
	}

	
	
	// Getters & setters
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	
	
}
