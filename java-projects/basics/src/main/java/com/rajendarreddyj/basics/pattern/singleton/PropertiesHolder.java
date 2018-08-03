package com.rajendarreddyj.basics.pattern.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesHolder implements Cloneable, Serializable {
    private static final long serialVersionUID = 1007236647121448027L;
    private static final String CONNECTION_FILE_WITH_PATH = "src/main/resources/dbconnection.properties";
    private static PropertiesHolder PROPERTIES_HOLDER = null;
    private final Properties properties = new Properties();

    private PropertiesHolder() {
    }

    public static PropertiesHolder getInstance() throws IOException {
        if (PROPERTIES_HOLDER == null) {
            synchronized (PropertiesHolder.class) {
                if (PROPERTIES_HOLDER == null) {
                    InputStream file = new FileInputStream(new File(CONNECTION_FILE_WITH_PATH));
                    PROPERTIES_HOLDER = new PropertiesHolder();
                    PROPERTIES_HOLDER.properties.load(file);
                }
            }
        }
        return PROPERTIES_HOLDER;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return PROPERTIES_HOLDER;
    }

    protected Object readResolve() {
        return PROPERTIES_HOLDER;
    }

    public String getPropertyValue(final String propertyKey) {
        if ((propertyKey != null) && !propertyKey.isEmpty()) {
            return this.properties.getProperty(propertyKey);
        }
        return null;
    }

    public Map<String, String> getProperties() {
        Map<String, String> propertyMap = new HashMap<>();
        for (String propertyKey : this.properties.stringPropertyNames()) {
            propertyMap.put(propertyKey, this.properties.getProperty(propertyKey));
        }
        return propertyMap;
    }
}
