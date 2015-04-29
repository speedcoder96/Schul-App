package de.szut.ita13.app.schulapp.calendar;

import java.util.ArrayList;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarDate {

    private int day;
    private int month;
    private int year;

    private ArrayList<CalendarAppointment> calendarAppointments;

    public CalendarDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

}
