package com.rajendarreddyj.basics.datetime;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @author rajendarreddy
 *
 */
public class UseLocalTime {
    public LocalTime getLocalTimeUsingFactoryOfMethod(final int hour, final int min, final int seconds) {
        LocalTime localTime = LocalTime.of(hour, min, seconds);
        return localTime;
    }

    public LocalTime getLocalTimeUsingParseMethod(final String timeRepresentation) {
        LocalTime localTime = LocalTime.parse(timeRepresentation);
        return localTime;
    }

    public LocalTime getLocalTimeFromClock() {
        LocalTime localTime = LocalTime.now();
        return localTime;
    }

    public LocalTime addAnHour(final LocalTime localTime) {
        LocalTime newTime = localTime.plus(1, ChronoUnit.HOURS);
        return newTime;
    }

    public int getHourFromLocalTime(final LocalTime localTime) {
        return localTime.getHour();
    }

    public LocalTime getLocalTimeWithMinuteSetToValue(final LocalTime localTime, final int minute) {
        return localTime.withMinute(minute);
    }
}
