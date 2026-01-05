package test.dao;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestDaoLigneMetro.class, TestDaoParking.class, TestDaoProximite.class, TestDaoReservationParking.class,
		TestDaoReservationVoirie.class, TestDaoUtilisateur.class, TestDaoZoneVoirie.class })
public class AllTests {

}
