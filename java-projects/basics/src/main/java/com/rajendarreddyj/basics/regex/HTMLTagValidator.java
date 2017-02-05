package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * HTML tag Regular Expression Pattern <("[^"]*"|'[^']*'|[^'">])*> Description
 * 
 * < #start with opening tag "<" ( # start of group #1 "[^"]*"	#	allow string with double quotes enclosed - "string"
 * | # ..or '[^']*' # allow string with single quote enclosed - 'string' | # ..or
 * [^'">]	#	cant contains one single quotes, double quotes and ">" ) # end of group #1 # 0 or more > #end with
 * closing tag ">" HTML tag, start with an opening tag “<" , follow by double quotes "string", or single quotes 'string'
 * but does not allow one double quotes (") "string, one single quote (') 'string or a closing tag > without single or
 * double quotes enclosed. At last , end with a closing tag “>”
 */public class HTMLTagValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

    public HTMLTagValidator() {
        this.pattern = Pattern.compile(HTML_TAG_PATTERN);
    }

    /**
     * Validate html tag with regular expression
     * 
     * @param tag
     *            html tag for validation
     * @return true valid html tag, false invalid html tag
     */
    public boolean validate(final String tag) {
        this.matcher = this.pattern.matcher(tag);
        return this.matcher.matches();
    }
}
