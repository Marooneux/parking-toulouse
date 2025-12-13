package modele.dao.requetes;

import modele.Proximite;

public class RequeteSelectProximites extends Requete<Proximite> {
    @Override
    public String requete() {
        return "SELECT * FROM est_proche_de";
    }
}