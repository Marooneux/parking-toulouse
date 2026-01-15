package modele;

public class Vehicule {

	public enum TypeVehicule {
		ELECTRIQUE, HYBRIDE, MOTO, NORMAL
	}

	private int id;
	private String immatriculation;
	private TypeVehicule type;
	private Utilisateur utilisateur;

	public Vehicule(int id, String immatriculation, TypeVehicule type, Utilisateur utilisateur) {
		this.id = id;
		this.immatriculation = immatriculation;
		this.type = type;
		this.utilisateur = utilisateur;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public TypeVehicule getType() {
		return this.type;
	}

	public void setType(TypeVehicule type) {
		this.type = type;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public static boolean immatriculationValide(String immatriculation) {
		return immatriculation.matches("[A-Z]{2}-[0-9]{3}-[A-Z]{2}");
	}
}
