package by.gsu.epamlab.model.modelUtils.security;

import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordCrypter {

    private static final String SALT = "!<>@#--&^~%";

    private String getMd5Hash(String hash) {
        String hashText;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(StandardCharsets.UTF_8.encode(hash));
            BigInteger bigInteger = new BigInteger(1, md.digest());
            hashText = String.format("%032x", bigInteger);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashText;
    }

    private String encodeBase64(String hash) {
        return new BASE64Encoder().encode(hash.getBytes());
    }

    public String calculateHash(String password) {
        String hash;
        hash = getMd5Hash(password);
        for(int i = 0; i < 1000; i++) {
            hash = getMd5Hash(hash);
        }
        return encodeBase64(hash);
    }

}
