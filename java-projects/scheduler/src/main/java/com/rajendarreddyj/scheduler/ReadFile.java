package com.rajendarreddyj.scheduler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author rajendarreddy
 */
public class ReadFile {

    private static final String PROP_FILE_NAME = "config.properties";
    private static final String PROP_KEY_FILE_PATH = "filePath";
    private static final String PROP_KEY_FILE_EXTENSION = "fileExtension";
    private static final String PROP_KEY_PROC_FILE_EXTENSION = "processsedFileExtension";
    private static final String PROP_KEY_SCHED_DELAY = "schedulerDelay";
    private static final String PROP_KEY_SCHED_PERIOD = "schedulerPeriod";
    private static final String PROP_KEY_WEB_SRVC_URL = "webServiceUrl";

    public static void main(final String[] args) {
        Properties prop = readProperties(PROP_FILE_NAME);
        TimerTask task = new TaskScheduler(prop.getProperty(PROP_KEY_FILE_PATH), prop.getProperty(PROP_KEY_FILE_EXTENSION),
                prop.getProperty(PROP_KEY_PROC_FILE_EXTENSION), prop.getProperty(PROP_KEY_WEB_SRVC_URL));
        Timer timer = new Timer();
        timer.schedule(task, Integer.parseInt(prop.getProperty(PROP_KEY_SCHED_DELAY, "1000")),
                Integer.parseInt(prop.getProperty(PROP_KEY_SCHED_PERIOD, "3000000")));

    }

    /**
     * @param propName
     * @return
     */
    private static Properties readProperties(final String propName) {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(propName);
        try {
            prop.load(stream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prop;
    }

}
