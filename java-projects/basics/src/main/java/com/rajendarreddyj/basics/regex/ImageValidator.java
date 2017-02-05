package com.rajendarreddyj.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Image File Extension Regular Expression Pattern ([^\s]+(\.(?i)(jpg|png|gif|bmp))$) Description ( #Start of the group
 * #1 [^\s]+ # must contains one or more anything (except white space) ( # start of the group #2 \. # follow by a dot
 * "." (?i) # ignore the case sensive checking for the following characters ( # start of the group #3 jpg # contains
 * characters "jpg" | # ..or png # contains characters "png" | # ..or gif # contains characters "gif" | # ..or bmp #
 * contains characters "bmp" ) # end of the group #3 ) # end of the group #2 $ # end of the string ) #end of the group
 * #1 Whole combination is means, must have 1 or more strings (but not white space), follow by dot “.” and string end in
 * “jpg” or “png” or “gif” or “bmp” , and the file extensive is case-insensitive. This regular expression pattern is
 * widely use in for different file extensive checking. You can just change the end combination (jpg|png|gif|bmp) to
 * come out different file extension checking that suit your need.
 */
public class ImageValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public ImageValidator() {
        this.pattern = Pattern.compile(IMAGE_PATTERN);
    }

    /**
     * Validate image with regular expression
     * 
     * @param image
     *            image for validation
     * @return true valid image, false invalid image
     */
    public boolean validate(final String image) {
        this.matcher = this.pattern.matcher(image);
        return this.matcher.matches();
    }
}
