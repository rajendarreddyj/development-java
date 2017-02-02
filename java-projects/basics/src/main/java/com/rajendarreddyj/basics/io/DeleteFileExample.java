package com.rajendarreddyj.basics.io;

import java.io.File;

/*
 * No nonsense, just issue the File.delete() to delete a file, it will return a boolean value to indicate the delete
 * operation status; true if the file is deleted; false if failed.
 */
public class DeleteFileExample {
    public static void main(final String[] args) {
        try {
            File file = new File("newfile.txt");
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
