package bethan.alarmapp;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.List;

public class WeekdayRule extends Rule {

    private List<DayOfWeek> weekdays;

    WeekdayRule(List<DayOfWeek> onDays) {
        this.weekdays = onDays;
    }

    public boolean isTrueForDate(LocalDate date) {
        return weekdays.contains(date.getDayOfWeek());
    }
}

