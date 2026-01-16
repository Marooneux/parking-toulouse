package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import modele.ZoneVoirie;

public class RequeteUpdateZoneVoirie extends Requete<ZoneVoirie> {

	@Override
	public String requete() {
		return "UPDATE zones_voirie SET couleur = ?, tarif_horaire = ?, duree_max = ?, debut_am = ?, fin_am = ?, "
				+ "debut_pm = ?, fin_pm = ? WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, ZoneVoirie donnee) throws SQLException {
		statement.setString(1, donnee.getCouleur());
		statement.setDouble(2, donnee.getTarifHoraire());
		statement.setDouble(3, donnee.getDureeMax());
		statement.setTime(4, donnee.getDebutAm() != null ? Time.valueOf(donnee.getDebutAm()) : null);
		statement.setTime(5, donnee.getFinAm() != null ? Time.valueOf(donnee.getFinAm()) : null);
		statement.setTime(6, donnee.getDebutPm() != null ? Time.valueOf(donnee.getDebutPm()) : null);
		statement.setTime(7, donnee.getFinPm() != null ? Time.valueOf(donnee.getFinPm()) : null);
		statement.setInt(8, donnee.getId());
	}
}