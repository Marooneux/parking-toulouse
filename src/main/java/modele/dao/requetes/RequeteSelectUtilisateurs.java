package modele.dao.requetes;

import modele.Utilisateur;

public class RequeteSelectUtilisateurs extends Requete<Utilisateur> {
    @Override
    public String requete() {
        return "SELECT * FROM utilisateurs";
    }
}