package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
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
        TestPaiement.class,

        // Utils
        TestPasswordUtil.class,
        TestAuthManager.class
})
public class AllTests {

}
