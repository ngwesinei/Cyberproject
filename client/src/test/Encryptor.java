/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Ei
 */

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;



public class Encryptor {
    
    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
           
            return Base64.getEncoder().encodeToString(encrypted);
//            System.out.println("encrypted string: "
//                    + Base64.encodeBase64String(encrypted));
//
//            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

           // byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
//       String key = "Bar12345Bar12345"; // 128 bit key
//       String initVector = "RandomInitVector"; // 16 bytes IV

        
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] keyByte = new byte[16];
//        secureRandom.nextBytes(keyByte);
//        String key=keyByte.toString();
        
        String key="eingwesin0000000";
        System.out.println("This is key="+key);
        
        String initVector="eingwesin0000000";
        String initVector2="RandomInitVector";
        
        System.out.println(encrypt(key,initVector,"top This is top Secrete"));
        
        System.out.println(decrypt(key, initVector,
                encrypt(key, initVector, "This is top Secrete")));
    }
}