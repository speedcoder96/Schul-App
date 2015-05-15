package de.szut.ita13.app.schulapp.calendar.views;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by rama on 12.05.2015.
 */
public class CalendarDateEditor extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendardate_editor);

        CalendarTimePicker timePicker = (CalendarTimePicker)findViewById(R.id.timepicker);
        Toast.makeText(this.getApplicationContext(), "Zeit:" + timePicker.getCalendarTime().getTimeString(), Toast.LENGTH_LONG).show();

    }
}
