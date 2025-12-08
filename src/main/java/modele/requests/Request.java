package modele.requests;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Request<T> {
    public abstract String request();

    public void parameters(PreparedStatement statement, String ...id) {};

    public void parameters(PreparedStatement statement, T donnee) {};
}
