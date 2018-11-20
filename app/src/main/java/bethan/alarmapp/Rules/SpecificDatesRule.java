package bethan.alarmapp.Rules;

import org.threeten.bp.LocalDate;

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
