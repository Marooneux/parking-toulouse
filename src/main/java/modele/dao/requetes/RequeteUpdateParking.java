package modele.dao.requetes;

import modele.Parking;

public class RequeteUpdateParking extends Requete<Parking> {

	// TODO ajouter parametres
	@Override
	public String requete() {
		return "update Parking set = ?";
	}

}
