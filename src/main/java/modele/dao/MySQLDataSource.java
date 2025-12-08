package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDataSource {
	private static Connection connexion = null;
	private static String login;
	private static String motDePasse;
	private static final String URL = "jdbc:mysql://localhost:3306/parking?serverTimezone=UTC";

	private MySQLDataSource() {
	}

	public static void creerAcces(String pLogin, String pMdp) {
		MySQLDataSource.login = pLogin;
		MySQLDataSource.motDePasse = pMdp;
		MySQLDataSource.connexion = null;
	}

	public static Connection getConnexion() throws SQLException {
		if (MySQLDataSource.connexion == null) {
			MySQLDataSource.connexion = DriverManager.getConnection(
					MySQLDataSource.URL,
					MySQLDataSource.login,
					MySQLDataSource.motDePasse);
			MySQLDataSource.connexion.setAutoCommit(true);
		}

		return MySQLDataSource.connexion;
	}

	public static void commit() throws SQLException {
		MySQLDataSource.connexion.commit();
	}

	public static void rollback() throws SQLException {
		MySQLDataSource.connexion.rollback();
	}

	public static void deconnecter() throws SQLException {
		MySQLDataSource.connexion.close();
		MySQLDataSource.connexion = null;
	}
}