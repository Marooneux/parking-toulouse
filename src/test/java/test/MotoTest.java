package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modele.Moto;

public class MotoTest {

	private Moto moto;

	@Before
	public void setUp() {
		this.moto = new Moto(120, "AB-123-CD", true, false);
	}

	@Test
	public void testConstructorAndGetters() {
		assertEquals("voiture", this.moto.getType());
		assertEquals(120, this.moto.getHauteur());
		assertEquals("AB-123-CD", this.moto.getImmatriculation());
	}
}
