package bethan.alarmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

// TODO: Butter knife?
public class MainActivity extends AppCompatActivity {

    MaterialCalendarView calendar;

    Switch mondaySwitch, tuesdaySwitch, wednesdaySwitch, thursdaySwitch, fridaySwitch,
            saturdaySwitch, sundaySwitch;

    Button saveBtn;

    WeekdayRule weekdayRule;
    LocalDate currentMonthStartDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();

        weekdayRule = new WeekdayRule(new ArrayList<DayOfWeek>());
        currentMonthStartDate = LocalDate.now();

        calendar.state().edit()
                .setMinimumDate(CalendarDay.today())
                .commit();

        calendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);

        calendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView materialCalendarView,
                                       CalendarDay calendarDay) {
                currentMonthStartDate = calendarDay.getDate();
                updateCalendar(weekdayRule);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<DayOfWeek> daysOfWeek = getSelectedDayOfWeeks();

                weekdayRule = new WeekdayRule(daysOfWeek);
                updateCalendar(weekdayRule);
            }
        });
    }

    private void updateCalendar(Rule rule) {
        List<CalendarDay> datesToSelect = new ArrayList<>();

        LocalDate startDate = LocalDate.now().isBefore(currentMonthStartDate) ?
                LocalDate.now() :
                currentMonthStartDate.minusMonths(1);

        LocalDate endDate = currentMonthStartDate.plusMonths(2);

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (rule.isTrueForDate(date)) {
                datesToSelect.add(CalendarDay.from(date));
            }
        }

        setDatesSelected(calendar, datesToSelect);
    }

    private List<DayOfWeek> getSelectedDayOfWeeks() {
        List<DayOfWeek> daysOfWeek = new ArrayList<>();

        if (mondaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.MONDAY);
        if (tuesdaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.TUESDAY);
        if (wednesdaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.WEDNESDAY);
        if (thursdaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.THURSDAY);
        if (fridaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.FRIDAY);
        if (saturdaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.SATURDAY);
        if (sundaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.SUNDAY);

        return daysOfWeek;
    }

    private void getViews() {
        calendar = findViewById(R.id.calendarView);

        mondaySwitch = findViewById(R.id.mondaySwitch);
        tuesdaySwitch = findViewById(R.id.tuesdaySwitch);
        wednesdaySwitch = findViewById(R.id.wednesdaySwitch);
        thursdaySwitch = findViewById(R.id.thursdaySwitch);
        fridaySwitch = findViewById(R.id.fridaySwitch);
        saturdaySwitch = findViewById(R.id.saturdaySwitch);
        sundaySwitch = findViewById(R.id.sundaySwitch);

        saveBtn = findViewById(R.id.saveBtn);
    }

    private void setDatesSelected(MaterialCalendarView calendar, List<CalendarDay> dates) {
        calendar.clearSelection();

        for (CalendarDay date : dates) {
            calendar.setDateSelected(date, true);
        }
    }

}


