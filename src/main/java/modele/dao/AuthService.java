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
		String sql = "SELECT id, nom, prenom, email, mdp, user_type FROM utilisateurs WHERE email=?";
		try (PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String stored = rs.getString("mdp");
					boolean valide = false;
					try {
						valide = PasswordUtil.checkMdp(mdp, stored);
					} catch (Exception e) {
						// Pas un hash BCrypt, fallback texte clair
					}
					if (!valide && stored != null) {
						valide = stored.equals(mdp);
					}
					if (!valide) {
						return null;
					}
					return new Utilisateur(
							rs.getInt("id"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							stored,
							parseType(rs.getString("user_type")));
				}
			}
		}
		return null;
	}

	private static Type parseType(String dbValue) {
		if (dbValue == null) {
			return Type.CLIENT;
		}
		switch (dbValue.trim().toUpperCase()) {
		case "PARKINGADMIN":
			return Type.PARKINGADMIN;
		case "SYSADMIN":
			return Type.SYSADMIN;
		default:
			return Type.CLIENT;
		}
	}
}
