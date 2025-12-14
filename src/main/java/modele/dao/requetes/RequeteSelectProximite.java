package modele.dao.requetes;

import modele.Proximite;

public class RequeteSelectProximite extends Requete<Proximite> {
    @Override
    public String requete() {
        return "SELECT * FROM est_proche_de";
    }
}