package MODELE;

public class AdministrateurParking extends Compte {

	public AdministrateurParking(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
	}

	public void modifierInfoParking(Parking p, String nouveauNom, String nouvelleAdresse, double nouveauTarif,
			int nouveauNbPlacesDisponibles) {
		p.setNom(nouveauNom);
		p.setAdresse(nouvelleAdresse);
		p.setTarif(nouveauTarif);
		p.setNbPlacesDisponibles(nouveauNbPlacesDisponibles);
	}

	@Override
	public String toString() {
		return "AdministrateurParking [nom =" + this.getNom() + ", prenom ="
				+ this.getPrenom()
				+ ", email =" + this.getEmail() + "]";
	}

}
