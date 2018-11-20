package bethan.alarmapp.Rules;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class WeekdayRule extends Rule {

    private List<DayOfWeek> weekdays;

    public WeekdayRule() {
        this.weekdays = new ArrayList<DayOfWeek>();
    }

    public WeekdayRule(List<DayOfWeek> onDays) {
        this.weekdays = onDays;
    }

    public boolean isTrueForDate(LocalDate date) {
        return weekdays.contains(date.getDayOfWeek());
    }
}

