package utils;

import java.io.IOException;

public class Environment {

    String environment = System.getProperty("environment");
    String runEnvironment;

    public String specifiedEnvironment() throws IOException {
        if (environment != null) {
            Configuration config = new Configuration();
            switch (environment) {
                case "Uat":
                    runEnvironment = config.getPropertiesParameter("uat", "UatURL");
                    break;
                case "test":
                    runEnvironment = config.getPropertiesParameter("test", "PreprodURL");
                    break;
                case "Sit":
                    runEnvironment = config.getPropertiesParameter("sit", "SitUrl");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }
        }
        return runEnvironment;
    }
}
