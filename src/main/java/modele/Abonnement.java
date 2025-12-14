package modele;

public class Abonnement {
    private int id;
    private String nom;
    private String description;

    public Abonnement(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}