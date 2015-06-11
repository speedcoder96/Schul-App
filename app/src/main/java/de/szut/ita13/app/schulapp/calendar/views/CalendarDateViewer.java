package de.szut.ita13.app.schulapp.calendar.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAppointmentListAdapter;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;
import de.szut.ita13.app.schulapp.newutils.AppointmentUtil;
import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by Rene on 11.05.2015.
 */
public class CalendarDateViewer extends ActionBarActivity implements MenuItem.OnMenuItemClickListener,
    View.OnClickListener {

    public static final String APPOINTMENT_INDEX = "appointment-index";

    private CalendarDate calendarDate;
    private CalendarAppointmentListAdapter calendarAppointmentListAdapter;
    private String dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendardate_viewer);
        Intent intent = getIntent();
        dateFormat = intent.getStringExtra(CalendarDate.DATE_FORMAT);
        calendarDate = Calendar.calendarMap.getCalendarDate(dateFormat);
        ArrayList<CalendarAppointment> calendarAppointments = calendarDate.getCalendarAppointments();

        ListView calendarAppointmentList = (ListView)findViewById(R.id.calendardate_appointmentlist);
        TextView calendarDateStatus = (TextView)findViewById(R.id.calendardate_status);
        TextView calendarDateDate = (TextView)findViewById(R.id.calendardate_date);
        TextView calendarDateWeekday = (TextView)findViewById(R.id.calendardate_weekday);
        calendarDateDate.setText(calendarDate.getDateString(CalendarDate.DEFAULT_DATE_FORMAT));
        calendarDateWeekday.setText(DateUtil.WEEKDAYS[calendarDate.getWeekday()]);

        if(calendarDate.hasAppointments()) {
            calendarAppointments = AppointmentUtil.Sorter.sort(calendarAppointments);
            calendarAppointmentListAdapter = new CalendarAppointmentListAdapter(this);
            calendarAppointmentList.setAdapter(calendarAppointmentListAdapter);
            calendarDateStatus.setText("Es ist 1 Termin vorhanden!");
            if(calendarAppointments.size() > 1) {
                calendarDateStatus.setText("Es sind " + calendarAppointments.size() + " Termine vorhanden!");
            }
        } else  {
            calendarDateStatus.setText(R.string.no_appointments);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_calendardate_viewer, menu);

        menu.getItem(0).setOnMenuItemClickListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.action_new_appointment) {
            CalendarAppointment calendarAppointment = new CalendarAppointment(calendarDate);
            calendarDate.addCalendarAppointment(calendarAppointment);
            gotoNextActivity(calendarAppointment);
        }
        return true;
    }

    public CalendarDate getCalendarDate() {
        return calendarDate;
    }

    @Override
    public void onClick(View appointmentView) {
        CalendarAppointment appointment = (CalendarAppointment)appointmentView.getTag();
        gotoNextActivity(appointment);
    }

    private int findAppointmentIndex(CalendarAppointment targetAppointment) {
        int index = 0;
        ArrayList<CalendarAppointment> calendarAppointments = calendarDate.getCalendarAppointments();
        for(int i = 0; i < calendarAppointments.size(); i++) {
            CalendarAppointment calendarAppointment = calendarAppointments.get(i);
            if(targetAppointment == calendarAppointment) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void gotoNextActivity(CalendarAppointment appointment) {
        Intent intent = new Intent(getApplicationContext(), CalendarDateEditor.class);
        intent.putExtra(CalendarDate.DATE_FORMAT, calendarDate.getDateString(CalendarDate.DATABASE_DATE_FORMAT));
        intent.putExtra(CalendarDateViewer.APPOINTMENT_INDEX, findAppointmentIndex(appointment));
        Calendar.getCalendarActivity().startActivity(intent);
        finish();
    }
}
