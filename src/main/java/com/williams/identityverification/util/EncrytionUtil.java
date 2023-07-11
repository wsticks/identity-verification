package com.williams.identityverification.util;

import com.williams.identityverification.exception.ProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 *
 * @author Muyiwa
 */
@Slf4j
public class EncrytionUtil {



  public static String hashWithSha256(String rawKey) throws ProcessingException {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] encrypted = md.digest(rawKey.getBytes());
      return new String(Hex.encodeHex(encrypted));
    } catch (NoSuchAlgorithmException ex) {
      String errorMessage = "Unable to hash this string";
      throw new ProcessingException(errorMessage);
    }
  }

  private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder();
    for (int i = 0; i < hash.length; i++) {
      String hex = Integer.toHexString(0xff & hash[i]);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  public static String toHexString(byte[] hash) {
    // Convert byte array into signum representation
    BigInteger number = new BigInteger(1, hash);
    // Convert message digest into hex value
    StringBuilder hexString = new StringBuilder(number.toString(16));
    // Pad with leading zeros
    while (hexString.length() < 32) {
      hexString.insert(0, '0');
    }
    return hexString.toString();
  }

  public static String sha256(String input) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] result = md.digest(input.getBytes(StandardCharsets.UTF_8));
    return toHexString(result);
  }

  public static String maskCardNumber(String cardNumber, String mask) {
    int index = 0;
    StringBuilder maskedNumber = new StringBuilder();
    for (int i = 0; i < mask.length(); i++) {
      char c = mask.charAt(i);
      switch (c) {
        case '#':
          maskedNumber.append(cardNumber.charAt(index));
          index++;
          break;
        case 'x':
          maskedNumber.append(c);
          index++;
          break;
        default:
          maskedNumber.append(c);
          break;
      }
    }
    return maskedNumber.toString();
  }

  public static String toHexStr(byte[] bytes) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      builder.append(String.format("%02x", bytes[i]));
    }
    return builder.toString();
  }

  public static String getKey(String seedKey) {
    try {
      MessageDigest md = MessageDigest.getInstance("md5");
      byte[] hashedString = md.digest(seedKey.getBytes("utf-8"));
      byte[] subHashString = toHexStr(Arrays.copyOfRange(
              hashedString, hashedString.length - 12, hashedString.length)).getBytes("utf-8");
      String subSeedKey;
      if(seedKey.startsWith("SBTESTSECK_")){
        subSeedKey = seedKey.replace("SBTESTSECK_", "");
      } else {
        subSeedKey = seedKey.replace("SBSECK_", "");
      }
      subSeedKey = subSeedKey.substring(0, 12);
      byte[] combineArray = new byte[24];
      System.arraycopy(subSeedKey.getBytes(), 0, combineArray, 0, 12);
      System.arraycopy(subHashString, subHashString.length - 12, combineArray, 12, 12);
      return new String(combineArray);
    } catch (NoSuchAlgorithmException ex) {
      log.error("encyrption error  {}" ,ex);
    } catch (UnsupportedEncodingException ex) {
      log.error("encyrption error  {}" ,ex);
    }
    return null;
  }

  public static String encryptData(String message, String _encryptionKey) {
    System.out.println("KEY TO ENCRYPT " + _encryptionKey);
    try {
      final byte[] digestOfPassword = _encryptionKey.getBytes("utf-8");
      final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
      final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, key);
      final byte[] plainTextBytes = message.getBytes("utf-8");
      final byte[] cipherText = cipher.doFinal(plainTextBytes);
      return Base64.getEncoder().encodeToString(cipherText);
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }

  public static String decryptData(String message, String _decrptionKey) {
    try {
      final byte[] digestOfPassword = _decrptionKey.getBytes("utf-8");
      final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
      final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, key);
      final byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(message));
      return new String(plainText, "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
}
