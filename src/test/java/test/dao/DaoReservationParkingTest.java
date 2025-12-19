package test.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modele.Parking;
import modele.ReservationParking;
import modele.dao.DaoParking;
import modele.dao.DaoReservationParking;
import modele.dao.MySQLDataSource;

public class DaoReservationParkingTest {

	private DaoReservationParking daoReservation;
	private DaoParking daoParking;

	@Before
	public void setup() throws SQLException {
		MySQLDataSource.creerAcces("root", "claudio");
		this.daoReservation = new DaoReservationParking();
		this.daoParking = new DaoParking();
	}

	private Parking ensureParking() throws SQLException {
		List<Parking> parkings = this.daoParking.findAll();
		if (parkings.isEmpty()) {
			Parking p = new Parking(1, "TestResvPark", "AdresseTest", 100, 2.0,
					Time.valueOf("08:00:00").toLocalTime(),
					Time.valueOf("18:30:00").toLocalTime(), true, 1.5);
			this.daoParking.create(p);
			return p;
		}
		return parkings.get(0);
	}

	@Test
	@DisplayName("Test create reservation")
	public void testCreate() throws SQLException {
		Parking p = this.ensureParking();
		ReservationParking r = new ReservationParking("AB-123-CD", p, LocalDateTime.now().minusHours(2));
		r.setDateDepart(LocalDateTime.now());
		this.daoReservation.create(r);

		List<ReservationParking> reservations = this.daoReservation.findAll();
		assertNotNull(reservations);
		assertTrue(reservations.stream().anyMatch(x -> x.getImmatriculation().equals("AB-123-CD")));

		this.daoReservation.delete(r);
	}

	@Test
	@DisplayName("Test update reservation")
	public void testUpdate() throws SQLException {
		Parking p = this.ensureParking();
		ReservationParking r = new ReservationParking("EF-456-GH", p, LocalDateTime.now().minusHours(3));
		r.setDateDepart(LocalDateTime.now().minusHours(1));
		this.daoReservation.create(r);

		r.setImmatriculation("EF-456-XX");
		this.daoReservation.update(r);

		List<ReservationParking> reservations = this.daoReservation.findAll();
		assertTrue(reservations.stream().anyMatch(x -> x.getImmatriculation().equals("EF-456-XX")));

		this.daoReservation.delete(r);
	}

	@Test
	@DisplayName("Test delete reservation")
	public void testDelete() throws SQLException {
		Parking p = this.ensureParking();
		ReservationParking r = new ReservationParking("ZZ-999-ZZ", p, LocalDateTime.now().minusHours(1));
		r.setDateDepart(LocalDateTime.now());
		this.daoReservation.create(r);

		this.daoReservation.delete(r);
		List<ReservationParking> reservations = this.daoReservation.findAll();
		assertFalse(reservations.stream().anyMatch(x -> x.getImmatriculation().equals("ZZ-999-ZZ")));
	}

	@Test
	@DisplayName("Test findAll reservations")
	public void testFindAll() throws SQLException {
		List<ReservationParking> reservations = this.daoReservation.findAll();
		assertNotNull(reservations);
	}
}
