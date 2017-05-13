package com.rajendarreddyj.basics.filereadwrite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileSearch {
    private static final Logger logger = Logger.getAnonymousLogger();
    private String fileNameToSearch;
    private List<String> result = new ArrayList<>();

    public String getFileNameToSearch() {
        return this.fileNameToSearch;
    }

    public void setFileNameToSearch(final String fileNameToSearch) {
        this.fileNameToSearch = fileNameToSearch;
    }

    public List<String> getResult() {
        return this.result;
    }

    public static void main(final String[] args) {
        FileSearch fileSearch = new FileSearch();
        // try different directory and filename :)
        fileSearch.searchDirectory(new File("."), "sample.txt");
        int count = fileSearch.getResult().size();
        if (count == 0) {
            logger.info("\nNo result found!");
        } else {
            logger.info("\nFound " + count + " result!\n");
            for (String matched : fileSearch.getResult()) {
                logger.info("Found : " + matched);
            }
        }
    }

    public void searchDirectory(final File directory, final String fileNameToSearch) {
        this.setFileNameToSearch(fileNameToSearch);
        if (directory.isDirectory()) {
            this.search(directory);
        } else {
            logger.info(directory.getAbsoluteFile() + " is not a directory!");
        }
    }

    private void search(final File file) {
        if (file.isDirectory()) {
            logger.info("Searching directory ... " + file.getAbsoluteFile());
            // do you have permission to read this directory?
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        this.search(temp);
                    } else {
                        if (this.getFileNameToSearch().equals(temp.getName().toLowerCase())) {
                            this.result.add(temp.getAbsoluteFile().toString());
                        }
                    }
                }
            } else {
                logger.info(file.getAbsoluteFile() + "Permission Denied");
            }
        }
    }
}
