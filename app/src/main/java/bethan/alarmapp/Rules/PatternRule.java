package bethan.alarmapp.Rules;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.List;

public class PatternRule extends Rule {

    private LocalDate startDate;
    private List<Pattern> pattern;
    private Integer totalDays;

    public PatternRule(LocalDate startDate, List<Pattern> pattern) {
        this.startDate = startDate;
        this.pattern = pattern;
        this.totalDays = getTotalPatternDays(pattern);
    }

    @Override
    public boolean isTrueForDate(LocalDate date) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, date);

        int remainder = (int) (daysBetween % totalDays);

        int cumulativeTotal = 0;

        for (Pattern daySequence : pattern) {
            cumulativeTotal += daySequence.days;

            if (cumulativeTotal <= remainder) {
                return daySequence.on;
            }
        }

        return false;
    }

    private int getTotalPatternDays(List<Pattern> pattern) {
        int total = 0;

        for (Pattern daySequence : pattern) {
            total += daySequence.days;
        }
        return total;
    }
}

class Pattern {
    Integer days;
    Boolean on;
}