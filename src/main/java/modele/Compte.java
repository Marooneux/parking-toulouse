package modele;

public abstract class Compte {
	private String nom;
	private String prenom;
	private String email;
    private String passwd;

	public Compte(String nom, String prenom, String email, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
        this.passwd = mdp;
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
        return this.passwd;
    }

    public void setMdp(String OldPasswd, String NewPasswd) {
        this.passwd = NewPasswd;
    }

}
