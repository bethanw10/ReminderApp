package bethan.alarmapp;

import org.junit.Test;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;

import java.util.Arrays;

import static org.junit.Assert.*;

public class WeekdayRuleTest {

    @Test
    public void isTrueForDate() {
        WeekdayRule rule = new WeekdayRule(Arrays.asList(DayOfWeek.MONDAY));

        LocalDateTime mondayDate =  LocalDateTime.of(
                2018, Month.NOVEMBER, 12,
                0, 0, 0);

        LocalDateTime tuesdayDate =  LocalDateTime.of(
                2018, Month.NOVEMBER, 13,
                0, 0, 0);

        assertTrue(rule.isTrueForDate(mondayDate));
        assertFalse(rule.isTrueForDate(tuesdayDate));
    }
}