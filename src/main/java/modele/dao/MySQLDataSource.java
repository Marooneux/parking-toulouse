package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDataSource {
	private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/sae_parking?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
	private static Connection connexion = null;
	private static String url = DEFAULT_URL;
	private static String login;
	private static String motDePasse;

	private MySQLDataSource() {
	}

	/**
	 * Configure l'accès en lisant d'abord les propriétés JVM (ex: Surefire), puis
	 * les variables d'environnement, avec un URL par défaut en dernier recours.
	 */
	public static void creerAcces() {
		String resolvedUrl = firstNonEmpty(System.getProperty("DB_URL"), System.getenv("DB_URL"), DEFAULT_URL);
		String resolvedUser = firstNonEmpty(System.getProperty("DB_USER"), System.getenv("DB_USER"));
		String resolvedPass = firstNonEmpty(
				System.getProperty("DB_PASSWORD"), System.getenv("DB_PASSWORD"),
				System.getProperty("DB_PASS"), System.getenv("DB_PASS"));

		if (resolvedUser == null) {
			throw new IllegalStateException("DB_USER non défini (propriété JVM ou variable d'environnement)");
		}

		MySQLDataSource.url = resolvedUrl;
		MySQLDataSource.login = resolvedUser;
		MySQLDataSource.motDePasse = resolvedPass != null ? resolvedPass : "";
		MySQLDataSource.connexion = null;
	}

	/**
	 * Configure l'accès en dur (conserve l'API existante si besoin ponctuel).
	 */
	public static void creerAcces(String pLogin, String pMdp) {
		MySQLDataSource.url = DEFAULT_URL;
		MySQLDataSource.login = pLogin;
		MySQLDataSource.motDePasse = pMdp;
		MySQLDataSource.connexion = null;
	}

	public static Connection getConnexion() throws SQLException {
		if (MySQLDataSource.connexion == null || !MySQLDataSource.connexion.isValid(2)) {
			MySQLDataSource.connexion = DriverManager.getConnection(
					MySQLDataSource.url,
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

	private static String firstNonEmpty(String... values) {
		for (String v : values) {
			if (v != null && !v.isBlank()) {
				return v;
			}
		}
		return null;
	}
}