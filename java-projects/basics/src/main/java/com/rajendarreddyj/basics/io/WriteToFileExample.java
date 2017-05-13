package com.rajendarreddyj.basics.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * In Java, BufferedWriter is a character streams class to handle the character data. Unlike bytes stream (convert data
 * into bytes), you can just write the strings, arrays or characters data directly to file.
 */
public class WriteToFileExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        try {
            String content = "This is the content to write into file";
            File file = new File("newfile3.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getName());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            logger.info("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
