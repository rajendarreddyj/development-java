package com.rajendarreddyj.basics.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author rajendarreddy
 *
 */
public class UseToInstant {
    public LocalDateTime convertDateToLocalDate(final Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime;
    }

    public LocalDateTime convertDateToLocalDate(final Calendar calendar) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        return localDateTime;
    }
}
