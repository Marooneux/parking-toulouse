package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAdministrateurParking.class, TestAdministrateurSysteme.class, TestCompte.class,
		TestLigneMetro.class, TestPaiement.class, TestParking.class, TestProximite.class, TestReservationParking.class,
		TestReservationVoirie.class, TestUtilisateur.class, TestZone.class })
public class AllTests {

}
