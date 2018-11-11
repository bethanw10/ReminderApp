package bethan.alarmapp;

import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MaterialCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calendar = findViewById(R.id.calendarView);

        calendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);

        calendar.setDateSelected(CalendarDay.today(), true);

        calendar.setDateSelected(CalendarDay.from(2018, 11, 11), true);
    }

    private void setDatesSelected(MaterialCalendarView calendar, CalendarDay... dates) {
        for (CalendarDay date : dates) {
            calendar.setDateSelected(date, true);
        }
    }

}


