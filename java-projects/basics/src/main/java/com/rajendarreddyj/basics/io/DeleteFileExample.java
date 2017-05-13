package com.rajendarreddyj.basics.io;

import java.io.File;
import java.util.logging.Logger;

/*
 * No nonsense, just issue the File.delete() to delete a file, it will return a boolean value to indicate the delete
 * operation status; true if the file is deleted; false if failed.
 */
public class DeleteFileExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        try {
            File file = new File("newfile.txt");
            if (file.delete()) {
                logger.info(file.getName() + " is deleted!");
            } else {
                logger.info("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
