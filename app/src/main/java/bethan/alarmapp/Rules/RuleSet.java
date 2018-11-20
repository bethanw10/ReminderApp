package bethan.alarmapp.Rules;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {
    enum BankHolidayRule {
        ALWAYS_TRUE,
        ALWAYS_FALSE,
        RUN_AS_USUAL
    }

    public List<Rule> rules;
    private BankHolidayRule bankHolidayRule;

    public RuleSet() {
        this.rules = new ArrayList<Rule>();
    }

    RuleSet(List<Rule> rules) {
        this.rules = rules;
    }

    RuleSet(List<Rule> rules, BankHolidayRule bankHolidayRule) {
        this.rules = rules;
        this.bankHolidayRule = bankHolidayRule;
    }

    // temp
    public void updateRule(Rule rule) {
        for (Rule rulesetRule : rules) {
            if(rulesetRule.getClass().getName().equals(rule.getClass().getName())) {
                rules.remove(rulesetRule);
                rules.add(rule);
                return;
            }
        }
    }

    public boolean isTrueForDate(LocalDate date) {
        for (Rule rule : rules) {
            if (!rule.isTrueForDate(date)) {
                return false;
            }
        }

        return true;
    }
}

