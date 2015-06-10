package de.szut.ita13.app.schulapp.calendar.container;

import java.util.HashMap;

/**
 * Created by Rene on 10.06.2015.
 */
public class CalendarMap extends HashMap<String, HashMap<String, HashMap<String, CalendarDate>>> {

    public CalendarMap() {

    }

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





}
