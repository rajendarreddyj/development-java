package com.rajendarreddyj.basics.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * In Java, there are many ways to read a file, here we show you how to use the simplest and most common-used method –
 * BufferedReader.
 */
public class BufferedReaderExample1 {
    public static void main(final String[] args) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("javaio-appendfile.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}