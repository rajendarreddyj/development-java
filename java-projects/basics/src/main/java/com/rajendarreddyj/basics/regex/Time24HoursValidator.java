package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * Time in 24-Hour Format Regular Expression Pattern ([01]?[0-9]|2[0-3]):[0-5][0-9] Description ( #start of group #1
 * [01]?[0-9] # start with 0-9,1-9,00-09,10-19 | # or 2[0-3] # start with 20-23 ) #end of group #1 : # follow by a semi
 * colon (:) [0-5][0-9] # follow by 0..5 and 0..9, which means 00 to 59 The 24-hour clock format is start from 0-23 or
 * 00-23 then a semi colon (:) and follow by 00-59.
 */

public class Time24HoursValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public Time24HoursValidator() {
        this.pattern = Pattern.compile(TIME24HOURS_PATTERN);
    }

    /**
     * Validate time in 24 hours format with regular expression
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
