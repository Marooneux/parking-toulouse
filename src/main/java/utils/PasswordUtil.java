package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hashMdp(String mdp) {
        return BCrypt.hashpw(mdp, BCrypt.gensalt());
    }

    public static boolean checkMdp(String mdp, String hashMdp) {
        return BCrypt.checkpw(mdp, hashMdp);
    }
}
