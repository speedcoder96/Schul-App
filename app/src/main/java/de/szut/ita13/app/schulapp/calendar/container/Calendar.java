package de.szut.ita13.app.schulapp.calendar.container;

import android.app.Activity;
import android.widget.ListView;

import java.util.HashMap;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAdapter;

/**
 * Created by Rene on 29.04.2015.
 */
public class Calendar {

    public static CalendarMap calendarMap;
    private static Activity calendarActivity;
    private ListView calendarView;
    private CalendarAdapter calendarAdapter;
    private CalendarMonth[] calendarMonths;

    public Calendar(Activity calendarActivity) {
        this.calendarMap = new CalendarMap();
        this.calendarActivity = calendarActivity;
        this.calendarActivity.setContentView(R.layout.activity_calendar);
        this.calendarView = (ListView) calendarActivity.findViewById(R.id.calendar);
        this.calendarMonths = CalendarMonth.generateDefaultMonths(this, 5, 2015);
        this.calendarAdapter = new CalendarAdapter(calendarActivity, this);
        this.calendarView.setAdapter(calendarAdapter);
    }

    public static Activity getCalendarActivity() {
        return calendarActivity;
    }

    public CalendarMonth[] getCalendarMonths() {
        return calendarMonths;
    }

    public CalendarMonth getCalendarMonth(int pointer) {
        switch(pointer) {
            case CalendarMonth.PREVIOUS_MONTH:
                return calendarMonths[CalendarMonth.PREVIOUS_MONTH];
            case CalendarMonth.CURRENT_MONTH:
                return calendarMonths[CalendarMonth.CURRENT_MONTH];
            case CalendarMonth.NEXT_MONTH:
                return calendarMonths[CalendarMonth.NEXT_MONTH];
            default:
                return null;
        }
    }

}
