package test.dao;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.dao.DaoModele;
import modele.dao.Iterateur;
import modele.dao.MySQLDataSource;

public class TestIterateur {

    private static Connection cn;

    // Simple model for testing
    static class Dummy {
        int id;
        String name;

        Dummy(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    // Minimal DaoModele implementation
    static class DaoDummy extends DaoModele<Dummy> {

        @Override
        protected Dummy creerInstance(ResultSet rs) throws SQLException {
            return new Dummy(
                rs.getInt("id"),
                rs.getString("name")
            );
        }

        @Override
        public void create(Dummy donnee) throws SQLException { /* Non utilisé dans les tests d'itérateur */ }

        @Override
        public void update(Dummy donnee) throws SQLException { /* Non utilisé dans les tests d'itérateur */ }

        @Override
        public void delete(Dummy donnee) throws SQLException { /* Non utilisé dans les tests d'itérateur */ }

        @Override
        public java.util.List<Dummy> findAll() throws SQLException {
            return null;
        }
    }

    @BeforeClass
    public static void initConnexion() {
        MySQLDataSource.creerAcces();
    }

    @Before
    public void setUp() throws Exception {
        cn = MySQLDataSource.getConnexion();
        cn.setAutoCommit(false);

        Statement st = cn.createStatement();
        st.execute("CREATE TEMPORARY TABLE dummy_test (id INT, name VARCHAR(50))");
        st.execute("INSERT INTO dummy_test VALUES (1, 'A')");
        st.execute("INSERT INTO dummy_test VALUES (2, 'B')");
    }

    @After
    public void tearDown() throws Exception {
        if (cn != null) {
            cn.rollback();
            MySQLDataSource.deconnecter();
        }
    }

    @Test
    public void testIterateurSimple() throws Exception {
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM dummy_test ORDER BY id");
        ResultSet rs = ps.executeQuery();

        Iterateur<Dummy> it = new Iterateur<>(rs, new DaoDummy());

        // Row 1
        assertTrue(it.hasNext());
        Dummy d1 = it.next();
        assertEquals(1, d1.id);
        assertEquals("A", d1.name);

        // Row 2
        assertTrue(it.hasNext());
        Dummy d2 = it.next();
        assertEquals(2, d2.id);
        assertEquals("B", d2.name);

        // End
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsAtEnd() throws Exception {
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM dummy_test ORDER BY id");
        ResultSet rs = ps.executeQuery();

        Iterateur<Dummy> it = new Iterateur<>(rs, new DaoDummy());

        it.next(); // row 1
        it.next(); // row 2
        it.next(); // should throw
    }
}
