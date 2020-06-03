package mate.academy.internetshop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static String SHA_256 = "SHA-256";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
