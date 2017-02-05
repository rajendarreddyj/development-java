package com.rajendarreddyj.basics.apache.common.io;

import java.io.File;

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

    public static void main(final String[] args) throws Exception {
        FileAlterationObserver observer = new FileAlterationObserver(System.getProperty("user.home") + "/Downloads");
        FileAlterationMonitor monitor = new FileAlterationMonitor(POLL_INTERVAL);
        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            @Override
            public void onStart(final FileAlterationObserver observer) {
                System.out.println("Directory Monitoring Started on " + observer.getDirectory());
            }

            @Override
            public void onFileCreate(final File file) {
                System.out.println("File: " + file.getName() + " created");
            }

            @Override
            public void onFileDelete(final File file) {
                System.out.println("File: " + file.getName() + " deleted");
            }

            @Override
            public void onFileChange(final File file) {
                System.out.println("File: " + file.getName() + " changed");
            }
        };
        observer.addListener(listener);
        monitor.addObserver(observer);
        monitor.start();
    }
}
