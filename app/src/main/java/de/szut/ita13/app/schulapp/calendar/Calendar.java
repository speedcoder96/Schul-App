package de.szut.ita13.app.schulapp.calendar;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import de.szut.ita13.app.schulapp.R;

import static de.szut.ita13.app.schulapp.calendar.CalendarMonth.*;

/**
 * Created by Rene on 29.04.2015.
 */
public class Calendar {

    private static Activity calendarActivity;
    private ListView calendarView;
    private CalendarAdapter calendarAdapter;
    private CalendarMonth[] calendarMonths;

    public Calendar(Activity calendarActivity) {
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
            case PREVIOUS_MONTH:
                return calendarMonths[PREVIOUS_MONTH];
            case CURRENT_MONTH:
                return calendarMonths[CURRENT_MONTH];
            case NEXT_MONTH:
                return calendarMonths[NEXT_MONTH];
            default:
                return null;
        }
    }

}
