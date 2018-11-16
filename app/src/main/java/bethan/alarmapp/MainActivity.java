package bethan.alarmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

// TODO: Butter knife?
public class MainActivity extends AppCompatActivity {

    WeekdaySelector weekdaySelector;

    MaterialCalendarView calendar;

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
                List<DayOfWeek> daysOfWeek = weekdaySelector.getSelectedDays();

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

    private void getViews() {
        weekdaySelector = findViewById(R.id.weekdaySelector);
        calendar = findViewById(R.id.calendarView);
        saveBtn = findViewById(R.id.saveBtn);
    }

    private void setDatesSelected(MaterialCalendarView calendar, List<CalendarDay> dates) {
        calendar.clearSelection();

        for (CalendarDay date : dates) {
            calendar.setDateSelected(date, true);
        }
    }

}


