package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Time in 12-Hour Format Regular Expression Pattern
 * 
 * (1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)
 * 
 * Description
 * 
 * ( #start of group #1 1[012] # start with 10, 11, 12 | # or [1-9] # start with 1,2,...9 ) #end of group #1 : # follow
 * by a semi colon (:) [0-5][0-9] # follow by 0..5 and 0..9, which means 00 to 59 (\\s)? # follow by a white space
 * (optional) (?i) # next checking is case insensitive (am|pm) # follow by am or pm
 * 
 * The 12-hour clock format is start from 0-12, then a semi colon (:) and follow by 00-59 , and end with am or pm.
 */
public class Time12HoursValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String TIME12HOURS_PATTERN = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";

    public Time12HoursValidator() {
        this.pattern = Pattern.compile(TIME12HOURS_PATTERN);
    }

    /**
     * Validate time in 12 hours format with regular expression
     * 
     * @param time
     *            time address for validation
     * @return true valid time fromat, false invalid time format
     */
    public boolean validate(final String time) {
        this.matcher = this.pattern.matcher(time);
        return this.matcher.matches();
    }
}
