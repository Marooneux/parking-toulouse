package modele;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class GestionMotDePasse {
	private static final SecureRandom RANDOM = new SecureRandom();
	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	private static final int ITERATIONS = 310_000;
	private static final int KEY_LENGTH = 256;

	public static String hashMdp(String password) {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);

		byte[] hash = pbkdf2(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);

		return Base64.getEncoder().encodeToString(salt) + ":" +
				Base64.getEncoder().encodeToString(hash);
	}

	public static boolean verifierMdp(String password, String stored) {
		String[] parts = stored.split(":");
		byte[] salt = Base64.getDecoder().decode(parts[0]);
		byte[] hash = Base64.getDecoder().decode(parts[1]);

		byte[] testHash = pbkdf2(password.toCharArray(), salt, ITERATIONS, hash.length * 8);

		return slowEquals(hash, testHash);
	}

	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLength) {
		try {
			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
			return skf.generateSecret(spec).getEncoded();
		} catch (Exception e) {
			throw new IllegalStateException("Erreur de hashing", e);
		}
	}

	private static boolean slowEquals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for (int i = 0; i < Math.min(a.length, b.length); i++) {
			diff |= a[i] ^ b[i];
		}
		return diff == 0;
	}
}
