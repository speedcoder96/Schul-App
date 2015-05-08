package de.szut.ita13.app.schulapp.calendar.container;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarHeader implements CalendarElement {

    public static final String[] WEEKDAYS = {
           "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"
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
        return LAYOUT_IDS[index];
    }
}
