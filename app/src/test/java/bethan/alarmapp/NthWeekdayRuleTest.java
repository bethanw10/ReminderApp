package bethan.alarmapp;

import org.junit.Test;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;

import java.util.Arrays;

import static org.junit.Assert.*;

public class NthWeekdayRuleTest {

    @Test
    public void isTrueForDate_first() {
        NthWeekdayRule rule = new NthWeekdayRule(DayOfWeek.THURSDAY, 1);

        LocalDate firstThursday =  LocalDate.of(
                2018, Month.NOVEMBER, 1);

        LocalDate secondThursday =  LocalDate.of(
                2018, Month.NOVEMBER, 8);

        LocalDate firstFriday =  LocalDate.of(
                2018, Month.NOVEMBER, 16);

        assertTrue(rule.isTrueForDate(firstThursday));
        assertFalse(rule.isTrueForDate(secondThursday));
        assertFalse(rule.isTrueForDate(firstFriday));
    }

    @Test
    public void isTrueForDate_last() {
        NthWeekdayRule rule = new NthWeekdayRule(DayOfWeek.FRIDAY, 5);

        LocalDate fifthFriday=  LocalDate.of(
                2018, Month.NOVEMBER, 30);

        LocalDate fourthFriday =  LocalDate.of(
                2018, Month.NOVEMBER, 23);

        LocalDate fourthMonday =  LocalDate.of(
                2018, Month.NOVEMBER, 26);

        assertTrue(rule.isTrueForDate(fifthFriday));
        assertFalse(rule.isTrueForDate(fourthFriday));
        assertFalse(rule.isTrueForDate(fourthMonday));
    }
}