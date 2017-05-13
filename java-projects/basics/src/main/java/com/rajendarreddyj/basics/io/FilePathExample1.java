package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * Here’s how to construct a file path : Check the operating system and create the file separator manually. (Not
 * recommend) Let’s Java do all the job ~ File.separator. (Best Practice) File.separator is always recommended, because
 * it will check your OS and display the correct file separator automatically, For example, Windows – Return “\” *nix –
 * Return “/” A classic way to construct a file path manually, which is not recommended.
 */
public class FilePathExample1 {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        try {
            String filename = "testing.txt";
            String finalfile = "";
            String workingDir = System.getProperty("user.dir");
            String your_os = System.getProperty("os.name").toLowerCase();
            if (your_os.indexOf("win") >= 0) {
                finalfile = workingDir + "\\" + filename;
            } else if ((your_os.indexOf("nix") >= 0) || (your_os.indexOf("nux") >= 0)) {
                finalfile = workingDir + "/" + filename;
            } else {
                finalfile = workingDir + "{others}" + filename;
            }
            logger.info("Final filepath : " + finalfile);
            File file = new File(finalfile);
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
