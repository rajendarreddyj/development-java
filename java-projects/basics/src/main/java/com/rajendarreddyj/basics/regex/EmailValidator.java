package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Email Regular Expression Pattern ^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@
 * [A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$ Description ^ #start of the line [_A-Za-z0-9-]+ # must start with
 * string in the bracket [ ], must contains one or more (+) ( # start of group #1 \\.[_A-Za-z0-9-]+ # follow by a dot
 * "." and string in the bracket [ ], must contains one or more (+) )* # end of group #1, this group is optional (*)
 * 
 * @ # must contains a "@" symbol [A-Za-z0-9]+ # follow by string in the bracket [ ], must contains one or more (+) ( #
 * start of group #2 - first level TLD checking \\.[A-Za-z0-9]+ # follow by a dot "." and string in the bracket [ ],
 * must contains one or more (+) )* # end of group #2, this group is optional (*) ( # start of group #3 - second level
 * TLD checking \\.[A-Za-z]{2,} # follow by a dot "." and string in the bracket [ ], with minimum length of 2 ) # end of
 * group #3 $ #end of the line Whole combination is means, email address must start with “_A-Za-z0-9-” , optional follow
 * by “.[_A-Za-z0-9-]“, and end with a “@” symbol. The email’s domain name must start with “A-Za-z0-9″, follow by first
 * level Tld (.com, .net) “. [A-Za-z0-9]” and optional follow by a second level Tld (.com.au, .com.my)
 * “\\.[A-Za-z]{2,}”, where second level Tld must start with a dot “.” and length must equal or more than 2 characters.
 */
public class EmailValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        this.pattern = Pattern.compile(EMAIL_PATTERN);
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
