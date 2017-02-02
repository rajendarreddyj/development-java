package com.rajendarreddyj.basics.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * FileWritter, a character stream to write characters to file. By default, it will replace all the existing content
 * with new content, however, when you specified a true (boolean) value as the second argument in FileWritter
 * constructor, it will keep the existing content and append the new content in the end of the file. 1.Replace all
 * existing content with new content. new FileWriter(file); 2. Keep the existing content and append the new content in
 * the end of the file. new FileWriter(file,true); Append file example A text file named “javaio-appendfile.txt” and
 * contains the following content. ABC Hello Append new content with new FileWriter(file,true)
 */
public class AppendToFileExample {
    public static void main(final String[] args) {
        try {
            String data = " This content will append to the end of the file";
            File file = new File("javaio-appendfile.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data);
            bufferWritter.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
