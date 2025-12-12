package modele.dao.requetes;

import modele.Reservation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Requete<T> {
    public abstract String requete();

    public void parametres(PreparedStatement statement, String ...id) throws SQLException {};

    public void parametres(PreparedStatement statement, T donnee) throws SQLException {};
}
