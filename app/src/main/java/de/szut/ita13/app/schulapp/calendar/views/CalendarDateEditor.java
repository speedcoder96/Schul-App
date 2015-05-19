package de.szut.ita13.app.schulapp.calendar.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;
import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by rama on 12.05.2015.
 */
public class CalendarDateEditor extends ActionBarActivity {

    TextView date;
    TextView weekday;
    EditText title;
    EditText note;
    CalendarTimePicker start;
    CalendarTimePicker end;
    CalendarAppointment appointment;
    CalendarDate calendarDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendardate_editor);
        Intent intent = getIntent();
        appointment = (CalendarAppointment) intent.getSerializableExtra(CalendarAppointment.SERIALIZABLE_KEY);
        calendarDate = appointment.getCalendarDate();

        String dateString = calendarDate.getDateString();
        String weekdayString = DateUtil.WEEKDAYS[calendarDate.getWeekday()];
        String titleString = appointment.getSubject();
        String noteString = appointment.getNote();
        CalendarTime startTime = appointment.getStartTime();
        CalendarTime endTime = appointment.getEndTime();

        if(titleString != null) {
            date = (TextView) findViewById(R.id.calendareditor_date);
            date.setText(dateString);

            weekday = (TextView) findViewById(R.id.calendareditor_weekday);
            weekday.setText(weekdayString);

            title = (EditText) findViewById(R.id.calendareditor_title);
            title.setText(titleString);

            note = (EditText) findViewById(R.id.calendareditor_note);
            note.setText(noteString);

            start = (CalendarTimePicker) findViewById(R.id.timepicker_start);
            start.setCalendarTime(appointment.getStartTime());

            end = (CalendarTimePicker) findViewById(R.id.timepicker_end);
            end.setCalendarTime(appointment.getEndTime());

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calendareditor, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.action_save:

                CalendarTime timeStart = start.getCalendarTime();
                CalendarTime timeEnd = end.getCalendarTime();

                if(timeEnd.isBefore(timeStart)){
                    Toast.makeText(this.getApplicationContext(),"check ma die Zeit DU Sucuk, ALTER",Toast.LENGTH_LONG).show();
                }
                else{
                    appointment.setSubject(title.getText().toString());
                    appointment.setStartTime(timeStart);
                    appointment.setEndTime(timeEnd);
                    appointment.setNote(note.getText().toString());
                }
                break;
            case R.id.action_cancel:
                break;
        }
        return true;
    }
}

