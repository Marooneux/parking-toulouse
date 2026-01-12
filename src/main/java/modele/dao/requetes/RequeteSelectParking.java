package modele.dao.requetes;

import modele.Parking;

public class RequeteSelectParking extends Requete<Parking> {

	@Override
	public String requete() {
		return "SELECT * FROM parkings";
	}
}
