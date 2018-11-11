package bethan.alarmapp;

import org.threeten.bp.LocalDateTime;

public abstract class Rule {
    public abstract boolean isTrueForDate(LocalDateTime date);
}
