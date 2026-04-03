package test.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestAuthService.class,
	TestDaoAbonne.class,
	TestDaoAbonnement.class,
	TestDaoAdresse.class,
	TestDaoLigneMetro.class,
	TestDaoParking.class,
	TestDaoProximite.class,
	TestDaoReservationParking.class,
	TestDaoReservationVoirie.class,
	TestDaoUtilisateur.class,
	TestDaoVehicule.class,
	TestDaoZoneVoirie.class,
	TestIterateur.class
})
public class AllTests {

}
