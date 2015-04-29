package de.szut.ita13.app.schulapp.calendar;

import java.util.ArrayList;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarWeek implements CalendarElement {

    private int weekNumber;
    private boolean oddWeek;

    private Calendar calendar;

    private ArrayList<CalendarDate> calendarDates;

    public CalendarWeek(Calendar calendar) {
        this.calendar = calendar;
        this.calendarDates = new ArrayList<CalendarDate>();
    }

    @Override
    public Object getItem(int index) {
        if(index >= 0 && index < calendarDates.size())
            return calendarDates.get(index);
        return null;
    }

    @Override
    public int getSize() {
        return calendarDates.size();
    }

    @Override
    public int getLayoutID(int index) {
        return CalendarElement.LAYOUT_IDS[index];
    }
}
