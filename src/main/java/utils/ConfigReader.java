package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static final String SECRETS_FILE_PATH = "src/main/resources/secret.properties";
    // Private constructor to prevent instantiation
    private ConfigReader() {}

    // Load properties only once
    static {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE_PATH, e);
        }
        
        try (FileInputStream fileInputStream = new FileInputStream(SECRETS_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + SECRETS_FILE_PATH, e);
        }
    }

    /**
     * Fetches the property value from config.properties
     *
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Property \"" + key + "\" not found in config.properties");
        }
        return value.trim();
    }

    /**
     * Fetches the property value and converts it to an integer
     *
     * @param key Property key
     * @return Integer property value
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    /**
     * Fetches the property value and converts it to a boolean
     *
     * @param key Property key
     * @return Boolean property value
     */
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
