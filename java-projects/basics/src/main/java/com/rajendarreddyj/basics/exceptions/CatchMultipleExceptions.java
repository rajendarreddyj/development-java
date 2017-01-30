package com.rajendarreddyj.basics.exceptions;

public class CatchMultipleExceptions {

    public static void main(final String[] args) {

        // We demonstrate with a short password

        try {

            CatchMultipleExceptions.checkPass("pass");

        } catch (NoPassException e) {

            e.printStackTrace();

        } catch (ShortPassException e) {

            e.printStackTrace();

        } finally {

            System.out.println("Finally block is always executed");

        }

        // We demonstrate with no password

        try {

            CatchMultipleExceptions.checkPass(null);

        } catch (NoPassException e) {

            e.printStackTrace();

        } catch (ShortPassException e) {

            e.printStackTrace();

        } finally {

            System.out.println("Finally block is always executed");

        }

        // We demonstrate with valid password

        try {

            CatchMultipleExceptions.checkPass("123456");

            System.out.println("Password check : OK");

        } catch (NoPassException e) {

            e.printStackTrace();

        } catch (ShortPassException e) {

            e.printStackTrace();

        } finally {

            System.out.println("Finally block is always executed");

        }

    }

    // Our business method that check password validity and throws
    // NoPassException and ShortPassException
    public static void checkPass(final String pass) throws NoPassException, ShortPassException {

        int minPassLength = 5;

        if (pass == null) {
            throw new NoPassException("No pass provided");
        }

        if (pass.length() < minPassLength) {
            throw new ShortPassException("The password provided is too short");
        }
    }
}

// A custom business exception for no password
class NoPassException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    NoPassException() {
    }

    NoPassException(final String message) {

        super(message);
    }

    NoPassException(final String message, final Throwable cause) {

        super(message, cause);
    }
}

// A custom business exception for short password
class ShortPassException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    ShortPassException() {
    }

    ShortPassException(final String message) {

        super(message);
    }

    ShortPassException(final String message, final Throwable cause) {

        super(message, cause);
    }
}
