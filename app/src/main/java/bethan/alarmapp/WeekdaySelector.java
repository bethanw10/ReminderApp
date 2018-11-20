package bethan.alarmapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import org.threeten.bp.DayOfWeek;

import java.util.ArrayList;
import java.util.List;

import static android.widget.CompoundButton.OnCheckedChangeListener;

// TODO: Custom attributes
public class WeekdaySelector extends LinearLayout {

    ToggleButton mondayBtn, tuesdayBtn, wednesdayBtn, thursdayBtn, fridayBtn,
            saturdayBtn, sundayBtn;

    private onWeekdaySelectedListener mOnWeekdaySelected;

    public WeekdaySelector(Context context) {
        this(context, null);
    }

    public WeekdaySelector(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.weekday_selector, this, true);

        mondayBtn = findViewById(R.id.mondayBtn);
        tuesdayBtn = findViewById(R.id.tuesdayBtn);
        wednesdayBtn = findViewById(R.id.wednesdayBtn);
        thursdayBtn = findViewById(R.id.thursdayBtn);
        fridayBtn = findViewById(R.id.fridayBtn);
        saturdayBtn = findViewById(R.id.saturdayBtn);
        sundayBtn = findViewById(R.id.sundayBtn);

        OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mOnWeekdaySelected != null) {
                    mOnWeekdaySelected.onWeekdaySelected(buttonView, isChecked);
                }
            }
        };

        mondayBtn.setOnCheckedChangeListener(onCheckedChangeListener);
        tuesdayBtn.setOnCheckedChangeListener(onCheckedChangeListener);
        wednesdayBtn.setOnCheckedChangeListener(onCheckedChangeListener);
        thursdayBtn.setOnCheckedChangeListener(onCheckedChangeListener);
        fridayBtn.setOnCheckedChangeListener(onCheckedChangeListener);
        saturdayBtn.setOnCheckedChangeListener(onCheckedChangeListener);
        sundayBtn.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public List<DayOfWeek> getSelectedDays() {
        List<DayOfWeek> days = new ArrayList<>();

        if (mondayBtn.isChecked()) days.add(DayOfWeek.MONDAY);
        if (tuesdayBtn.isChecked()) days.add(DayOfWeek.TUESDAY);
        if (wednesdayBtn.isChecked()) days.add(DayOfWeek.WEDNESDAY);
        if (thursdayBtn.isChecked()) days.add(DayOfWeek.THURSDAY);
        if (fridayBtn.isChecked()) days.add(DayOfWeek.FRIDAY);
        if (saturdayBtn.isChecked()) days.add(DayOfWeek.SATURDAY);
        if (sundayBtn.isChecked()) days.add(DayOfWeek.SUNDAY);

        return days;
    }

    public void setOnWeekdaySelected(onWeekdaySelectedListener eventListener) {
        mOnWeekdaySelected = eventListener;
    }

    public interface onWeekdaySelectedListener {
        void onWeekdaySelected(CompoundButton buttonView, boolean isChecked);
    }
}