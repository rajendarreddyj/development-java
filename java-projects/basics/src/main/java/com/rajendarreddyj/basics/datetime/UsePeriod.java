package com.rajendarreddyj.basics.datetime;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author rajendarreddy
 *
 */
public class UsePeriod {
    public LocalDate modifyDates(final LocalDate localDate, final Period period) {
        return localDate.plus(period);
    }

    public Period getDifferenceBetweenDates(final LocalDate localDate1, final LocalDate localDate2) {
        return Period.between(localDate1, localDate2);
    }
}
