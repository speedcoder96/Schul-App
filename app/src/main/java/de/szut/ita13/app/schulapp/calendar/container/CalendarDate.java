package de.szut.ita13.app.schulapp.calendar.container;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarDate implements View.OnClickListener {

    public static boolean NONE = true;

    private int day;
    private int month;
    private int year;
    private boolean none;

    private ArrayList<CalendarAppointment> calendarAppointments;

    public CalendarDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        calendarAppointments = new ArrayList<CalendarAppointment>();
        none = false;
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

    public ArrayList<CalendarAppointment> getCalendarAppointments() {
        return calendarAppointments;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), String.valueOf(day + "." + month + "." + year), Toast.LENGTH_LONG).show();
        v.setBackgroundColor(Calendar.getCalendarActivity().getResources().getColor(R.color.red));
    }

}
