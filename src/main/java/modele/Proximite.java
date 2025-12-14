package modele;

public class Proximite {
    private int idParking;
    private int idLigneMetro;
    private int distanceMetres;

    public Proximite(int idParking, int idLigneMetro, int distanceMetres) {
        this.idParking = idParking;
        this.idLigneMetro = idLigneMetro;
        this.distanceMetres = distanceMetres;
    }

    public int getIdParking() { return idParking; }
    public int getIdLigneMetro() { return idLigneMetro; }
    public int getDistanceMetres() { return distanceMetres; }
}