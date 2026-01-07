package modele;

public class Utilisateur extends Compte {
	private int id;
    private String type;

	public Utilisateur(String nom, String prenom, String email, String passwd, String type) {
		super(nom, prenom, email, passwd);
        this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
