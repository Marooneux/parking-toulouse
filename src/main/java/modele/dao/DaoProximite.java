package modele.dao;

import modele.Proximite;
import modele.dao.requetes.RequeteSelectProximites;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoProximite extends DaoModele<Proximite> {
    @Override
    public void create(Proximite donnees) throws SQLException {}

    @Override
    public void update(Proximite donnees) throws SQLException {}

    @Override
    public void delete(Proximite donnees) throws SQLException {}

    @Override
    public List<Proximite> findAll() throws SQLException {
        return this.find(new RequeteSelectProximites());
    }

    @Override
    protected Proximite creerInstance(ResultSet c) throws SQLException {
        return new Proximite(
                c.getInt("id_parking"),
                c.getInt("id_ligne_metro"),
                c.getInt("distance_metres")
        );
    }
}