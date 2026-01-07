package test.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestDaoLigneMetro.class, TestDaoParking.class, TestDaoProximite.class, TestDaoReservationParking.class,
		TestDaoReservationVoirie.class, TestDaoUtilisateur.class, TestDaoZoneVoirie.class })
public class AllTests {

}
