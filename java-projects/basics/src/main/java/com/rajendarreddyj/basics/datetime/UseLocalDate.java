package com.rajendarreddyj.basics.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author rajendarreddy
 *
 */
public class UseLocalDate {
    public LocalDate getLocalDateUsingFactoryOfMethod(final int year, final int month, final int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    public LocalDate getLocalDateUsingParseMethod(final String representation) {
        return LocalDate.parse(representation);
    }

    public LocalDate getLocalDateFromClock() {
        LocalDate localDate = LocalDate.now();
        return localDate;
    }

    public LocalDate getNextDay(final LocalDate localDate) {
        return localDate.plusDays(1);
    }

    public LocalDate getPreviousDay(final LocalDate localDate) {
        return localDate.minus(1, ChronoUnit.DAYS);
    }

    public DayOfWeek getDayOfWeek(final LocalDate localDate) {
        DayOfWeek day = localDate.getDayOfWeek();
        return day;
    }

    public LocalDate getFirstDayOfMonth() {
        LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        return firstDayOfMonth;
    }

    public LocalDateTime getStartOfDay(final LocalDate localDate) {
        LocalDateTime startofDay = localDate.atStartOfDay();
        return startofDay;
    }
}
