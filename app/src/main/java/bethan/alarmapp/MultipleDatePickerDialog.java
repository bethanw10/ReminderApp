package bethan.alarmapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.List;

public class MultipleDatePickerDialog extends DialogFragment {

    MaterialCalendarView calenderPicker;

    TextView datesText;

    MultipleDatePickerListener mListener;

    public interface MultipleDatePickerListener {
        void onDialogPositiveClick(DialogFragment dialog, List<CalendarDay> selectedDates);
    }

    // TODO Button colour?
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.multiple_date_picker_dialog, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getDialog().cancel();
                    }
                });

        Dialog dialog =  builder.create();

        datesText = view.findViewById(R.id.dateText);
        calenderPicker = view.findViewById(R.id.datePicker);

        calenderPicker.state().edit()
                .setMinimumDate(CalendarDay.today())
                .commit();

        calenderPicker.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

        calenderPicker.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView,
                                       @NonNull CalendarDay calendarDay, boolean b) {

                Integer datesSelected = calenderPicker.getSelectedDates().size();

                datesText.setText(datesSelected == 1 ?
                        "1 date selected" :
                        datesSelected.toString() + " dates selected");

            }
        });

        return dialog;
    }

}
