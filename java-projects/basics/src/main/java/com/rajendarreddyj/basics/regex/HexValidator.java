package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Hexadecimal Color Code Regular Expression Pattern ^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$ Description ^ #start of the line
 * # # must constains a "#" symbols ( # start of group #1 [A-Fa-f0-9]{6} # any strings in the list, with length of 6 | #
 * ..or [A-Fa-f0-9]{3} # any strings in the list, with length of 3 ) # end of group #1 $ #end of the line Whole
 * combination is means, string must start with a “#” symbol , follow by a letter from “a” to “f”, “A” to “Z” or a digit
 * from “0″ to 9″ with exactly 6 or 3 length. This regular expression pattern is very useful for the Hexadecimal web
 * colors code checking.
 */
public class HexValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

    public HexValidator() {
        this.pattern = Pattern.compile(HEX_PATTERN);
    }

    /**
     * Validate hex with regular expression
     * 
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {
        this.matcher = this.pattern.matcher(hex);
        return this.matcher.matches();
    }
}