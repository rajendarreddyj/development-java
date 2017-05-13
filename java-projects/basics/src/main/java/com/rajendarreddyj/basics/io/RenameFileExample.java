package com.rajendarreddyj.basics.io;

import java.io.File;
import java.util.logging.Logger;

/*
 * Java comes with renameTo() method to rename a file. However , this method is really platform-dependent: you may
 * successfully rename a file in *nix but failed in Windows. So, the return value (true if the file rename successful,
 * false if failed) should always be checked to make sure the file is rename successful.
 */
public class RenameFileExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        File oldfile = new File("oldfile.txt");
        File newfile = new File("newfile.txt");
        if (oldfile.renameTo(newfile)) {
            logger.info("Rename succesful");
        } else {
            logger.info("Rename failed");
        }
    }
}
