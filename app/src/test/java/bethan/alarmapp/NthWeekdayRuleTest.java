package bethan.alarmapp;

import org.junit.Test;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;

import java.util.Arrays;

import static org.junit.Assert.*;

public class NthWeekdayRuleTest {

    @Test
    public void isTrueForDate_first() {
        NthWeekdayRule rule = new NthWeekdayRule(DayOfWeek.THURSDAY, 1);

        LocalDateTime firstThursday =  LocalDateTime.of(
                2018, Month.NOVEMBER, 1,
                0, 0, 0);

        LocalDateTime secondThursday =  LocalDateTime.of(
                2018, Month.NOVEMBER, 8,
                0, 0, 0);

        LocalDateTime firstFriday =  LocalDateTime.of(
                2018, Month.NOVEMBER, 16,
                0, 0, 0);

        assertTrue(rule.isTrueForDate(firstThursday));
        assertFalse(rule.isTrueForDate(secondThursday));
        assertFalse(rule.isTrueForDate(firstFriday));
    }

    @Test
    public void isTrueForDate_last() {
        NthWeekdayRule rule = new NthWeekdayRule(DayOfWeek.FRIDAY, 5);

        LocalDateTime fifthFriday=  LocalDateTime.of(
                2018, Month.NOVEMBER, 30,
                0, 0, 0);

        LocalDateTime fourthFriday =  LocalDateTime.of(
                2018, Month.NOVEMBER, 23,
                0, 0, 0);

        LocalDateTime fourthMonday =  LocalDateTime.of(
                2018, Month.NOVEMBER, 26,
                0, 0, 0);

        assertTrue(rule.isTrueForDate(fifthFriday));
        assertFalse(rule.isTrueForDate(fourthFriday));
        assertFalse(rule.isTrueForDate(fourthMonday));
    }
}