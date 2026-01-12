package modele.dao.requetes;

import modele.Abonnement;

public class RequeteSelectAbonnement extends Requete<Abonnement> {

	@Override
	public String requete() {
		return "SELECT * FROM abonnements";
	}
}
