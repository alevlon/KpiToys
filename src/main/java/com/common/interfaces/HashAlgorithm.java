package com.common.interfaces;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;


public interface HashAlgorithm {
    default String getHashCode(String s){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        char[] result = Hex.encodeHex(hash);
        return new String(result);
    }
}
