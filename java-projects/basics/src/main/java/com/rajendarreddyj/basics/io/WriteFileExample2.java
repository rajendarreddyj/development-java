package com.rajendarreddyj.basics.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* An updated JDK7 example, using new “try resource close” method to handle file easily. */
public class WriteFileExample2 {
    public static void main(final String[] args) {

        File file = new File("newfile2.txt");
        String content = "This is the text content2";

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
