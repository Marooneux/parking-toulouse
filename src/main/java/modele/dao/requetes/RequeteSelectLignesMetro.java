package modele.dao.requetes;

import modele.LigneMetro;

public class RequeteSelectLignesMetro extends Requete<LigneMetro> {

	@Override
	public String requete() {
		return "SELECT * FROM lignes_metro";
	}
}