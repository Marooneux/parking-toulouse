package modele;

import java.time.LocalDateTime;

public class ReservationVoirie {
    private int id;
    private String immatriculation;
    private String typeVehicule;
    private LocalDateTime dateDebut;
    private int dureeMinutes;
    private int idZone;
    private int idUtilisateur;

    public ReservationVoirie(int id, String immatriculation, String typeVehicule, LocalDateTime dateDebut,
                             int dureeMinutes, int idZone, int idUtilisateur) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.typeVehicule = typeVehicule;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.idZone = idZone;
        this.idUtilisateur = idUtilisateur;
    }

    public int getId() { return id; }
    public String getImmatriculation() { return immatriculation; }
    public String getTypeVehicule() { return typeVehicule; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public int getDureeMinutes() { return dureeMinutes; }
    public int getIdZone() { return idZone; }
    public int getIdUtilisateur() { return idUtilisateur; }
}