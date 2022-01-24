package fai.imp.farmaclick.config;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final String PROJECT_PROPERTIES_FILE_NAME = "project.properties";

    static Logger logger = Logger.getLogger(PropertiesLoader.class);

    public static Properties getApplicationProperties() {
        Properties projectProperties = new Properties();
        try {
            InputStream is = PropertiesLoader.class
                    .getClassLoader()
                    .getResourceAsStream(PROJECT_PROPERTIES_FILE_NAME);
            projectProperties.load(is);
        } catch (IOException e) {
            logger.error("Errore nel caricamento delle propriet\u00E0 dell'applicazione");
        }
        return projectProperties;
    }
}
