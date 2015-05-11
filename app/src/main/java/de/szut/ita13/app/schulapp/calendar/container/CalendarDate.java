package de.szut.ita13.app.schulapp.calendar.container;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.views.CalendarDateEditor;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarDate implements View.OnClickListener, Serializable {

    public static String SERIALIZABLE_KEY = "calendardate";

    public static boolean NONE = true;

    private int day;
    private int month;
    private int year;
    private boolean none;
    private boolean actualDate;

    private ArrayList<CalendarAppointment> calendarAppointments;

    public CalendarDate(int day, int month, int year, boolean actualDate) {
        this.day = day;
        this.month = month;
        this.year = year;
        calendarAppointments = new ArrayList<CalendarAppointment>();
        none = false;
        this.actualDate = actualDate;
    }

    public CalendarDate(boolean none) {
        this.none = none;
    }

    public void addCalendarAppointment(CalendarAppointment appointment) {
        calendarAppointments.add(appointment);
    }

    public void removeCalendarAppointment(CalendarAppointment appointment) {
        calendarAppointments.remove(appointment);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isNone() {
        return none;
    }

    public boolean isActualDate() {
        return actualDate;
    }

    public ArrayList<CalendarAppointment> getCalendarAppointments() {
        return calendarAppointments;
    }

    public boolean hasAppointments() {
        return calendarAppointments.size() != 0;
    }

    @Override
    public void onClick(View v) {
        CalendarDateEditor editor = new CalendarDateEditor();
        Intent intent = new Intent(Calendar.getCalendarActivity(), CalendarDateEditor.class);
        intent.putExtra(SERIALIZABLE_KEY, this);
        Calendar.getCalendarActivity().startActivity(intent);
    }

}
