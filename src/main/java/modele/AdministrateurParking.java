package modele;

import java.time.LocalTime;

public class AdministrateurParking extends Compte {

	public AdministrateurParking(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
	}

	public void modifierInfoParking(Parking p, String nouveauNom, String nouvelleAdresse, double nouveauTarif,
			int nouveauNbPlacesTotales, double nouvelleHauteur, LocalTime nouvelleHeureOuverture,
			LocalTime nouvelleHeureFermeture) {
		p.setNom(nouveauNom);
		p.setAdresse(nouvelleAdresse);
		p.setTarif(nouveauTarif);
		p.setNbPlacesMax(nouveauNbPlacesTotales);
		p.setHauteur(nouvelleHauteur);
		p.setHeureOuverture(nouvelleHeureOuverture);
		p.setHeureFermeture(nouvelleHeureFermeture);
	}

	@Override
	public String toString() {
		return "AdministrateurParking [nom =" + this.getNom() + ", prenom ="
				+ this.getPrenom()
				+ ", email =" + this.getEmail() + "]";
	}

}
