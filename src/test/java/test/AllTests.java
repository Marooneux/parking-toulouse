package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		TestPasswordUtil.class,

		// Parking administration
		AdminParkingTest.class,

		// Abonnement & user-related tests
		TestAbonne.class,
		TestAbonnement.class,
		TestAdresse.class,
		TestCompte.class,
		TestUtilisateur.class,
		TestVehicule.class,

		// Metro & proximity
		TestLigneMetro.class,
		TestProximite.class,

		// Parking & voirie
		TestParking.class,
		TestStationnementVoirie.class,
		TestReservationParking.class,
		TestReservationVoirie.class,

		// Zones
		TestZone.class,

		// Paiement
		TestPaiement.class
})
public class AllTests {

}
