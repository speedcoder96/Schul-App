package de.szut.ita13.app.schulapp.newutils;

import java.io.Serializable;
import java.util.ArrayList;

import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;

/**
 * Created by rama on 17.05.2015.
 */
public class AppointmentUtil {

    public static class Builder {


        public static CalendarAppointment build(CalendarDate cd, CalendarTime ct, String subject, String note) {
            CalendarAppointment appointment = new CalendarAppointment(cd);
            appointment.setStartTime(ct);
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
                    if(right.getStartTime().isBefore(left.getStartTime())) {
                        calendarAppointments.set(i, right);
                        calendarAppointments.set(i + 1, left);
                    }
                }
                max--;
            }
            return calendarAppointments;
        }

    }

    public static class Parser {

        public static CalendarDate getCalendarDate(String calendarDateString) {
            if(calendarDateString.contains("-")) {
                String[] calendarDateParts = calendarDateString.split("-");
                if(calendarDateParts.length == 3) {
                    int year = Integer.parseInt(calendarDateParts[0]);
                    int month = Integer.parseInt(calendarDateParts[1]);
                    int day  = Integer.parseInt(calendarDateParts[2]);
                    int[] weekDayIndex = DateUtil.getWeekdayIndex(day, month, year);
                    boolean isActual = DateUtil.isActualDate(DateUtil.getActualDate(), day, month, year);
                    CalendarDate date = new CalendarDate(day, month, year, weekDayIndex[DateUtil.WEEKDAY_INDEX], isActual);
                    return date;
                }
            }
            return null;
        }


    }
}
