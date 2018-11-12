package bethan.alarmapp;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

public abstract class Rule {
    public abstract boolean isTrueForDate(LocalDate date);
}
