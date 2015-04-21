package de.szut.ita13.app.schulapp.utilities;

/**
 * Created by Rene on 13.02.2015.
 */
public class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes) throws InvalidTimeException {
        this.hours = hours;
        this.minutes = minutes;
        if(!TimeUtilities.isValidTime(this)) {
            throw new InvalidTimeException(InvalidException.INVALID_TIME);
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }


}
