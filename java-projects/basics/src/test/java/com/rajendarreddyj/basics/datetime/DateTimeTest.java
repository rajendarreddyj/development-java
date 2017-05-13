package com.rajendarreddyj.basics.datetime;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rajendarreddy
 *
 */
public class DateTimeTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    UseLocalDateTime useLocalDateTime = new UseLocalDateTime();
    UseLocalDate useLocalDate = new UseLocalDate();
    UseLocalTime useLocalTime = new UseLocalTime();
    UsePeriod usingPeriod = new UsePeriod();
    UseZonedDateTime zonedDateTime = new UseZonedDateTime();

    @Test
    public void currentTime() {
        final LocalDate now = LocalDate.now();
        logger.info(now.toString());
        // there is not much to test here
    }

    @Test
    public void specificTime() {
        LocalDate birthDay = LocalDate.of(1987, Month.MARCH, 17);
        logger.info(birthDay.toString());
        // there is not much to test here
    }

    @Test
    public void extractMonth() {
        Month month = LocalDate.of(1990, Month.DECEMBER, 15).getMonth();
        Assertions.assertThat(month).isEqualTo(Month.DECEMBER);
    }

    @Test
    public void subtractTime() {
        LocalDateTime fiveHoursBefore = LocalDateTime.of(1990, Month.DECEMBER, 15, 15, 0).minusHours(5);
        Assertions.assertThat(fiveHoursBefore.getHour()).isEqualTo(10);
    }

    @Test
    public void alterField() {
        LocalDateTime inJune = LocalDateTime.of(1990, Month.DECEMBER, 15, 15, 0).with(Month.JUNE);
        Assertions.assertThat(inJune.getMonth()).isEqualTo(Month.JUNE);
    }

    @Test
    public void truncate() {
        LocalTime truncated = LocalTime.of(15, 12, 34).truncatedTo(ChronoUnit.HOURS);
        Assertions.assertThat(truncated).isEqualTo(LocalTime.of(15, 0, 0));
    }

    @Test
    public void getTimeSpan() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime hourLater = now.plusHours(1);
        Duration span = Duration.between(now, hourLater);
        Assertions.assertThat(span).isEqualTo(Duration.ofHours(1));
    }

    @Test
    public void formatAndParse() throws ParseException {
        LocalDate someDate = LocalDate.of(2016, 12, 7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = someDate.format(formatter);
        LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
        Assertions.assertThat(formattedDate).isEqualTo("2016-12-07");
        Assertions.assertThat(parsedDate).isEqualTo(someDate);
    }

    @Test
    public void daysInMonth() {
        int daysInMonth = YearMonth.of(1990, 2).lengthOfMonth();
        Assertions.assertThat(daysInMonth).isEqualTo(28);
    }

    @Test
    public void givenString_whenUsingParse_thenLocalDateTime() {
        Assert.assertEquals(LocalDate.of(2016, Month.MAY, 10), this.useLocalDateTime.getLocalDateTimeUsingParseMethod("2016-05-10T06:30").toLocalDate());
        Assert.assertEquals(LocalTime.of(6, 30), this.useLocalDateTime.getLocalDateTimeUsingParseMethod("2016-05-10T06:30").toLocalTime());
    }

    @Test
    public void givenValues_whenUsingFactoryOf_thenLocalDate() {
        Assert.assertEquals("2016-05-10", this.useLocalDate.getLocalDateUsingFactoryOfMethod(2016, 5, 10).toString());
    }

    @Test
    public void givenString_whenUsingParse_thenLocalDate() {
        Assert.assertEquals("2016-05-10", this.useLocalDate.getLocalDateUsingParseMethod("2016-05-10").toString());
    }

    @Test
    public void whenUsingClock_thenLocalDate() {
        Assert.assertEquals(LocalDate.now(), this.useLocalDate.getLocalDateFromClock());
    }

    @Test
    public void givenDate_whenUsingPlus_thenNextDay() {
        Assert.assertEquals(LocalDate.now().plusDays(1), this.useLocalDate.getNextDay(LocalDate.now()));
    }

    @Test
    public void givenDate_whenUsingMinus_thenPreviousDay() {
        Assert.assertEquals(LocalDate.now().minusDays(1), this.useLocalDate.getPreviousDay(LocalDate.now()));
    }

    @Test
    public void givenToday_whenUsingGetDayOfWeek_thenDayOfWeek() {
        Assert.assertEquals(DayOfWeek.SUNDAY, this.useLocalDate.getDayOfWeek(LocalDate.parse("2016-05-22")));
    }

    @Test
    public void givenToday_whenUsingWithTemporalAdjuster_thenFirstDayOfMonth() {
        Assert.assertEquals(1, this.useLocalDate.getFirstDayOfMonth().getDayOfMonth());
    }

    @Test
    public void givenLocalDate_whenUsingAtStartOfDay_thenReturnMidnight() {
        Assert.assertEquals(LocalDateTime.parse("2016-05-22T00:00:00"), this.useLocalDate.getStartOfDay(LocalDate.parse("2016-05-22")));
    }

    @Test
    public void givenValues_whenUsingFactoryOf_thenLocalTime() {
        Assert.assertEquals("07:07:07", this.useLocalTime.getLocalTimeUsingFactoryOfMethod(7, 7, 7).toString());
    }

    @Test
    public void givenString_whenUsingParse_thenLocalTime() {
        Assert.assertEquals("06:30", this.useLocalTime.getLocalTimeUsingParseMethod("06:30").toString());
    }

    @Test
    public void givenTime_whenAddHour_thenLocalTime() {
        Assert.assertEquals("07:30", this.useLocalTime.addAnHour(LocalTime.of(6, 30)).toString());
    }

    @Test
    public void getHourFromLocalTime() {
        Assert.assertEquals(1, this.useLocalTime.getHourFromLocalTime(LocalTime.of(1, 1)));
    }

    @Test
    public void getLocalTimeWithMinuteSetToValue() {
        Assert.assertEquals(LocalTime.of(10, 20), this.useLocalTime.getLocalTimeWithMinuteSetToValue(LocalTime.of(10, 10), 20));
    }

    @Test
    public void givenPeriodAndLocalDate_thenCalculateModifiedDate() {
        Period period = Period.ofDays(1);
        LocalDate localDate = LocalDate.parse("2007-05-10");
        Assert.assertEquals(localDate.plusDays(1), this.usingPeriod.modifyDates(localDate, period));
    }

    @Test
    public void givenDates_thenGetPeriod() {
        LocalDate localDate1 = LocalDate.parse("2007-05-10");
        LocalDate localDate2 = LocalDate.parse("2007-05-15");
        Assert.assertEquals(Period.ofDays(5), this.usingPeriod.getDifferenceBetweenDates(localDate1, localDate2));
    }

    @Test
    public void givenZoneId_thenZonedDateTime() {
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime zonedDatetime = this.zonedDateTime.getZonedDateTime(LocalDateTime.parse("2016-05-20T06:30"), zoneId);
        Assert.assertEquals(zoneId, ZoneId.from(zonedDatetime));
    }
}
