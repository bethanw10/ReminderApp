package bethan.alarmapp;

import org.junit.Test;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

import java.util.Arrays;

import bethan.alarmapp.Rules.WeekdayRule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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