package de.szut.ita13.app.schulapp.utilities;

/**
 * Created by Rene on 14.02.2015.
 */
public class Date {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) throws InvalidDateException {
        this.day = day;
        this.month = month;
        this.year = year;
        if(!DateUtilities.isValidDate(this)) {
            throw new InvalidDateException(InvalidDateException.INVALID_DATE);
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

}
