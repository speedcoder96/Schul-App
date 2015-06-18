package de.szut.ita13.app.schulapp.calendar.container;

import android.app.PendingIntent;
import android.database.Cursor;

import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAppointmentListAdapter;
import de.szut.ita13.app.schulapp.calendar.dao.DatabaseHelper;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarAppointment {

    public static final int NOT_REGISTERED = -1;

    public static CalendarAppointmentListAdapter calendarAppointmentListAdapter;

    private CalendarDate calendarDate;
    private CalendarTime startTime;
    private CalendarTime endTime;
    private String subject;
    private String note;

    private boolean none;

    private long refID;


    public CalendarAppointment(CalendarDate calendarDate) {
        this.calendarDate = calendarDate;
        this.refID = -1;
    }

    public void setStartTime(CalendarTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime;
    }

    public long getRefID() {
        return refID;
    }

    public void setRefID(long refID) {
       this.refID = refID;
    }

    public void setEndTime(CalendarTime endTime) {
        this.endTime = endTime;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CalendarDate getCalendarDate() {
        return calendarDate;
    }

    public CalendarTime getStartTime() {
        return startTime;
    }
    public CalendarTime getEndTime() {
        return endTime;
    }

    public String getSubject() {
        return subject;
    }

    public String getNote() {
        return note;
    }

    public static CalendarAppointment toCalendarAppointment(CalendarDate calendarDate, Cursor cursor) {
        CalendarAppointment calendarAppointment = new CalendarAppointment(calendarDate);
        calendarAppointment.setRefID(Long.parseLong(
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Appointments.ID.name()))));
        calendarAppointment.setSubject(
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Appointments.TITLE.name())));
        calendarAppointment.setStartTime(new CalendarTime(
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Appointments.START.name()))));
        calendarAppointment.setEndTime(new CalendarTime(
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Appointments.END.name()))));
        calendarAppointment.setNote(
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Appointments.NOTE.name())));
        return calendarAppointment;
    }


}
