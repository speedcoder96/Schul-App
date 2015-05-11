package de.szut.ita13.app.schulapp.calendar.container;

import java.util.ArrayList;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarAppointment {

    private CalendarDate calendarDate;
    private CalendarTime calendarTime;
    private String subject;
    private String note;


    public CalendarAppointment(CalendarDate calendarDate) {
        this.calendarDate = calendarDate;
    }

    public void setCalendarTime(CalendarTime calendarTime) {
        this.calendarTime = calendarTime;
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

    public CalendarTime getCalendarTime() {
        return calendarTime;
    }

    public String getSubject() {
        return subject;
    }

    public String getNote() {
        return note;
    }

    public static class Builder {

        public static CalendarAppointment build(CalendarDate cd, CalendarTime ct, String subject, String note) {
            CalendarAppointment appointment = new CalendarAppointment(cd);
            appointment.setCalendarTime(ct);
            appointment.setSubject(subject);
            appointment.setNote(note);
            return appointment;
        }

    }

    public static class Sorter {

        public static ArrayList<CalendarAppointment> sort(ArrayList<CalendarAppointment> calendarAppointments) {
            int max = calendarAppointments.size() - 1;
            while(max > 0) {
                for(int i = 0; i < max; i++) {
                    CalendarAppointment left = calendarAppointments.get(i);
                    CalendarAppointment right = calendarAppointments.get(i + 1);
                    if(right.getCalendarTime().isBefore(left.getCalendarTime())) {
                        calendarAppointments.set(i, right);
                        calendarAppointments.set(i + 1, left);
                    }
                }
                max--;
            }
            return calendarAppointments;
        }

    }


}
