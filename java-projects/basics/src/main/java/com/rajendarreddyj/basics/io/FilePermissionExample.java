package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * In Java, file permissions are very OS specific: *nix , NTFS (windows) and FAT/FAT32, all have different kind of file
 * permissions. Java comes with some generic file permission to deal with it. Check if the file permission allow :
 * file.canExecute(); – return true, file is executable; false is not. file.canWrite(); – return true, file is writable;
 * false is not. file.canRead(); – return true, file is readable; false is not. Set the file permission :
 * file.setExecutable(boolean); – true, allow execute operations; false to disallow it. file.setReadable(boolean); –
 * true, allow read operations; false to disallow it. file.setWritable(boolean); – true, allow write operations; false
 * to disallow it. In *nix system, you may need to configure more specifies about file permission, e.g set a 777
 * permission for a file or directory, however, Java IO classes do not have ready method for it, but you can use the
 * following dirty workaround : Runtime.getRuntime().exec("chmod 777 file");
 */
public class FilePermissionExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        try {
            File file = new File("shellscript.sh");
            if (file.exists()) {
                logger.info("Is Execute allow : " + file.canExecute());
                logger.info("Is Write allow : " + file.canWrite());
                logger.info("Is Read allow : " + file.canRead());
            }
            file.setExecutable(false);
            file.setReadable(false);
            file.setWritable(false);
            logger.info("Is Execute allow : " + file.canExecute());
            logger.info("Is Write allow : " + file.canWrite());
            logger.info("Is Read allow : " + file.canRead());
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
