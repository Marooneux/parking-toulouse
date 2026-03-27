package test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.Utilisateur.Type;
import modele.dao.AuthService;

public class TestAuthService {

    @Test
    public void testParseTypeNull() {
        // default should be CLIENT
        Type t = invokeParseType(null);
        assertEquals(Type.CLIENT, t);
    }

    @Test
    public void testParseTypeParkingAdmin() {
        Type t = invokeParseType("PARKINGADMIN");
        assertEquals(Type.PARKINGADMIN, t);
    }

    @Test
    public void testParseTypeSysAdmin() {
        Type t = invokeParseType("SYSADMIN");
        assertEquals(Type.SYSADMIN, t);
    }

    @Test
    public void testParseTypeLowercase() {
        Type t = invokeParseType("parkingadmin");
        assertEquals(Type.PARKINGADMIN, t);
    }

    @Test
    public void testParseTypeUnknown() {
        Type t = invokeParseType("somethingElse");
        assertEquals(Type.CLIENT, t);
    }

    // Helper to call private static method via reflection
    private Type invokeParseType(String value) {
        try {
            var m = AuthService.class.getDeclaredMethod("parseType", String.class);
            m.setAccessible(true);
            return (Type) m.invoke(null, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
