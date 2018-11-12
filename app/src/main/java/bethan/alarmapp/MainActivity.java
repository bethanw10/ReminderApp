package bethan.alarmapp;

import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Switch;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// TODO: Butter knife?
public class MainActivity extends AppCompatActivity {

    MaterialCalendarView calendar;

    Switch mondaySwitch, tuesdaySwitch, wednesdaySwitch, thursdaySwitch, fridaySwitch,
            saturdaySwitch, sundaySwitch;

    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();

        calendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<DayOfWeek> daysOfWeek = getSelectedDayOfWeeks();

                WeekdayRule weekdayRule = new WeekdayRule(daysOfWeek);

                updateCalendar(weekdayRule);
            }
        });
    }

    private void updateCalendar(Rule rule) {
        List<CalendarDay> datesToSelect = new ArrayList<>();

        // TODO: hard coded
        for (LocalDate date = LocalDate.of(2018, 11, 1); date.isBefore(LocalDate.of(2018, 11, 30)); date = date.plusDays(1))
        {
            if(rule.isTrueForDate(date)) {
                datesToSelect.add(CalendarDay.from(date));
            }
        }

        setDatesSelected(calendar, datesToSelect);
    }

    private List<DayOfWeek> getSelectedDayOfWeeks() {
        List<DayOfWeek> daysOfWeek = new ArrayList<>();

        if(mondaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.MONDAY);

        if(tuesdaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.TUESDAY);

        if(wednesdaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.WEDNESDAY);

        if(thursdaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.THURSDAY);

        if(fridaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.FRIDAY);

        if(saturdaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.SATURDAY);

        if(sundaySwitch.isChecked()) daysOfWeek.add(DayOfWeek.SUNDAY);

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
        for (CalendarDay date : dates) {
            calendar.setDateSelected(date, true);
        }
    }

}


