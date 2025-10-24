package O3_utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String FILE_PATH = PROJECT_PATH + "/src/test/resources/testData/config.properties";

    // Constructor - matches class name, no return type
    public PropertyReader() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            properties.load(fis);
            fis.close(); // Always close the stream
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + FILE_PATH, e);
        }
    }

    public String getKey(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property key '" + key + "' not found in config file");
        }
        return value;
    }
}