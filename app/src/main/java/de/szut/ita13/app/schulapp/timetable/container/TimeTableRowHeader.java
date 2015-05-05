package de.szut.ita13.app.schulapp.timetable.container;

/**
 * Created by Rene on 23.02.2015.
 * TimeTableRowHeader is one of the class which inherits from
 * TimeTableElement. It is also a little container class which
 * provides the header strings that display the weekday names.
 * Whatever is set as parameter in TimeTable constructor the strings
 * are in english or in german
 */
public class TimeTableRowHeader extends TimeTableElement {


    private String[] headers;
    private int now;

    public TimeTableRowHeader(String[] headers) {
        this.headers = headers;
    }

    public int getHeaderCount() {
        return headers.length;
    }

    public String getHeaderAt(int position) {
        return headers[position];
    }

    public void setHeaderAt(int position, String value) {
        if(position >= 0 && position < headers.length) {
            headers[position] = value;
        }
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }





}
