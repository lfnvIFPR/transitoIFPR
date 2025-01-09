package projetosIFPR.transitoIFPR.utilidade;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

public class Hash {

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomString(int tamanho) {
        StringBuilder saida = new StringBuilder();
        Random rand = new Random();

        while (saida.length() < tamanho) {
            int index = (int) (rand.nextFloat() * CHARS.length());
            saida.append(CHARS.charAt(index));
        }
        String saidaStr = saida.toString();
        return saidaStr;
    }

    public static String hashString(String str) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean hashCorrespondente(String str, String hash) {
        return hash.equals(hashString(str));
    }
}
