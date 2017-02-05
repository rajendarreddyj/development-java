package com.rajendarreddyj.basics.exceptions;

public class CustomExceptionExample {
    public static void main(final String[] args) {
        // We demonstrate with a short password
        try {
            CustomExceptionExample.checkPass("pass");
        } catch (InvalidPassException e) {
            e.printStackTrace();
        }
        // We demonstrate with no password
        try {
            CustomExceptionExample.checkPass(null);
        } catch (InvalidPassException e) {
            e.printStackTrace();
        }
    }

    // Our business method that check password validity and throws
    // InvalidPassException
    public static void checkPass(final String pass) throws InvalidPassException {
        int minPassLength = 5;
        try {
            if (pass.length() < minPassLength) {
                throw new InvalidPassException("The password provided is too short");
            }
        } catch (NullPointerException e) {
            throw new InvalidPassException("No password provided", e);
        }
    }
}

// A custom business exception
class InvalidPassException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    InvalidPassException() {
    }

    InvalidPassException(final String message) {
        super(message);
    }

    InvalidPassException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
