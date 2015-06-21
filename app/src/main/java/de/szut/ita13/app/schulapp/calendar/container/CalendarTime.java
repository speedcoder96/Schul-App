package de.szut.ita13.app.schulapp.calendar.container;

/**
 * Created by Rene on 11.05.2015.
 */
public class CalendarTime {

    public static final int MINUTES_PER_HOURS = 60;

    private int hours;
    private int minutes;

    public CalendarTime(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public CalendarTime(int minutes){
        this.hours = minutes / MINUTES_PER_HOURS;
        this.minutes = minutes % MINUTES_PER_HOURS;
    }

    public CalendarTime(String timeString) {
        if(timeString.length() == 5) {
            this.hours = Integer.parseInt(timeString.split(":")[0]);
            this.minutes = Integer.parseInt(timeString.split(":")[1]);
        }
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

    public int totalMinutes(){
        return (hours * MINUTES_PER_HOURS) + minutes;
    }

}
