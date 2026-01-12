package modele;

public class LigneMetro {
	private int id;
	private String nom;
	private String couleur;

	public LigneMetro(int id, String nom, String couleur) {
		this.id = id;
		this.nom = nom;
		this.couleur = couleur;
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

	public String getCouleur() {
		return this.couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
}