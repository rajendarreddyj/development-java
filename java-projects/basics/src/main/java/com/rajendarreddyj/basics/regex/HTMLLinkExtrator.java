package com.rajendarreddyj.basics.regex;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * HTML A tag Regular Expression Pattern (?i)<a([^>]+)>(.+?)</a> Extract HTML link Regular Expression Pattern
 * \s*(?i)href\s*=\s*(\"([^"]*\")|'[^']*'|([^'">\s]+)); Description ( #start of group #1 ?i # all checking are case
 * insensive ) #end of group #1 <a #start with "<a" ( # start of group #2 [^>]+ # anything except (">"), at least one
 * character ) # end of group #2 > # follow by ">" (.+?) # match anything </a> # end with "</a>
 * 
 * \s* #can start with whitespace (?i) # all checking are case insensive href # follow by "href" word \s*=\s* # allows
 * spaces on either side of the equal sign, ( # start of group #1
 * "([^"]*")   #      allow string with double quotes enclosed - "string" | # ..or '[^']*' # allow string with single
 * quotes enclosed - 'string' | # ..or ([^'">]+)   #      can't contains one single quotes, double quotes ">" ) # end of
 * group #1 Here is a simple Java Link extractor to extract the ‘href’ value from 1st pattern, and use 2nd pattern to
 * extract the link from 1st pattern value. Of course with some logic as below.
 */
public class HTMLLinkExtrator {
    private Pattern patternTag, patternLink;
    private Matcher matcherTag, matcherLink;
    private static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
    private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

    public HTMLLinkExtrator() {
        this.patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
        this.patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
    }

    /**
     * Validate html with regular expression
     * 
     * @param html
     *            html content for validation
     * @return Vector links and link text
     */
    public Vector<HtmlLink> grabHTMLLinks(final String html) {
        Vector<HtmlLink> result = new Vector<>();
        this.matcherTag = this.patternTag.matcher(html);
        while (this.matcherTag.find()) {
            String href = this.matcherTag.group(1); // href
            String linkText = this.matcherTag.group(2); // link text
            this.matcherLink = this.patternLink.matcher(href);
            while (this.matcherLink.find()) {
                String link = this.matcherLink.group(1); // link
                result.add(new HtmlLink(link, linkText));
            }
        }
        return result;
    }

    public class HtmlLink {
        String link;
        String linkText;

        HtmlLink(final String link, final String linkText) {
            this.link = link;
            this.linkText = linkText;
        }

        @Override
        public String toString() {
            return new StringBuffer("Link : ").append(this.link).append(" Link Text : ").append(this.linkText).toString();
        }
    }
}