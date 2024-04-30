package net.casim.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

import static net.casim.utils.EncryptionUtils.AES_ALGORITHM;
import static net.casim.utils.EncryptionUtils.SECRET_KEY;

public class EncryptIniFile {

    public static void main(String[] args) {
        // Load the ini file
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\ekinc\\Downloads\\config.ini")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Error reading config.ini file: " + e.getMessage());
            return;
        }

        // Encrypt each property value
        for (String key : properties.stringPropertyNames()) {
            String encryptedValue = encrypt(properties.getProperty(key));
            properties.setProperty(key, encryptedValue);
        }

        // Save the encrypted values back to the ini file
        try (FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\ekinc\\Downloads\\config_encrypted.ini")) {
            properties.store(fileOutputStream, null);
        } catch (IOException e) {
            System.err.println("Error writing encrypted config.ini file: " + e.getMessage());
        }
    }

    private static String encrypt(String valueToEncrypt) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(valueToEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.err.println("Error encrypting value: " + e.getMessage());
            return null;
        }
    }
}


