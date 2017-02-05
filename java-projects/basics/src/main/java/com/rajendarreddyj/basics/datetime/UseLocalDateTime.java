package com.rajendarreddyj.basics.datetime;

import java.time.LocalDateTime;

/**
 * @author rajendarreddy
 *
 */
public class UseLocalDateTime {
    public LocalDateTime getLocalDateTimeUsingParseMethod(final String representation) {
        return LocalDateTime.parse(representation);
    }
}
