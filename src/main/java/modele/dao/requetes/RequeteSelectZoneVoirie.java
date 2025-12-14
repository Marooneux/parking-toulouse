package modele.dao.requetes;

import modele.Zone;

public class RequeteSelectZoneVoirie extends Requete<Zone> {
    @Override
    public String requete() {
        return "SELECT * FROM zones_voirie";
    }
}