package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestAdministrateurParking.class, TestAdministrateurSysteme.class, TestCompte.class,
		TestLigneMetro.class, TestPaiement.class, TestParking.class, TestProximite.class, TestReservationParking.class,
		TestReservationVoirie.class, TestUtilisateur.class, TestZone.class })
public class AllTests {

}
