package com.rajendarreddyj.basics.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author rajendarreddy
 *
 */
public class UseZonedDateTime {
    public ZonedDateTime getZonedDateTime(final LocalDateTime localDateTime, final ZoneId zoneId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        return zonedDateTime;
    }
}
