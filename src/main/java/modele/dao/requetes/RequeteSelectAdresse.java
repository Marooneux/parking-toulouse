package modele.dao.requetes;

import modele.Adresse;

public class RequeteSelectAdresse extends Requete<Adresse> {

	@Override
	public String requete() {
		return "SELECT * FROM adresse";
	}

}
