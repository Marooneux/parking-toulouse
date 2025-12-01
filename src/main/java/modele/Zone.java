package modele;

public class Zone {

    public enum CouleurZone {
        BLEU,
        ROUGE,
        JAUNE,
        VERTE,
        ORANGE
    }
    private String nom;
    private CouleurZone couleur;
    private double tarifHoraire;
    private double dureeMax; // Durée de stationnement max en heures

    public Zone(String nom, CouleurZone couleur, double tarifHoraire, double dureeMax) {
        this.nom = nom;
        this.couleur = couleur;
        this.tarifHoraire = tarifHoraire;
        this.dureeMax = dureeMax;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CouleurZone getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurZone couleur) {
        this.couleur = couleur;
    }

    public double getTarifHoraire() {
        return tarifHoraire;
    }

    public void setTarifHoraire(double tarifHoraire) {
        this.tarifHoraire = tarifHoraire;
    }
    
    public double getDureeMax() {
    	return this.dureeMax;
    }
    
    public void setDureeMax(double dureeMax) {
    	this.dureeMax = dureeMax;
    }

}
