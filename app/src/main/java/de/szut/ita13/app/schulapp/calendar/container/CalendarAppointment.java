package de.szut.ita13.app.schulapp.calendar.container;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarAppointment implements Serializable {

    public static String SERIALIZABLE_KEY = "appointment";

    private CalendarDate calendarDate;
    private CalendarTime startTime;
    private CalendarTime endTime;
    private String subject;
    private String note;


    public CalendarAppointment(CalendarDate calendarDate) {
        this.calendarDate = calendarDate;
    }

    public void setStartTime(CalendarTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime;
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
