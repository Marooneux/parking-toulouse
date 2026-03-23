package test;

import org.junit.runner.RunWith; 
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
		TestLigneMetro.class, TestPaiement.class, TestParking.class, TestProximite.class, TestReservationParking.class,
		TestReservationVoirie.class, TestUtilisateur.class, TestZone.class })
public class AllTests {

}
