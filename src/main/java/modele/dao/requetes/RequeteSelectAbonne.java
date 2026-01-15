package modele.dao.requetes;

import modele.Abonne;

public class RequeteSelectAbonne extends Requete<Abonne> {

	@Override
	public String requete() {
		return "SELECT * FROM abonne";
	}
}
