package modele.dao.requetes;

import modele.ZoneVoirie;

public class RequeteSelectZoneVoirie extends Requete<ZoneVoirie> {

	@Override
	public String requete() {
		return "SELECT * FROM zones_voirie";
	}
}