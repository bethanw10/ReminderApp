package bethan.alarmapp;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.List;

public class SpecificDatesRule extends Rule {

    private List<LocalDate> dates;

    SpecificDatesRule(List<LocalDate> onDays) {
        this.dates = onDays;
    }

    public boolean isTrueForDate(LocalDate date) {
        return dates.contains(date);
    }
}
