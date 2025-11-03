package MODELE;

import java.util.ArrayList;
import java.util.List;

public class AdministrateurSysteme extends Utilisateur {

	public AdministrateurSysteme(int id, String nom, String prenom, String email, String motDePasse, String role) {
		super(id, nom, prenom, email, motDePasse, role);
		// TODO Auto-generated constructor stub
	}

	public AdministrateurParking creerCompteAdminParking(int id, String nom, String prenom, String email,
			String motDePasse) {
		return new AdministrateurParking(id, nom, prenom, email, motDePasse, "admin");
	}

	public boolean supprimerUtilisateur(List<Utilisateur> utilisateurs, int idASupprimer) {
		return utilisateurs.removeIf(u -> u.getId() == idASupprimer);
	}

	public List<Utilisateur> rechercherUtilisateurs(List<Utilisateur> utilisateurs, String email, String nom,
			String prenom, int id) {
		List<Utilisateur> resultats = new ArrayList<>();

		for (Utilisateur u : utilisateurs) {
			boolean correspond = false;

			if (email != null && !email.isEmpty() && u.getEmail().equalsIgnoreCase(email)) {
				correspond = true;
			}
			if (nom != null && !nom.isEmpty() && u.getNom().equalsIgnoreCase(nom)) {
				correspond = true;
			}
			if (prenom != null && !prenom.isEmpty() && u.getPrenom().equalsIgnoreCase(prenom)) {
				correspond = true;
			}
			if (id != -1 && u.getId() == id) {
				correspond = true;
			}

			if (correspond) {
				resultats.add(u);
			}
		}

		return resultats;
	}

	public void modifierInfoParking(Parking p, String nouveauNom, String nouveauPrenom, String nouvelleAdresse,
			double nouveauTarif, int nouvellesPlaces) {
		p.setNom(nouveauNom);
		p.setPrenom(nouveauPrenom);
		p.setAdresse(nouvelleAdresse);
		p.setTarifHoraire(nouveauTarif);
		p.setNombrePlaces(nouvellesPlaces);
	}

	public List<AdministrateurParking> listerAdminsParking(List<Utilisateur> utilisateur) {
		List<AdministrateurParking> admins = new ArrayList<>();
		for (Utilisateur u : utilisateur) {
			if (u instanceof AdministrateurParking) {
				admins.add((AdministrateurParking) u);
			}
		}
		return admins;
	}
}
