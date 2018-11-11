package bethan.alarmapp;

import org.threeten.bp.LocalDateTime;

import java.util.List;

public class SpecificDatesRule extends Rule {

    private List<LocalDateTime> dates;

    SpecificDatesRule(List<LocalDateTime> onDays) {
        this.dates = onDays;
    }

    public boolean isTrueForDate(LocalDateTime date) {
        return dates.contains(date);
    }
}
