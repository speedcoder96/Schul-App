package de.szut.ita13.app.schulapp.calendar.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAppointmentListAdapter;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
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
    private AlertDialog alertDialog;

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

        calendarAppointmentListAdapter = new CalendarAppointmentListAdapter(this);

        if(calendarDate.hasAppointments()) {
            calendarAppointments = AppointmentUtil.Sorter.sort(calendarAppointments);
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

        for(int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setOnMenuItemClickListener(this);
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_new_appointment:
                CalendarAppointment calendarAppointment = new CalendarAppointment(calendarDate);
                calendarDate.addCalendarAppointment(calendarAppointment);
                gotoNextActivity(calendarAppointment);
                break;
            case R.id.action_remove_all_appointment:
                if(calendarDate.hasAppointments()) {
                    showConfirmDialog();
                }
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        gotoPreviousActivity();
        super.onBackPressed();
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

    private void showConfirmDialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Termine löschen");
        alertDialogBuilder.setMessage("Wollen Sie alle Termine löschen?");
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialogBuilder.setPositiveButton("Ja, alle löschen!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                alertDialog.cancel();
                Calendar.dataSource.open();
                Calendar.dataSource.deleteAll(calendarDate);
                Calendar.dataSource.close();
                calendarAppointmentListAdapter.notifyDataSetChanged();
                gotoPreviousActivity();
            }
        });
        alertDialogBuilder.setNegativeButton("Nein", null);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void gotoPreviousActivity() {
        Intent intent = new Intent(CalendarDateViewer.this.getApplicationContext(), CalendarActivity.class);
        Calendar.getCalendarActivity().startActivity(intent);
        finish();
    }

}
