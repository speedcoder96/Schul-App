package de.szut.ita13.app.schulapp.calendar.container;

import java.io.Serializable;

/**
 * Created by Rene on 11.05.2015.
 */
public class CalendarTime implements Serializable {

    public static final int MINUTES_PER_HOURS = 60;

    private int hours;
    private int minutes;

    public CalendarTime(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean isBefore(CalendarTime calendarTime) {
        int totalMinutes = (hours * MINUTES_PER_HOURS) + minutes;
        int otherTotalMinutes = (calendarTime.getHours() * MINUTES_PER_HOURS) + calendarTime.getMinutes();
        return otherTotalMinutes > totalMinutes;
    }

    public boolean isAfter(CalendarTime calendarTime) {
        int totalMinutes = (hours * MINUTES_PER_HOURS) + minutes;
        int otherTotalMinutes = (calendarTime.getHours() * MINUTES_PER_HOURS) + calendarTime.getMinutes();
        return otherTotalMinutes < totalMinutes;
    }

    public String getTimeString() {
        String hourStr = (hours < 10) ? "0" + hours : String.valueOf(hours);
        String minuteStr = (minutes < 10) ? "0" + minutes : String.valueOf(minutes);
        return hourStr + ":" + minuteStr;
    }

}
