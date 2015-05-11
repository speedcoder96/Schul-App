package de.szut.ita13.app.schulapp.calendar.views;

import android.app.Activity;
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

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAppointmentListAdapter;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;
import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by Rene on 11.05.2015.
 */
public class CalendarDateEditor extends ActionBarActivity implements MenuItem.OnMenuItemClickListener {

    private CalendarDate calendarDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendardate_editor);
        Intent intent = getIntent();
        calendarDate = (CalendarDate)intent.getSerializableExtra(CalendarDate.SERIALIZABLE_KEY);
        ArrayList<CalendarAppointment> calendarAppointments = calendarDate.getCalendarAppointments();

        ListView calendarAppointmentList = (ListView)findViewById(R.id.calendardate_appointmentlist);
        TextView calendarDateStatus = (TextView)findViewById(R.id.calendardate_status);
        TextView calendarDateDate = (TextView)findViewById(R.id.calendardate_date);
        TextView calendarDateWeekday = (TextView)findViewById(R.id.calendardate_weekday);
        calendarDateDate.setText(calendarDate.getDateString());
        calendarDateWeekday.setText(DateUtil.WEEKDAYS[calendarDate.getWeekday()]);

        CalendarAppointment test = CalendarAppointment.Builder
                .build(calendarDate, new CalendarTime(10, 0), "Ramazan", "Wir sind gut!");
        CalendarAppointment test2 = CalendarAppointment.Builder
                .build(calendarDate, new CalendarTime(8, 45), "Ramazan", "Wir sind gut!");
        calendarAppointments.add(test);
        calendarAppointments.add(test2);
        calendarAppointments = CalendarAppointment.Sorter.sort(calendarAppointments);

        if(calendarDate.hasAppointments()) {
            CalendarAppointmentListAdapter calendarAppointmentListAdapter =
                    new CalendarAppointmentListAdapter(this);
            calendarAppointmentList.setAdapter(calendarAppointmentListAdapter);
            calendarDateStatus.setText("Es ist/sind " + calendarAppointments.size() + " Termin(e) vorhanden!");
        } else  {
            calendarDateStatus.setText(R.string.no_appointments);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_calendardate_editor, menu);

        menu.getItem(0).setOnMenuItemClickListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        //TODO hier kommt der Code zum Hinzufügen eines Termins rein
        return true;
    }

    public CalendarDate getCalendarDate() {
        return calendarDate;
    }
}
