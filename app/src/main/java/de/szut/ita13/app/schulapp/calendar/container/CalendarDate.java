package de.szut.ita13.app.schulapp.calendar.container;

import android.content.Intent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import de.szut.ita13.app.schulapp.calendar.views.CalendarDateViewer;
import de.szut.ita13.app.schulapp.newutils.AppointmentUtil;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarDate implements View.OnClickListener, Serializable {

    public static String SERIALIZABLE_KEY = "calendardate";

    public static boolean NONE = true;

    private int day;
    private int month;
    private int year;
    private int weekday;
    private boolean none;
    private boolean actualDate;

    private long refID;

    private ArrayList<CalendarAppointment> calendarAppointments;

    public CalendarDate(int day, int month, int year, int weekday, boolean actualDate) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.weekday = weekday;
        calendarAppointments = new ArrayList<CalendarAppointment>();
        this.actualDate = actualDate;
        none = false;
        refID = Long.parseLong(day + "" + month + "" + year);

        CalendarAppointment test = AppointmentUtil.Builder
                .build(this, new CalendarTime(10, 0), "Ramazan", "Wir sind gut!");
        CalendarAppointment test2 = AppointmentUtil.Builder
                .build(this, new CalendarTime(8, 45), "Ramazan", "Wir sind gut!");
        calendarAppointments.add(test);
        calendarAppointments.add(test2);
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

    public int getWeekday() {
        return weekday;
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

    public String getDateString() {
        String dayStr = (day < 10) ? "0" + day : String.valueOf(day);
        String monthStr = (month < 10) ? "0" + month : String.valueOf(month);
        return dayStr + "." + monthStr + "." + year;
    }

    public void setRefID(long refID) {
        this.refID = refID;
    }

    public long getRefID() {
        return refID;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Calendar.getCalendarActivity(), CalendarDateViewer.class);
        intent.putExtra(SERIALIZABLE_KEY, this);
        Calendar.getCalendarActivity().startActivity(intent);
    }




}
