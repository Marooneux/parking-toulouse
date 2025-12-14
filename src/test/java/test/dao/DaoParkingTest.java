package test.dao;

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;
import modele.dao.Iterateur;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DaoParkingTest {

    private DaoParking daoParking;

    @Before
    public void setup() throws SQLException {
        // Inicializa acesso ao banco de dados
        MySQLDataSource.creerAcces("root", "claudio");
        this.daoParking = new DaoParking();
    }

    @Test
    @DisplayName("Test create")
    public void testCreate() throws SQLException {
        Parking p = new Parking("TestCreate", "AdresseTest", 100, 2.0,
                Time.valueOf("08:00:00").toLocalTime(),
                Time.valueOf("18:30:00").toLocalTime(),
                true);
        daoParking.create(p);

        List<Parking> parkings = daoParking.findAll();
        assertTrue(parkings.stream().anyMatch(parking -> parking.getNom().equals("TestCreate")));

        // Cleanup
        daoParking.delete(p);
    }

    @Test
    @DisplayName("Test update")
    public void testUpdate() throws SQLException {
        Parking p = new Parking("TestUpdate", "AdresseTest", 100, 2.0,
                Time.valueOf("08:00:00").toLocalTime(),
                Time.valueOf("18:30:00").toLocalTime(),
                true);
        daoParking.create(p);

        p.setNom("TestUpdateModified");
        daoParking.update(p);

        List<Parking> parkings = daoParking.findAll();
        assertTrue(parkings.stream().anyMatch(parking -> parking.getNom().equals("TestUpdateModified")));

        // Cleanup
        daoParking.delete(p);
    }

    @Test
    @DisplayName("Test delete")
    public void testDelete() throws SQLException {
        Parking p = new Parking("TestDelete", "AdresseTest", 100, 2.0,
                Time.valueOf("08:00:00").toLocalTime(),
                Time.valueOf("18:30:00").toLocalTime(),
                true);
        daoParking.create(p);

        daoParking.delete(p);
        List<Parking> parkings = daoParking.findAll();
        assertFalse(parkings.stream().anyMatch(parking -> parking.getNom().equals("TestDelete")));
    }

    @Test
    @DisplayName("Test findAll")
    public void testFindAll() throws SQLException {
        List<Parking> parkings = daoParking.findAll();
        assertNotNull(parkings, "La liste des parkings ne doit pas être nulle");
        assertTrue("La liste peut être vide mais non nulle", parkings.size() >= 0);
    }

    @Test
    @DisplayName("Test findAllIte / Iterateur")
    public void testIterateur() throws SQLException {
        Iterateur<Parking> ite = daoParking.findAllIte();
        if (ite != null) {
            while (ite.hasNext()) {
                Parking p = ite.next();
                assertNotNull(p, "Chaque parking retourné par l'iterateur ne doit pas être null");
            }
        }
    }
}
