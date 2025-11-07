package MODELE;

import java.util.ArrayList;
import java.util.List;

public class AdministrateurSysteme extends Compte {

	public AdministrateurSysteme(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
	}

	public AdministrateurParking creerCompteAdminParking(String nom, String prenom, String email,
			String motDePasse) {
		return new AdministrateurParking(nom, prenom, email, motDePasse);
	}



	public void modifierInfoParking(Parking p, String nouveauNom, String nouvelleAdresse, double nouveauTarif,
			int nouvellesPlaces) {
		p.setNom(nouveauNom);
		p.setAdresse(nouvelleAdresse);
		p.setTarifHoraire(nouveauTarif);
		p.setNombrePlaces(nouvellesPlaces);
	}

}
