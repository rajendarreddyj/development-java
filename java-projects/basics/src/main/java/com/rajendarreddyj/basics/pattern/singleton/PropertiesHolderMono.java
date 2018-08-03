package com.rajendarreddyj.basics.pattern.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author rajendarreddy.jagapathi
 */
public class PropertiesHolderMono {
    private static final String CONNECTION_FILE_WITH_PATH = "src/main/resources/dbconnection.properties";
    private static Properties properties = new Properties();
    static {
        InputStream file;
        try {
            file = new FileInputStream(new File(CONNECTION_FILE_WITH_PATH));
            properties.load(file);
        } catch (FileNotFoundException exp) {
            exp.printStackTrace();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public static String getPropertyValue(final String propertyKey) {
        if ((propertyKey != null) && !propertyKey.isEmpty()) {
            return properties.getProperty(propertyKey);
        }
        return null;
    }

    public static Map<String, String> getProperties() {
        Map<String, String> propertyMap = new HashMap<>();
        for (String propertyKey : properties.stringPropertyNames()) {
            propertyMap.put(propertyKey, properties.getProperty(propertyKey));
        }
        return propertyMap;
    }

}
