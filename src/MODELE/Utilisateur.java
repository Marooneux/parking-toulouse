package MODELE;

public class Utilisateur {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private String role;

	public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, String role) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
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

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean logIn(String email, String motDePasse) {
		return this.email.equals(email) && this.motDePasse.equals(motDePasse);
	}

	public void logOut() {
		System.out.println(this.nom + " s'est déconnecté ");
	}

	public void consulterProfil() {
		System.out.println("Nom : " + this.nom);
		System.out.println("Prénom : " + this.prenom);
		System.out.println("Email : " + this.email);
		System.out.println("Rôle : " + this.role);
	}

	public void modifierProfil(String nouveauNom, String nouveauprenom, String nouvelEmail, String nouveaumotDepasse) {
		this.nom = nouveauNom;
		this.prenom = nouveauprenom;
		this.email = nouvelEmail;
		this.motDePasse = nouveaumotDepasse;
	}

}
