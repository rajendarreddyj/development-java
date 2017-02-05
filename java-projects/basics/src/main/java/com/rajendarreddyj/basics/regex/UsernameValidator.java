package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Username Regular Expression Pattern ^[a-z0-9_-]{3,15}$ Description
 * 
 * ^ # Start of the line [a-z0-9_-] # Match characters and symbols in the list, a-z, 0-9, underscore, hyphen {3,15} #
 * Length at least 3 characters and maximum length of 15 $ # End of the line Whole combination is means, 3 to 15
 * characters with any lower case character, digit or special symbol “_-” only. This is common username pattern that’s
 * widely use in different websites.
 */
public class UsernameValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_ !`@#$%^&*()+=:;\"'{}<>,./?|~\\/-]{3,15}$";

    public UsernameValidator() {
        this.pattern = Pattern.compile(USERNAME_PATTERN);
    }

    /**
     * Validate username with regular expression
     * 
     * @param username
     *            username for validation
     * @return true valid username, false invalid username
     */
    public boolean validate(final String username) {
        this.matcher = this.pattern.matcher(username);
        return this.matcher.matches();
    }
}
