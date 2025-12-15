package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.LigneMetro;

public class RequeteInsertLigneMetro extends Requete<LigneMetro> {

	@Override
	public String requete() {
		return "INSERT INTO lignes_metro (nom, adresse, nombre_places_max, hauteur_max, horaire_ouverture, horaire_fermeture, contient_places_moto) VALUES (?,?,?,?,?,?,?)";
	}

	@Override
	public void parametres(PreparedStatement statement, LigneMetro lM) throws SQLException {
		statement.setInt(1, lM.getId());
		statement.setString(2, lM.getNom());
		statement.setString(3, lM.getCouleur());
	}
}
