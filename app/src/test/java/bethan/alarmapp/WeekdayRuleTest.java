package bethan.alarmapp;

import org.junit.Test;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;

import java.util.Arrays;

import static org.junit.Assert.*;

public class WeekdayRuleTest {

    @Test
    public void isTrueForDate() {
        WeekdayRule rule = new WeekdayRule(Arrays.asList(DayOfWeek.MONDAY));

        LocalDate mondayDate =  LocalDate.of(
                2018, Month.NOVEMBER, 12);

        LocalDate tuesdayDate =  LocalDate.of(
                2018, Month.NOVEMBER, 13);

        assertTrue(rule.isTrueForDate(mondayDate));
        assertFalse(rule.isTrueForDate(tuesdayDate));
    }
}