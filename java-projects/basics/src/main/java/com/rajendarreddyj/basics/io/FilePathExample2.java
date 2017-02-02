package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.IOException;

/*
 * Here’s how to construct a file path : Check the operating system and create the file separator manually. (Not
 * recommend) Let’s Java do all the job ~ File.separator. (Best Practice) File.separator is always recommended, because
 * it will check your OS and display the correct file separator automatically, For example, Windows – Return “\” *nix –
 * Return “/” A proper way is use the File.separator.
 */
public class FilePathExample2 {
    public static void main(final String[] args) {
        try {
            String filename = "testing.txt";
            String finalfile = "";
            String workingDir = System.getProperty("user.dir");
            finalfile = workingDir + File.separator + filename;
            System.out.println("Final filepath : " + finalfile);
            File file = new File(finalfile);
            if (file.createNewFile()) {
                System.out.println("Done");
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
