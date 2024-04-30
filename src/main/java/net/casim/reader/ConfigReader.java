package net.casim.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static net.casim.utils.EncryptionUtils.decrypt;

public class ConfigReader {

    public static void main(String[] args) {
        Properties properties = new Properties();
        Scanner scanner = new Scanner(System.in);

        try {
            // Load the .ini file from the specified path
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\ekinc\\Downloads\\config_encrypted.ini");
            properties.load(fileInputStream);

            // Get encrypted values from properties
            String encryptedSleepTime = properties.getProperty("SleepTime");
            String encryptedExceptionSleepTime = properties.getProperty("ExceptionSleepTime");
            String encryptedEmailTo = properties.getProperty("EmailTo");
            String encryptedDataDB = properties.getProperty("DataDB");
            String encryptedDataPORT = properties.getProperty("DataPORT");
            String encryptedDataSID = properties.getProperty("DataSID");
            String encryptedDataDBUser = properties.getProperty("DataDBUser");
            String encryptedDataDBPass = properties.getProperty("DataDBPass");

            // Decrypt values
            String sleepTime = decrypt(encryptedSleepTime);
            String exceptionSleepTime = decrypt(encryptedExceptionSleepTime);
            String emailTo = decrypt(encryptedEmailTo);
            String dataDB = decrypt(encryptedDataDB);
            String dataPORT = decrypt(encryptedDataPORT);
            String dataSID = decrypt(encryptedDataSID);
            String dataDBUser = decrypt(encryptedDataDBUser);
            String dataDBPass = decrypt(encryptedDataDBPass);

            // Prompt user with decrypted values
            System.out.println("Sleep Time: " + sleepTime);
            System.out.println("Exception Sleep Time: " + exceptionSleepTime);
            System.out.println("Email To: " + emailTo);
            System.out.println("Data DB: " + dataDB);
            System.out.println("Data PORT: " + dataPORT);
            System.out.println("Data SID: " + dataSID);
            System.out.println("Data DB User: " + dataDBUser);
            System.out.println("Data DB Pass: " + dataDBPass);

            // Close the input stream
            fileInputStream.close();
        } catch (IOException e) {
            System.err.println("Error reading config.ini file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error decrypting config.ini file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

}
