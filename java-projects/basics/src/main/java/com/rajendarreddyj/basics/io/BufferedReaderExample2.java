package com.rajendarreddyj.basics.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* Example in JDK 7, which use try-with-resources new feature to close file automatically. */
public class BufferedReaderExample2 {

    public static void main(final String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("javaio-appendfile.txt"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
