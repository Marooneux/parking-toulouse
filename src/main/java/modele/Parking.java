package modele;

import java.time.LocalTime;

public class Parking {
	private static double tarif; // prix pour 15mins de stationnement

	private int id;
	private String nom;
	private String adresse;
	private int nbPlacesMax;
	private int nbPlacesOccupees;
	private double hauteur;
	private LocalTime heureOuverture;
	private LocalTime heureFermeture;
	private boolean contientPlacesMoto;

	public Parking(String nom, String adresse, int nbPlacesMax, double hauteur,
			LocalTime heureOuverture, LocalTime heureFermeture, boolean contientPlacesMoto) {
		this.nom = nom;
		this.adresse = adresse;
		this.nbPlacesMax = nbPlacesMax;
		this.nbPlacesOccupees = 0;
		this.hauteur = hauteur;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
		this.contientPlacesMoto = contientPlacesMoto;
	}

	public static double getTarif() {
		return tarif;
	}

	public static void setTarif(double tarif) {
		Parking.tarif = tarif;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getNbPlacesMax() {
		return this.nbPlacesMax;
	}

	public void setNbPlacesMax(int nbPlacesMax) {
		this.nbPlacesMax = nbPlacesMax;
	}

	public int getNbPlacesOccupees() {
		return this.nbPlacesOccupees;
	}

	public void setNbPlacesOccupees(int nbPlacesOccupees) {
		this.nbPlacesOccupees = nbPlacesOccupees;
	}

	public double getHauteur() {
		return this.hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public LocalTime getHeureOuverture() {
		return this.heureOuverture;
	}

	public void setHeureOuverture(LocalTime heureOuverture) {
		this.heureOuverture = heureOuverture;
	}

	public LocalTime getHeureFermeture() {
		return this.heureFermeture;
	}

	public void setHeureFermeture(LocalTime heureFermeture) {
		this.heureFermeture = heureFermeture;
	}

	public boolean isContientPlacesMoto() {
		return this.contientPlacesMoto;
	}

	public void setContientPlacesMoto(boolean contientPlacesMoto) {
		this.contientPlacesMoto = contientPlacesMoto;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int nbPlacesOccupees() {
		return this.nbPlacesOccupees;
	}

	public Boolean estOuvert(LocalTime heure) {
		return (heure.isAfter(this.heureOuverture) && heure.isBefore(this.heureFermeture));
	}

	public void ajouterVehicule(Vehicule v) {
		if (this.nbPlacesOccupees < this.nbPlacesMax) {
			this.nbPlacesOccupees += 1;
		}
	}

	public void enleverVehicule(Vehicule v) {
		if (this.nbPlacesOccupees > 0) {
			this.nbPlacesOccupees -= 1;
		}
	}

	@Override
	public String toString() {
		return "Parking{" +
				"nom='" + this.nom + '\'' +
				", adresse='" + this.adresse + '\'' +
				", tarif=" + tarif +
				", nbPlacesOccupees=" + this.nbPlacesOccupees +
				", nbPlacesTotales=" + this.nbPlacesMax +
				", hauteur=" + this.hauteur +
				", heureOuverture=" + this.heureOuverture +
				", heureFermeture=" + this.heureFermeture +
				'}';
	}
}
