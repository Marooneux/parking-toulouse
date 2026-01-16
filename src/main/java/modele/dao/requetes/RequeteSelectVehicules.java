package modele.dao.requetes;

import modele.Vehicule;

public class RequeteSelectVehicules extends Requete<Vehicule> {

    @Override
    public String requete() {
        return "SELECT * FROM vehicules";
    }
}
