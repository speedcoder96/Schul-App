package de.szut.ita13.app.schulapp.calendar.container;

import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAppointmentListAdapter;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarAppointment {

    public static String SERIALIZABLE_KEY = "appointment";
    public static String NONE = "appointment_none";

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




}
