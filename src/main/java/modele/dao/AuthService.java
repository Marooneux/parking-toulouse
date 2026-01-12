package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.Utilisateur.Type;
import utils.PasswordUtil;

public class AuthService {
	/**
	 * Authentifie un utilisateur par mail et mot de passe. Cette methode recupere
	 * l'utilisateur par son mail, puis compare le mot de passe donné avec le hash.
	 * Si le mot de passe n'est pas un hash BCrypt, on compare directement.
	 */
	public static Utilisateur authenticate(String email, String mdp) throws SQLException {
		Connection cn = MySQLDataSource.getConnexion();
		String sql = "SELECT * FROM utilisateurs WHERE email=?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String stored = rs.getString("mdp");
			boolean valide = false;
			try {
				valide = PasswordUtil.checkMdp(mdp, stored);
			} catch (Exception e) {
				// Fallback
			}
			if (!valide) {
				valide = stored != null && stored.equals(mdp);
				return null;
			}
			return new Utilisateur(
					rs.getInt("id_utilisateur"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("mot_de_passe"),
					null,
					Type.valueOf(rs.getString("type")));
		}
		return null;
	}
}
