package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * IP Address Regular Expression Pattern ^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.
 * ([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$ Description ^ #start of the line ( # start of
 * group #1 [01]?\\d\\d? # Can be one or two digits. If three digits appear, it must start either 0 or 1 # e.g ([0-9],
 * [0-9][0-9],[0-1][0-9][0-9]) | # ...or 2[0-4]\\d # start with 2, follow by 0-4 and end with any digit (2[0-4][0-9]) |
 * # ...or 25[0-5] # start with 2, follow by 5 and end with 0-5 (25[0-5]) ) # end of group #2 \. # follow by a dot "."
 * .... # repeat with 3 time (3x) $ #end of the line Whole combination means , digit from 0 to 255 and follow by a dot
 * “.”, repeat 4 time and ending with no dot “.” Valid IP address format is “0-255.0-255.0-255.0-255″.
 */
public class IPAddressValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public IPAddressValidator() {
        this.pattern = Pattern.compile(IPADDRESS_PATTERN);
    }

    /**
     * Validate ip address with regular expression
     * 
     * @param ip
     *            ip address for validation
     * @return true valid ip address, false invalid ip address
     */
    public boolean validate(final String ip) {
        this.matcher = this.pattern.matcher(ip);
        return this.matcher.matches();
    }
}
