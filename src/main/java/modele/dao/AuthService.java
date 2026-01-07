package modele.dao;

import modele.Utilisateur;
import utils.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    /**
     * Authenticate a user by email and password.
     * This method fetches the user by email, then validates the provided password
     * against the stored hash using BCrypt. For backward compatibility, if the
     * stored password is not a BCrypt hash, a direct equality check is performed.
     */
    public static Utilisateur authenticate(String email, String mdp) throws SQLException {
        Connection cn = MySQLDataSource.getConnexion();
        String sql = "SELECT * FROM utilisateurs WHERE email=?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String stored = rs.getString("mdp");
            boolean valid = false;
            try {
                // Preferred: BCrypt hashed passwords
                valid = PasswordUtil.checkMdp(mdp, stored);
            } catch (Exception ignored) {
                // Fallback: non-bcrypt stored value
            }
            if (!valid) {
                // Fallback to direct comparison for legacy plain-text entries
                valid = stored != null && stored.equals(mdp);
            }
            if (!valid) {
                return null;
            }
            return new Utilisateur(
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                rs.getString("mdp"),
                rs.getString("user_type")
            );
        }
        return null;
    }
}
