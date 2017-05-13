package com.rajendarreddyj.basics.filereadwrite;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.logging.Logger;

public class TestPrintStream {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        logger.info("January");
        logger.info("February");
        PrintStream ps = null;
        try {
            ps = new PrintStream("sample.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
        System.setOut(ps);
        logger.info("March");
        logger.info("April");
        ps.close();
    }
}
