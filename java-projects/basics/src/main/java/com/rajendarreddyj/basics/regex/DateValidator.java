package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Date Format (dd/mm/yyyy) Regular Expression Pattern (0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)
 * Description ( #start of group #1 0?[1-9] # 01-09 or 1-9 | # ..or [12][0-9] # 10-19 or 20-29 | # ..or 3[01] # 30, 31 )
 * #end of group #1 / # follow by a "/" ( # start of group #2 0?[1-9] # 01-09 or 1-9 | # ..or 1[012] # 10,11,12 ) # end
 * of group #2 / # follow by a "/" ( # start of group #3 (19|20)\\d\\d # 19[0-9][0-9] or 20[0-9][0-9] ) # end of group
 * #3 The above regular expression is used to validate the date format in “dd/mm/yyyy”, you can easy customize to suit
 * your need. However, it’s a bit hard to validate the leap year , 30 or 31 days of a month, we may need basic logic as
 * below.
 */
public class DateValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    public DateValidator() {
        this.pattern = Pattern.compile(DATE_PATTERN);
    }

    /**
     * Validate date format with regular expression
     * 
     * @param date
     *            date address for validation
     * @return true valid date fromat, false invalid date format
     */
    public boolean validate(final String date) {
        this.matcher = this.pattern.matcher(date);
        if (this.matcher.matches()) {
            this.matcher.reset();
            if (this.matcher.find()) {
                String day = this.matcher.group(1);
                String month = this.matcher.group(2);
                int year = Integer.parseInt(this.matcher.group(3));
                if (day.equals("31") && (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11") || month.equals("04")
                        || month.equals("06") || month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    // leap year
                    if ((year % 4) == 0) {
                        if (day.equals("30") || day.equals("31")) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        if (day.equals("29") || day.equals("30") || day.equals("31")) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
