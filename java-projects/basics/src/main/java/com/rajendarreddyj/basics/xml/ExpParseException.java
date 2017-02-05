package com.rajendarreddyj.basics.xml;

/**
 * ExpParseException
 *
 * A custom exception class that is thrown when errors are encountered when processing an expression (e.g., a + b * c)
 * into XML.
 *
 * 
 */
public class ExpParseException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public ExpParseException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ExpParseException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
