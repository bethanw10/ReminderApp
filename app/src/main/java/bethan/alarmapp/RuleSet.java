package bethan.alarmapp;

import org.threeten.bp.DayOfWeek;

import java.util.List;

public class RuleSet {
    enum BankHolidayRule {
        ALWAYS_TRUE,
        ALWAYS_FALSE,
        RUN_AS_USUAL
    }

    private List<Rule> rules;
    private BankHolidayRule bankHolidayRule;

    RuleSet(List<Rule> rules, BankHolidayRule bankHolidayRule) {
        this.rules = rules;
        this.bankHolidayRule = bankHolidayRule;
    }
}

