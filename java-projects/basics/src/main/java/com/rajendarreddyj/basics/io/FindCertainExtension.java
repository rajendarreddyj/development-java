package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Logger;

/*
 * A FilenameFilter example, it will only display files that use “.txt” extension in folder “.“.
 */
public class FindCertainExtension {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String FILE_DIR = ".";
    private static final String FILE_TEXT_EXT = ".txt";

    public static void main(final String args[]) {
        new FindCertainExtension().listFile(FILE_DIR, FILE_TEXT_EXT);
    }

    public void listFile(final String folder, final String ext) {
        GenericExtFilter filter = new GenericExtFilter(ext);
        File dir = new File(folder);
        if (dir.isDirectory() == false) {
            logger.info("Directory does not exists : " + FILE_DIR);
            return;
        }
        // list out all the file name and filter by the extension
        String[] list = dir.list(filter);
        if (list.length == 0) {
            logger.info("no files end with : " + ext);
            return;
        }
        for (String file : list) {
            String temp = new StringBuffer(FILE_DIR).append(File.separator).append(file).toString();
            logger.info("file : " + temp);
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