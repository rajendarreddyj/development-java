package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/*
 * Java didn’t comes with any ready make file copy function, you have to manual create the file copy process. To copy
 * file, just convert the file into a bytes stream with FileInputStream and write the bytes into another file with
 * FileOutputStream. The overall processes are quite simple, just do not understand why Java doesn’t include this method
 * into the java.io.File class.
 */
public class CopyFileExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File afile = new File("AFile.txt");
            File bfile = new File("BFile.txt");
            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);
            byte[] buffer = new byte[1024];
            int length;
            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            logger.info("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
