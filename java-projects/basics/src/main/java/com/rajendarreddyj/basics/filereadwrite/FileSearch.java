package com.rajendarreddyj.basics.filereadwrite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {
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
            System.out.println("\nNo result found!");
        } else {
            System.out.println("\nFound " + count + " result!\n");
            for (String matched : fileSearch.getResult()) {
                System.out.println("Found : " + matched);
            }
        }
    }

    public void searchDirectory(final File directory, final String fileNameToSearch) {
        this.setFileNameToSearch(fileNameToSearch);
        if (directory.isDirectory()) {
            this.search(directory);
        } else {
            System.out.println(directory.getAbsoluteFile() + " is not a directory!");
        }
    }

    private void search(final File file) {
        if (file.isDirectory()) {
            System.out.println("Searching directory ... " + file.getAbsoluteFile());
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
                System.out.println(file.getAbsoluteFile() + "Permission Denied");
            }
        }
    }
}
