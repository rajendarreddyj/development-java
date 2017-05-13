package com.rajendarreddyj.basics.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

/* Example in JDK 7, which use try-with-resources new feature to close file automatically. */
public class BufferedReaderExample2 {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("javaio-appendfile.txt"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                logger.info(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
