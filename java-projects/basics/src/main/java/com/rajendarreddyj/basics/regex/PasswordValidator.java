package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Password Regular Expression Pattern ((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20}) Description ( # Start of
 * group (?=.*\d) # must contains one digit from 0-9 (?=.*[a-z]) # must contains one lowercase characters (?=.*[A-Z]) #
 * must contains one uppercase characters (?=.*[@#$%]) # must contains one special symbols in the list "@#$%" . # match
 * anything with previous condition checking {6,20} # length at least 6 characters and maximum of 20 ) # End of group ?=
 * – means apply the assertion condition, meaningless by itself, always work with other combination Whole combination is
 * means, 6 to 20 characters string with at least one digit, one upper case letter, one lower case letter and one
 * special symbol (“@#$%”). This regular expression pattern is very useful to implement a strong and complex password.
 */
public class PasswordValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    public PasswordValidator() {
        this.pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    /**
     * Validate password with regular expression
     * 
     * @param password
     *            password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(final String password) {
        this.matcher = this.pattern.matcher(password);
        return this.matcher.matches();
    }
}
