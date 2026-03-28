package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.PasswordUtil;

public class TestPasswordUtil {

    @Test
    public void testHashMdpRetourneChaine() {
        String hash = PasswordUtil.hashMdp("monMotDePasse");
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }

    @Test
    public void testHashMdpEstDifferentDuMdpOriginal() {
        String mdp = "monMotDePasse";
        String hash = PasswordUtil.hashMdp(mdp);
        assertNotEquals(mdp, hash);
    }

    @Test
    public void testCheckMdpCorrect() {
        String mdp = "monMotDePasse";
        String hash = PasswordUtil.hashMdp(mdp);
        assertTrue(PasswordUtil.checkMdp(mdp, hash));
    }

    @Test
    public void testCheckMdpIncorrect() {
        String hash = PasswordUtil.hashMdp("monMotDePasse");
        assertFalse(PasswordUtil.checkMdp("mauvaisMotDePasse", hash));
    }

    @Test
    public void testDeuxHashsDifferentsPourMemeMdp() {
        String hash1 = PasswordUtil.hashMdp("test");
        String hash2 = PasswordUtil.hashMdp("test");
        // BCrypt génère un sel aléatoire, donc les deux hash doivent être différents
        assertNotEquals(hash1, hash2);
        // Mais les deux doivent valider le même mot de passe
        assertTrue(PasswordUtil.checkMdp("test", hash1));
        assertTrue(PasswordUtil.checkMdp("test", hash2));
    }
}
