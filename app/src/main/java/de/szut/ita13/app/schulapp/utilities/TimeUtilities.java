package de.szut.ita13.app.schulapp.utilities;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.szut.ita13.app.schulapp.timetable.container.TimeTable;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableColumn;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableElement;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableRow;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableSubject;


/**
 * Created by Rene on 13.02.2015.
 * The TimeUtilities class comes up with some helper methods to calculate
 * some of time related problems, building up time strings and checking
 * a time string or time object for their validity
 */
public class TimeUtilities {

    /**
     * Tag that identifies logs of this class
     */
    public static final String TAG = TimeUtilities.class.getSimpleName();

    /**
     * the value of hours per day
     */
    public static final int HOURS_PER_DAY = 24;
    /**
     * the value of minutes per hour
     */
    public static final int MINUTES_PER_HOUR = 60;
    /**
     * the value of seconds per minute
     */
    public static final int SECONDS_PER_MINUTE = 60;

    /**
     * the value of milliseconds per second
     */
    public static final int MILLISECOND_PER_SECOND = 1000;

    /**
     * the value of minutes in a day
     */
    public static final int DAY_IN_MINUTES = MINUTES_PER_HOUR * HOURS_PER_DAY;

    /**
     * the separator that separates hours and minutes
     */
    public static final String SEPARATOR_BETWEEN_HOURS_AND_MINUTES = ":";

    /**
     * represents the format that the time stamp is in
     */
    public static final String TIME_STAMP_FORMAT = "HH:mm";

    public static final String TIME_STAMP_FORMAT_SECONDS = "HH:mm:ss";

    /**
     * represents the additional character
     */
    public static final String ADDITIONAL_MISSING_CHARACTER = "0";

    /**
     * the length of a time string in the following format [HH:MM]
     */
    public static final int TIME_STRING_LENGTH = 5;

    public static final int TIME_STRING_SECONDS_LENGTH = 8;

    /**
     * an enum of time units
     */
    public static enum TimeUnit {
        HOURS, MINUTES, SECONDS;
    }

    /**
     * Build a time object with hours and minutes out of the totalMinutes which is passed in
     * @param totalMinutes the total minutes
     * @return returns totalMinutes in hours and minutes
     */
    public static Time getTime(int totalMinutes) throws InvalidTimeException {
        int hours = totalMinutes / MINUTES_PER_HOUR;
        int minutes = totalMinutes % MINUTES_PER_HOUR;
        return new Time(hours, minutes);
    }

    /**
     * Build a time object with hours and minutes out of the time string which is passed in
     * @param timeString a time string in the following format [HH:MM]
     * @return returns a time object with hours and minutes if it isn't a valid time string
     * the result would be null
     */
    public static Time getTime(String timeString) throws InvalidTimeException {
        if(isValidTimeString(timeString)) {
            String[] values = timeString.split(SEPARATOR_BETWEEN_HOURS_AND_MINUTES);
            int hours = Integer.parseInt(values[0]);
            int minutes = Integer.parseInt(values[1]);
            return new Time(hours, minutes);
        }
        return null;
    }

    /**
     * Returns a time string which is built out of the time object
     * @param time the time object that provides hours and minutes
     * @return returns the string in the following format [HH:MM]
     */
    public static String getTimeString(Time time) {
        StringBuilder builder = new StringBuilder();
        int hours = time.getHours();
        int minutes = time.getMinutes();
        builder.append(fillMissingCharacter(hours));
        builder.append(SEPARATOR_BETWEEN_HOURS_AND_MINUTES);
        builder.append(fillMissingCharacter(minutes));
        return builder.toString();
    }

    /**
     * Checks if a time string is valid
     * @param timeString a time string with hours and minutes separated by ":"
     * @return returns true if it's a valid time string and false if it's not
     */
    public static boolean isValidTimeString(String timeString) {
        if(timeString.length() == TIME_STRING_LENGTH || timeString.length() == TIME_STRING_LENGTH - 1) {
            if(timeString.length() == TIME_STRING_LENGTH - 1) {
                timeString = ADDITIONAL_MISSING_CHARACTER + timeString;
            }
            if(timeString.contains(SEPARATOR_BETWEEN_HOURS_AND_MINUTES)) {
                String[] values = timeString.split(SEPARATOR_BETWEEN_HOURS_AND_MINUTES);
                try {
                    int hours = Integer.parseInt(values[0]);
                    int minutes = Integer.parseInt(values[1]);
                    if(isInRange(hours, TimeUnit.HOURS) && isInRange(minutes, TimeUnit.MINUTES)) {
                        return true;
                    }
                } catch(NumberFormatException e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * Checks if a time object is valid
     * @param time the time object that provides hours and minutes
     * @return returns true if it's a valid time and false if it's not
     */
    public static boolean isValidTime(Time time) {
        int hours = time.getHours();
        int minutes = time.getMinutes();
        return (hours >= 0 && hours < HOURS_PER_DAY) && (minutes >= 0 && minutes < MINUTES_PER_HOUR);
    }

    /**
     * Checks if the hours and minutes are valid
     * @param hours the hours of the time
     * @param minutes the minutes of the time
     * @return returns true if the hours and minutes are valid and false if they aren't
     */
    public static boolean isValidTime(int hours, int minutes) {
        return (hours >= 0 && hours < HOURS_PER_DAY) && (minutes >= 0 && minutes < MINUTES_PER_HOUR);
    }

    /**
     * Checks if a value of a certain unit is in the defined range
     * @param value a value that represents one of the time units
     * @param unit specifies the time unit of the value
     * @return returns true if the value is in the range of the specified unit
     */
    public static boolean isInRange(int value, TimeUnit unit) {
        switch(unit) {
            case HOURS:
                return (value >= 0 && value < HOURS_PER_DAY);
            case MINUTES:
                return (value >= 0 && value < MINUTES_PER_HOUR);
            case SECONDS:
                return (value >= 0 && value < SECONDS_PER_MINUTE);
            default:
                return false;
        }
    }

    /**
     * Creates a time object based on the given start time plus the minutes of an interval times
     * the index of the actual hour including the interval of breaks
     * @param startTime is the time object that is set as start time (index = 0)
     * @param interval is given in minutes, is the length of an hour
     * @param next is the index of the calculated time object
     * @param breakInterval is the interval of the breaks
     * @param breakLength is given in minutes, is the length of a break
     * @return returns the next time object based on the parameters
     * @throws InvalidTimeException throws InvalidTimeException if the time object isn't valid
     */
    public static Time getNextTimeIncludingBreak(Time startTime, int interval, int next, int breakInterval, int breakLength) throws InvalidTimeException {
        int totalMinutes = totalMinutesOf(startTime) + (interval * (next)) + (breakLength * (next / breakInterval));
        return getTime(totalMinutes);
    }

    /**
     * Creates a time object of the actual time in hours and minutes
     * @return returns the actual time stamp
     */
    public static Time getActualTimeStamp() {
        try {
            String timeString = new SimpleDateFormat(TIME_STAMP_FORMAT)
                    .format(new Date()).substring(0, TIME_STRING_LENGTH);
            return getTime(timeString);
        } catch (InvalidTimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a time object of the actual time in hours, minutes and seconds
     * @return returns the actual time stamp
     */
    public static Time getActualTimeStringIncludingSeconds() {
        String timeString = new SimpleDateFormat(TIME_STAMP_FORMAT_SECONDS)
                .format(new Date()).substring(0, TIME_STRING_SECONDS_LENGTH);
        String normalTimeString = new SimpleDateFormat(TIME_STAMP_FORMAT)
                .format(new Date()).substring(0, TIME_STRING_LENGTH);
        try {
            Time time = getTime(normalTimeString);
            time.setSeconds(Integer.parseInt(timeString.substring(6, timeString.length())));
            return time;
        } catch (InvalidTimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Calculates the time difference in minutes between the actual time and
     * a certain time that is in future. If the second parameter is less than
     * the first one the result would be -1
     * @param actual the actual time stamp
     * @param next a next certain time
     * @return returns the difference in minutes
     */
    public static long getDifferenceMinutes(Time actual, Time next) {
        if(actual == null || next == null) return -1;
        int actualTotalMinutes = totalMinutesOf(actual);
        int nextTotalMinutes = totalMinutesOf(next);
        if(actualTotalMinutes > nextTotalMinutes) {
            return -1;
        }
        return nextTotalMinutes - actualTotalMinutes;
    }

    public static long getDifferenceMillisecondsToNextDay(Time next) {
        long timeNextMilliseconds = totalMillisecondsOf(next);
        long dayMilliseconds = totalMillisecondsOfADay();
        long actualMilliseconds = totalMillisecondsOf(getActualTimeStamp());
        return (timeNextMilliseconds + dayMilliseconds) - actualMilliseconds;
    }

    /**
     *
     * @param actual
     * @param next
     * @return
     */
    public static long getDifferenceMilliseconds(Time actual, Time next) {
        if(actual == null || next == null) return -1;
        long actualMilliSeconds = totalMillisecondsOf(actual);
        long nextMilliSeconds = totalMillisecondsOf(next);
        if(actualMilliSeconds > nextMilliSeconds) {
            return -1;
        }
        return nextMilliSeconds - actualMilliSeconds;
    }

    /**
     * Sets the now value to true for the row that hour is currently running and
     * false for the row that isn't running
     * @param timeTableElements contains all the elements of the time table
     * @param position the current hour position in the time table
     */
    public static void isNow(ArrayList<TimeTableElement> timeTableElements, int position) {
        int totalMinutesActual = totalMinutesOf(getActualTimeStamp());
        if(position < timeTableElements.size()) {
            TimeTableRow rowNow = (TimeTableRow) timeTableElements.get(position);
            int totalMinutesNow = totalMinutesOf(rowNow.getTime());
            int totalMinutesNext = totalMinutesOf(rowNow.getNextTime());
            if(totalMinutesNow <= totalMinutesActual && totalMinutesActual < totalMinutesNext) {
                rowNow.setNow(true);
            } else {
                rowNow.setNow(false);
            }
        }
    }

    /**
     * Retrieves the next time object which is followed by the actual selected time
     * @param timeTableElements contains all the elements of the time table
     * @return returns the next time object
     */
    public static Time getNextUpdateTime(ArrayList<TimeTableElement> timeTableElements) {
        Time resultTime = null;
        for(int i = 1; i < timeTableElements.size(); i++) {
            TimeTableRow timeTableRow = (TimeTableRow) timeTableElements.get(i);
            if(timeTableRow.isNow()) {
               resultTime = timeTableRow.getNextTime();
               break;
            }
        }
        return resultTime;
    }

    /**
     * Fills the string with the missing 0 if the value is under 10
     * @param value the value in hours or minutes
     * @return a string with the missing character if the value is less than 10
     */
    private static String fillMissingCharacter(int value) {
        return (value < 10) ? ADDITIONAL_MISSING_CHARACTER + String.valueOf(value) : String.valueOf(value);
    }

    /**
     * Calculates the total minutes out of the given hours and minutes
     * @param hours the hours
     * @param minutes the minutes
     * @return returns the total minutes calculated by the hours and minutes
     */
    public static int totalMinutesOf(int hours, int minutes) {
        return hours * MINUTES_PER_HOUR + minutes;
    }

    /**
     * Calculates the total minutes out of the given time object
     * @param time the time object that provides hours and minutes
     * @return returns the total minutes calculated by hours and minutes
     */
    public static int totalMinutesOf(Time time) {
        return time.getHours() * MINUTES_PER_HOUR + time.getMinutes();
    }

    /**
     * Calculates the total milliseconds out of the given time object
     * @param time the time object that provides hours, minutes and seconds
     * @return returns the total minutes calculated by hours, minutes and seconds
     */
    public static long totalMillisecondsOf(Time time) {
        return (((time.getHours() * MINUTES_PER_HOUR + time.getMinutes()) * SECONDS_PER_MINUTE) + time.getSeconds()) * MILLISECOND_PER_SECOND;
    }

    public static long totalMillisecondsOfADay() {
        return DAY_IN_MINUTES * SECONDS_PER_MINUTE * MILLISECOND_PER_SECOND;
    }

}
