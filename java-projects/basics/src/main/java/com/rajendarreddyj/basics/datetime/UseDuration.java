package com.rajendarreddyj.basics.datetime;

import java.time.Duration;
import java.time.LocalTime;

/**
 * @author rajendarreddy
 *
 */
public class UseDuration {
    public LocalTime modifyDates(final LocalTime localTime, final Duration duration) {
        return localTime.plus(duration);
    }

    public Duration getDifferenceBetweenDates(final LocalTime localTime1, final LocalTime localTime2) {
        return Duration.between(localTime1, localTime2);
    }
}
