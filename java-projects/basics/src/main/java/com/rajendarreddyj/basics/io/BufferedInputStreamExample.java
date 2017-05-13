package com.rajendarreddyj.basics.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * To show how to read a file in Java with BufferedInputStream and DataInputStream classes. The readLine() from the type
 * DataInputStream is deprecated. Sun officially announced this method can not convert property from bytes to
 * characters. It’s advised to use BufferedReader.
 */
public class BufferedInputStreamExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    @SuppressWarnings("deprecation")
    public static void main(final String[] args) {
        File file = new File("javaio-appendfile.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            while (dis.available() != 0) {
                logger.info(dis.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                bis.close();
                dis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}