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

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;
import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by rama on 12.05.2015.
 */
public class CalendarDateEditor extends ActionBarActivity {

    private TextView date;
    private TextView weekday;
    private EditText title;
    private EditText note;
    private CalendarTimePicker start;
    private CalendarTimePicker end;
    private CalendarAppointment appointment;
    private CalendarDate calendarDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendardate_editor);
        Intent intent = getIntent();
        calendarDate = Calendar.calendarMap.getCalendarDate(intent.getStringExtra(CalendarDate.DATE_FORMAT));
        ArrayList<CalendarAppointment> calendarAppointments = calendarDate.getCalendarAppointments();
        appointment = calendarAppointments.get(intent.getIntExtra(CalendarDateViewer.APPOINTMENT_INDEX, 0));

        String dateString = calendarDate.getDateString(CalendarDate.DEFAULT_DATE_FORMAT);
        String weekdayString = DateUtil.WEEKDAYS[calendarDate.getWeekday()];
        String titleString = appointment.getSubject();
        String noteString = appointment.getNote();

        date = (TextView) findViewById(R.id.calendareditor_date);
        date.setText(dateString);

        weekday = (TextView) findViewById(R.id.calendareditor_weekday);
        weekday.setText(weekdayString);

        title = (EditText) findViewById(R.id.calendareditor_title);
        note = (EditText) findViewById(R.id.calendareditor_note);
        start = (CalendarTimePicker) findViewById(R.id.timepicker_start);
        end = (CalendarTimePicker) findViewById(R.id.timepicker_end);

        if(titleString != null) {
            title.setText(titleString);
            note.setText(noteString);
            start.setCalendarTime(appointment.getStartTime());
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
        switch(item.getItemId()) {
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
                    Toast.makeText(this.getApplicationContext(), "Termin wurde gespeichert!", Toast.LENGTH_LONG).show();
                    CalendarAppointment.calendarAppointmentListAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.action_cancel:
                //TODO hier wieder auf den CalendarDateViewer zurück verweisen
                break;
        }
        return true;
    }
}

