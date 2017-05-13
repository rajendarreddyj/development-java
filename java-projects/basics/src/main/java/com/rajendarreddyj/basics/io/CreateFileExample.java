package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * The File.createNewFile() method is used to create a file in Java, and return a boolean value : true if the file is
 * created successful; false if the file is already exists or the operation failed.
 */
public class CreateFileExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        try {
            File file = new File("newfile.txt");
            if (file.createNewFile()) {
                logger.info("File is created!");
            } else {
                logger.info("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
