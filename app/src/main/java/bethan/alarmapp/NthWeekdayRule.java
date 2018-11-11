package bethan.alarmapp;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDateTime;

// n can be between 1 - 5
public class NthWeekdayRule extends Rule {

    private DayOfWeek dayOfWeek;
    private Integer weekNumber;

    NthWeekdayRule(DayOfWeek dayOfWeek, Integer weekNumber) {
        this.dayOfWeek = dayOfWeek;
        this.weekNumber = weekNumber;
    }

    @Override
    public boolean isTrueForDate(LocalDateTime date) {
        if (date.getDayOfWeek() == dayOfWeek) {
            int dateWeekNumber = ((date.getDayOfMonth() - 1) / 7) + 1;

            return dateWeekNumber == weekNumber;
        }

        return false;
    }
}
