package modele;

import java.sql.SQLException;
import java.time.LocalTime;

import modele.dao.DaoParking;

public class Parking {
	private int id;
	private String nom;
	private int capacite;
	private int nbPlacesOccupees;
	private double hauteurMax;
	private LocalTime horaireOuverture;
	private LocalTime horaireFermeture;
	private boolean contientPlacesMoto;
	private double tarif;
	private Adresse adresse;

	public Parking(int id, String nom, int capacite, double hauteurMax,
			LocalTime horaireOuverture, LocalTime horaireFermeture,
			boolean contientPlacesMoto, Adresse adresse) {
		this(id, nom, capacite, 0, hauteurMax, horaireOuverture, horaireFermeture, contientPlacesMoto, 0.0, adresse);
	}

	public Parking(String nom, String adresseRue, int capacite, int nbPlacesOccupees, double hauteurMax,
			LocalTime horaireOuverture, LocalTime horaireFermeture, boolean contientPlacesMoto, double tarif) {
		this(0, nom, capacite, nbPlacesOccupees, hauteurMax, horaireOuverture, horaireFermeture, contientPlacesMoto, tarif,
			new Adresse(0, null, adresseRue, null, null));
	}

	public Parking(int id, String nom, int capacite, int nbPlacesOccupees, double hauteurMax,
			LocalTime horaireOuverture, LocalTime horaireFermeture, boolean contientPlacesMoto, double tarif,
			Adresse adresse) {
		this.id = id;
		this.nom = nom;
		this.capacite = capacite;
		this.nbPlacesOccupees = nbPlacesOccupees;
		this.hauteurMax = hauteurMax;
		this.horaireOuverture = horaireOuverture;
		this.horaireFermeture = horaireFermeture;
		this.contientPlacesMoto = contientPlacesMoto;
		this.tarif = tarif;
		this.adresse = adresse;
	}

	public int calculerNbPlacesOccupees() {
		DaoParking dao = new DaoParking();
		try {
			return dao.getNbPlacesOccupees(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.nbPlacesOccupees;
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

	public int getCapacite() {
		return this.capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getNbPlacesMax() {
		return this.capacite;
	}

	public void setNbPlacesMax(int capacite) {
		this.capacite = capacite;
	}

	public int getNbPlacesOccupees() {
		return this.nbPlacesOccupees;
	}

	public void setNbPlacesOccupees(int nbPlacesOccupees) {
		this.nbPlacesOccupees = nbPlacesOccupees;
	}

	public double getHauteurMax() {
		return this.hauteurMax;
	}

	public void setHauteurMax(double hauteurMax) {
		this.hauteurMax = hauteurMax;
	}

	public double getHauteur() {
		return this.hauteurMax;
	}

	public void setHauteur(double hauteur) {
		this.hauteurMax = hauteur;
	}

	public LocalTime getHoraireOuverture() {
		return this.horaireOuverture;
	}

	public void setHoraireOuverture(LocalTime horaireOuverture) {
		this.horaireOuverture = horaireOuverture;
	}

	public LocalTime getHoraireFermeture() {
		return this.horaireFermeture;
	}

	public void setHoraireFermeture(LocalTime horaireFermeture) {
		this.horaireFermeture = horaireFermeture;
	}

	public boolean isContientPlacesMoto() {
		return this.contientPlacesMoto;
	}

	public void setContientPlacesMoto(boolean contientPlacesMoto) {
		this.contientPlacesMoto = contientPlacesMoto;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public void setAdresse(String rue) {
		this.adresse = new Adresse(0, null, rue, null, null);
	}

	public LocalTime getHeureOuverture() {
		return this.horaireOuverture;
	}

	public LocalTime getHeureFermeture() {
		return this.horaireFermeture;
	}

	public double getTarif() {
		return this.tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public void ajouterNbPlacesOccupes(int nb) {
		this.nbPlacesOccupees = Math.max(0, Math.min(this.capacite, this.nbPlacesOccupees + nb));
	}

	public void enleverNbPlacesOccupes(int nb) {
		this.ajouterNbPlacesOccupes(-nb);
	}

	public Boolean estOuvertApres(LocalTime heure) {
		return (heure.isAfter(this.horaireOuverture) && heure.isBefore(this.horaireFermeture));
	}
}
