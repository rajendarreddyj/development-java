package com.rajendarreddyj.basics.apache.common.io;

import java.io.File;
import java.util.logging.Logger;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * @author rajendarreddy
 *
 */
public class DirectoryMonitoringMain {
    public static final int POLL_INTERVAL = 500;
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) throws Exception {
        FileAlterationObserver observer = new FileAlterationObserver(System.getProperty("user.home") + "/Downloads");
        FileAlterationMonitor monitor = new FileAlterationMonitor(POLL_INTERVAL);
        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            @Override
            public void onStart(final FileAlterationObserver observer) {
                logger.info("Directory Monitoring Started on " + observer.getDirectory());
            }

            @Override
            public void onFileCreate(final File file) {
                logger.info("File: " + file.getName() + " created");
            }

            @Override
            public void onFileDelete(final File file) {
                logger.info("File: " + file.getName() + " deleted");
            }

            @Override
            public void onFileChange(final File file) {
                logger.info("File: " + file.getName() + " changed");
            }
        };
        observer.addListener(listener);
        monitor.addObserver(observer);
        monitor.start();
    }
}
