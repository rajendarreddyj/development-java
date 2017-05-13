package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * In Java, FileOutputStream is a bytes stream class that’s used to handle raw binary data. To write the data to file,
 * you have to convert the data into bytes and save it to file.
 */
public class WriteFileExample1 {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        FileOutputStream fop = null;
        File file;
        String content = "This is the text content1";
        try {
            file = new File("newfile1.txt");
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            logger.info("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
