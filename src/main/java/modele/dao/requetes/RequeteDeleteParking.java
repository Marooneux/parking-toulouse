package modele.dao.requetes;

import modele.Parking;

public class RequeteDeleteParking extends Requete<Parking> {

	// TODO ajouter parametres
	@Override
	public String requete() {
		return "delete from Parking where idParking = ?";
	}

}
