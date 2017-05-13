package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * Here’s how to construct a file path : Check the operating system and create the file separator manually. (Not
 * recommend) Let’s Java do all the job ~ File.separator. (Best Practice) File.separator is always recommended, because
 * it will check your OS and display the correct file separator automatically, For example, Windows – Return “\” *nix –
 * Return “/” This can be even more simple :) String filename = “testing.txt”; String workingDir =
 * System.getProperty(“user.dir”); File file = new File(workingDir, filename);.
 */
public class FilePathExample3 {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        try {
            String filename = "testing.txt";
            String finalfile = "";
            String workingDir = System.getProperty("user.dir");
            finalfile = workingDir + File.separator + filename;
            logger.info("Final filepath : " + finalfile);
            File file = new File(workingDir, filename);
            if (file.createNewFile()) {
                logger.info("Done");
            } else {
                logger.info("File already exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
