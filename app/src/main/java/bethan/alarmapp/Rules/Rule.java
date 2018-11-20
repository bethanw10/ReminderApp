package bethan.alarmapp.Rules;

import org.threeten.bp.LocalDate;

public abstract class Rule {
    public abstract boolean isTrueForDate(LocalDate date);
}
