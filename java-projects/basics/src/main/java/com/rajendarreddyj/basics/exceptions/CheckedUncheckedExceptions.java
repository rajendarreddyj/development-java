package com.rajendarreddyj.basics.exceptions;

import java.io.File;
import java.io.IOException;

public class CheckedUncheckedExceptions {
    public static void main(final String[] args) {
        // We must catch the checked exception - to test use an existing file!
        try {
            CheckedUncheckedExceptions.checkSize("testFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // The unchecked exception doesn't requires you to catch it
        CheckedUncheckedExceptions.divide(1, 0);
    }

    /**
     * This method throws a Checked Exception, so it must declare the Exception in its method declaration
     * 
     * @param fileName
     *            given file name
     * @throws IOException
     *             when the file size is to large.
     */
    public static void checkSize(final String fileName) throws IOException {
        File file = new File(fileName);
        if (file.length() > Integer.MAX_VALUE) {
            throw new IOException("File size is too large!");
        }
    }

    /**
     * This method throws a RuntimeException. There is no need to declare the Exception in the method declaration
     * 
     * @param x
     *            the dividend
     * @param y
     *            the divisor
     * 
     * @return the division result
     * @throws ArithmeticException
     *             when arithmetic exception occurs (divided by zero)
     */
    public static int divide(final int x, final int y) {
        return x / y;
    }
}
