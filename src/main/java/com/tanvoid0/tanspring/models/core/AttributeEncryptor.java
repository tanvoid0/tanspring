package com.tanvoid0.tanspring.models.core;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AttributeEncryptor {

  private static final String AES = "AES";
  private static final String SHA1 = "SHA-1";
  private static final String SECRET = "secret-key-12345";
  private static final String cipherString = "AES/ECB/PKCS5PADDING";
  private static SecretKeySpec secretKey;
  private static byte[] key;

  public static void setKey(final String myKey) {
    MessageDigest sha = null;
    try {
      key = myKey.getBytes(StandardCharsets.UTF_8);
      sha = MessageDigest.getInstance(SHA1);
      key = sha.digest(key);
      key = Arrays.copyOf(key, 16);
      secretKey = new SecretKeySpec(key, AES);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public static String encrypt(final String strToEncrypt) {
    try {
      setKey(SECRET);
      Cipher cipher = Cipher.getInstance(cipherString);
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      return Base64.getEncoder()
          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    } catch (final Exception ex) {
      System.out.println("Error while encrypting: " + ex);
    }
    return null;
  }

  public static String decrypt(final String strToDecrypt) {
    try {
      setKey(SECRET);
      Cipher cipher = Cipher.getInstance(cipherString);
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (final Exception ex) {
      System.out.println("Error while decrypting: " + ex);
    }
    return null;
  }

}
