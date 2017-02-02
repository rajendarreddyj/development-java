package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.IOException;

/*
 * The File.createNewFile() method is used to create a file in Java, and return a boolean value : true if the file is
 * created successful; false if the file is already exists or the operation failed.
 */

public class CreateFileExample {
    public static void main(final String[] args) {
        try {
            File file = new File("newfile.txt");
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
