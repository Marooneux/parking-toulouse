package modele.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    public abstract void create(T donnee) throws SQLException;
    public abstract void update(T donnee) throws SQLException;
    public abstract void delete(T donnee) throws SQLException;
    public abstract List<T> findAll() throws SQLException;
    public abstract T findById(int id) throws SQLException;
}
