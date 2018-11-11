package bethan.alarmapp;

import org.threeten.bp.LocalDateTime;

import java.util.List;

public class DayOfMonthRule extends Rule {

    private List<Integer> days;

    DayOfMonthRule(List<Integer> days) {
        this.days = days;
    }

    public boolean isTrueForDate(LocalDateTime date) {
        return days.contains(date.getDayOfMonth());
    }
}

