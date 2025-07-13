package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public String getPropertiesParameter(String environment, String key) throws IOException {
        Properties properties = new Properties();
        String path = "src/test/java/utils/environmentProperties/" + environment.toLowerCase() + ".properties";
        FileInputStream inputStream = new FileInputStream(path);
        properties.load(inputStream);
        return properties.getProperty(key);
    }
}
