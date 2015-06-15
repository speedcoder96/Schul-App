package de.szut.ita13.app.schulapp.calendar.container;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import de.szut.ita13.app.schulapp.calendar.views.CalendarDateViewer;
import de.szut.ita13.app.schulapp.newutils.AppointmentUtil;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarDate implements View.OnClickListener {

    public static final int DATABASE_DATE_FORMAT = 0;
    public static final int DEFAULT_DATE_FORMAT = 1;
    public static final String DATE_FORMAT = "date_format";

    public static boolean NONE = true;

    private int day;
    private int month;
    private int year;
    private int weekday;
    private boolean none;
    private boolean actualDate;

    private ArrayList<CalendarAppointment> calendarAppointments;

    public CalendarDate(int day, int month, int year, int weekday, boolean actualDate) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.weekday = weekday;
        calendarAppointments = new ArrayList<>();
        this.actualDate = actualDate;
        none = false;
    }

    public void addCalendarAppointment(CalendarAppointment calendarAppointment) {
        calendarAppointments.add(calendarAppointment);
    }

    public CalendarDate(boolean none) {
        this.none = none;
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

    public String getDateString(int format) {
        if(format == DATABASE_DATE_FORMAT) {
            String dayStr = (day < 10) ? "0" + day : String.valueOf(day);
            String monthStr = (month < 10) ? "0" + month : String.valueOf(month);
            return year + "-" + monthStr + "-" + dayStr;
        } else {
            String dayStr = (day < 10) ? "0" + day : String.valueOf(day);
            String monthStr = (month < 10) ? "0" + month : String.valueOf(month);
            return dayStr + "." + monthStr + "." + year;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Calendar.getCalendarActivity(), CalendarDateViewer.class);
        intent.putExtra(DATE_FORMAT, getDateString(DATABASE_DATE_FORMAT));
        Calendar.getCalendarActivity().startActivity(intent);
    }

    public static int getActualDateIndex(CalendarWeek calendarWeek) {
        for(int i = 0; i < calendarWeek.getSize(); i++) {
            CalendarDate calendarDate = (CalendarDate) calendarWeek.getItem(i);
            if(calendarDate.isActualDate()) {
                return i;
            }
        }
        return -1;
    }



}
