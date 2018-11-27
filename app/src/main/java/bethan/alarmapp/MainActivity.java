package bethan.alarmapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import bethan.alarmapp.Rules.RuleSet;
import bethan.alarmapp.Rules.WeekdayRule;

// TODO: Butter knife?
public class MainActivity extends FragmentActivity {

    WeekdaySelector weekdaySelector;

    FloatingActionButton editDatesBtn;

    TextView datesText;

    MaterialCalendarView calendar;

    private RuleSet rulesSet;
    private LocalDate currentMonthStartDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();

        rulesSet = new RuleSet();

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
                updateCalendar();
            }
        });

        weekdaySelector.setOnWeekdaySelected(new WeekdaySelector.onWeekdaySelectedListener() {
            @Override
            public void onWeekdaySelected(CompoundButton buttonView, boolean isChecked) {
                List<DayOfWeek> daysOfWeek = weekdaySelector.getSelectedDays();

                WeekdayRule weekdayRule = new WeekdayRule(daysOfWeek);
                rulesSet.updateRule(weekdayRule);
                updateCalendar();
            }
        });

        editDatesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultipleDatePickerDialog dialog = new MultipleDatePickerDialog();
                dialog.show(getSupportFragmentManager(), "MultipleDatePickerDialog");
            }
        });
    }

    private void getViews() {
        editDatesBtn = findViewById(R.id.editDatesBtn);
        datesText = findViewById(R.id.dateText);
        calendar = findViewById(R.id.calendarView);
        weekdaySelector = findViewById(R.id.weekdaySelector);
    }

    private void updateCalendar() {
        List<CalendarDay> datesToSelect = new ArrayList<>();

        LocalDate startDate = LocalDate.now().isBefore(currentMonthStartDate) ?
                LocalDate.now() :
                currentMonthStartDate.minusMonths(1);

        LocalDate endDate = currentMonthStartDate.plusMonths(2);

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (rulesSet.isTrueForDate(date)) {
                datesToSelect.add(CalendarDay.from(date));
            }
        }

        setDatesSelected(calendar, datesToSelect);
    }

    private void setDatesSelected(MaterialCalendarView calendar, List<CalendarDay> dates) {
        calendar.clearSelection();

        for (CalendarDay date : dates) {
            calendar.setDateSelected(date, true);
        }
    }

    private AlertDialog dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        MaterialCalendarView calenderPicker = findViewById(R.id.datePicker);
        calenderPicker.state().edit()
                .setMinimumDate(CalendarDay.today())
                .commit();

        calenderPicker.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.multiple_date_picker_dialog, null))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        return builder.create();
    }

}


