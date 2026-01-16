package modele;

public class Adresse {

	private int id;
	private int numero;
	private String rue;
	private int codePostal;
	private String ville;

	public Adresse(int numero, String rue, int codePostal, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int i) {
		this.numero = i;
	}

	public String getRue() {
		return this.rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(int i) {
		this.codePostal = i;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
}
