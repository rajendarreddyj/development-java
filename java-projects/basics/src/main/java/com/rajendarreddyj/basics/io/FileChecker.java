package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Logger;

/*
 * In Java, you can implements the FilenameFilter, override the accept(File dir, String name) method, to perform the
 * file filtering function. In this example, we show you how to use FilenameFilter to list out all files that are end
 * with “.txt” extension in folder “/apps“, and then delete it.
 */
public class FileChecker {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String FILE_DIR = ".";
    private static final String FILE_TEXT_EXT = ".txt";

    public static void main(final String args[]) {
        new FileChecker().deleteFile(FILE_DIR, FILE_TEXT_EXT);
    }

    public void deleteFile(final String folder, final String ext) {
        GenericExtFilter filter = new GenericExtFilter(ext);
        File dir = new File(folder);
        // list out all the file name with .txt extension
        String[] list = dir.list(filter);
        if (list.length == 0) {
            return;
        }
        File fileDelete;
        for (String file : list) {
            String temp = new StringBuffer(FILE_DIR).append(File.separator).append(file).toString();
            fileDelete = new File(temp);
            boolean isdeleted = fileDelete.delete();
            logger.info("file : " + temp + " is deleted : " + isdeleted);
        }
    }

    // inner class, generic extension filter
    public class GenericExtFilter implements FilenameFilter {
        private String ext;

        public GenericExtFilter(final String ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(final File dir, final String name) {
            return (name.endsWith(this.ext));
        }
    }
}
