package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtMySQLDataSource {
    private static final String NOM_SCHEMA = "sae_parking";
    private static Connection connexion = null;

    private static String login;
    private static String password;

    private static final String URL = "jdbc:mysql://localhost:3306/" +
                                        UtMySQLDataSource.NOM_SCHEMA + "?serverTimezone=UTC";

    private UtMySQLDataSource(String login, String password) {};

    public static void creerAcces(String pLogin, String pPassword) {
        UtMySQLDataSource.login = pLogin;
        UtMySQLDataSource.password = pPassword;
        UtMySQLDataSource.connexion = null;
    }

    public static Connection getConnexion() throws SQLException {
        if (connexion == null) {
            UtMySQLDataSource.connexion = DriverManager.getConnection(
                    UtMySQLDataSource.URL,
                    UtMySQLDataSource.login,
                    UtMySQLDataSource.password
            );
            UtMySQLDataSource.connexion.setAutoCommit(true);
        }
        return UtMySQLDataSource.connexion;
    }

    public static void commit() throws SQLException
    {
        UtMySQLDataSource.connexion.commit();
    }

    public static void rollback() throws SQLException
    {
        UtMySQLDataSource.connexion.rollback();
    }

    public static void deconnecter() throws SQLException
    {
        UtMySQLDataSource.connexion.close();
        UtMySQLDataSource.connexion = null;
    }
}
