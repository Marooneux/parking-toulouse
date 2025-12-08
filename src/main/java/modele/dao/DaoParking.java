package modele.dao;

import modele.Parking;
import modele.dao.requetes.RequesteSelectParkingById;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoParking implements Dao<Parking> {

    public static void main(String[] args) throws SQLException {
        DaoParking daoParking = new DaoParking();

        Parking p = daoParking.getOne(1);
        System.out.println(p);
    }

    @Override
    public void create(Parking donnee) throws SQLException {
        Connection cn = UtMySQLDataSource.getConnexion();
        RequesteSelectParkingById insertRequest = new RequesteSelectParkingById();
        PreparedStatement insert = cn.prepareStatement(insertRequest.requete());
        insertRequest.parameters(insert);
        insert.executeUpdate();
    }

    @Override
    public void update(Parking donnee) throws SQLException {
        Connection cn = UtMySQLDataSource.getConnexion();
        PreparedStatement update = cn.prepareStatement("UPDATE parking SET name = ? WHERE id = ?");
        update.executeUpdate();
    }

    @Override
    public void delete(Parking donnee) throws SQLException {

    }

    @Override
    public List<Parking> findAll() throws SQLException {
        Connection cn = UtMySQLDataSource.getConnexion();
        PreparedStatement select = cn.prepareStatement("SELECT * FROM parking");
        ResultSet rs = select.executeQuery();
        List<Parking> parkings = new ArrayList<>();
        while (rs.next()) {
            //parkings.add(new Parking());
        }
        return parkings;
    }

    // Todo: Verifier la bonne foçon de retourner un seul ResultSet();
    @Override
    public Parking findById(int id) throws SQLException {
        UtMySQLDataSource.creerAcces("root", "claudio");
        Connection cn = UtMySQLDataSource.getConnexion();
        PreparedStatement select = cn.prepareStatement("SELECT * FROM parkings WHERE idparkings = ?");
        select.setInt(1, id);
        ResultSet rs = select.executeQuery();
        Parking  parking = null;
        if (rs.next()) {
           parking = new Parking(rs.getString(2),
                                rs.getString(3),
                                rs.getDouble(4),
                                rs.getInt(5),
                                rs.getDouble(6)
                                );
           return parking;
        }
        return null;
    }
}
