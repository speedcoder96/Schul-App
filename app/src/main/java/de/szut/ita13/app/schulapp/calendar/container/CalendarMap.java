package de.szut.ita13.app.schulapp.calendar.container;


import java.util.ArrayList;
import java.util.HashMap;

import de.szut.ita13.app.schulapp.calendar.dao.CalendarDataSource;

/**
 * Created by Rene on 10.06.2015.
 */
public class CalendarMap extends HashMap<String, HashMap<String, HashMap<String, CalendarDate>>> {

    public CalendarDate getCalendarDate(String yearString, String monthString, String dayString) {
        if(containsKey(yearString)) {
            if(get(yearString).containsKey(monthString)) {
                if(get(yearString).get(monthString).containsKey(dayString)) {
                    return this.get(yearString).get(monthString).get(dayString);
                }
            }
        }
        return null;
    }

    public CalendarDate getCalendarDate(String dateString) {
        String[] dateStringParts = dateString.split("-");
        String yearString = dateStringParts[0];
        String monthString = dateStringParts[1];
        String dayString = dateStringParts[2];
        return getCalendarDate(yearString, monthString, dayString);
    }

    public void putCalendarDate(CalendarDate calendarDate, String yearString, String monthString, String dayString) {
        if(!containsKey(yearString)) {
            this.put(yearString, new HashMap<String, HashMap<String, CalendarDate>>());
        }
        if(!get(yearString).containsKey(monthString)) {
            this.get(yearString).put(monthString, new HashMap<String, CalendarDate>());
        }
        this.get(yearString).get(monthString).put(dayString, calendarDate);
    }

    public void putCalendarDate(CalendarDate calendarDate, String dateString) {
        String[] dateStringParts = dateString.split("-");
        String yearString = dateStringParts[0];
        String monthString = dateStringParts[1];
        String dayString = dateStringParts[2];
        putCalendarDate(calendarDate, yearString, monthString, dayString);
    }

    public void saveToDatabase(CalendarDataSource dataSource) {
        dataSource.open();
        for(Entry<String, HashMap<String, HashMap<String, CalendarDate>>> calendarYear : this.entrySet()) {
            for(Entry<String, HashMap<String, CalendarDate>> calendarMonth : calendarYear.getValue().entrySet()) {
                for(Entry<String, CalendarDate> calendarDay : calendarMonth.getValue().entrySet()) {
                    CalendarDate calendarDate = calendarDay.getValue();
                    if(calendarDate.hasAppointments()) {
                        ArrayList<CalendarAppointment> appointments = calendarDate.getCalendarAppointments();
                        for(CalendarAppointment appointment : appointments) {
                            if(appointment.getRefID() == CalendarAppointment.NOT_REGISTERED) {
                                dataSource.insertAppointment(appointment);
                            } else {
                                dataSource.updateAppointment(appointment);
                            }
                        }
                    }
                }
            }
        }
        dataSource.close();
    }





}
