package MODELE;

import java.util.List;

public class AdministrateurParking extends Compte {

	public AdministrateurParking(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
	}

	
	public boolean ajouterParking(List<Parking> p, Parking nouveauParking) {
		return p.add(nouveauParking);
	}

	public boolean supprimerParking(List<Parking> p, int idParking) {
		return p.removeIf(u -> u.getId() == idParking);
	}

	public void modifierInfoParking(Parking p, String nouveauNom, String nouvelleAdresse, double nouveauTarif,
			int nouvellesPlaces) {
		p.setNom(nouveauNom);
		p.setAdresse(nouvelleAdresse);
		p.setTarifHoraire(nouveauTarif);
		p.setNombrePlaces(nouvellesPlaces);
	}

	public void afficherParkings(List<Parking> parkings) {
		for (Parking p : parkings) {
			System.out.println(
					"ID: " + p.getId() + "| Nom: " + p.getNom() + "| Tarif: " + p.getTarifHoraire() + "euro/h");
		}
	}

}
