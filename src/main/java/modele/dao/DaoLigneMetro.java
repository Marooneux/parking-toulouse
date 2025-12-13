package modele.dao;

import modele.LigneMetro;
import modele.dao.requetes.RequeteSelectLignesMetro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoLigneMetro extends DaoModele<LigneMetro> {
    @Override
    public void create(LigneMetro donnees) throws SQLException {}

    @Override
    public void update(LigneMetro donnees) throws SQLException {}

    @Override
    public void delete(LigneMetro donnees) throws SQLException {}

    @Override
    public List<LigneMetro> findAll() throws SQLException {
        return this.find(new RequeteSelectLignesMetro());
    }

    @Override
    protected LigneMetro creerInstance(ResultSet curseur) throws SQLException {
        return new LigneMetro(
                curseur.getInt("id"),
                curseur.getString("nom"),
                curseur.getString("couleur")
        );
    }
}