package de.szut.ita13.app.schulapp.calendar;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarHeader implements CalendarElement {

    public static final String[] WEEKDAYS = {
           "So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"
    };

    @Override
    public Object getItem(int index) {
        if(index >= 0 && index < WEEKDAYS.length)
            return WEEKDAYS[index];
        return null;
    }

    @Override
    public int getSize() {
        return WEEKDAYS.length;
    }

    @Override
    public int getLayoutID(int index) {
        return CalendarElement.LAYOUT_IDS[index];
    }
}
