package modele.dao.requetes;

import java.sql.PreparedStatement;

public abstract class Requete<T> {
    public abstract String request();

    public void parameters(PreparedStatement statement, String ...id) {};

    public void parameters(PreparedStatement statement, T donnee) {};
}
