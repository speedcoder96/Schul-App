package de.szut.ita13.app.schulapp.calendar;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Rene on 29.04.2015.
 */
public class Calendar {

    private Activity calendarActivity;
    private ListView calendarView;
    private CalendarAdapter calendarAdapter;
    private ArrayList<CalendarElement> calendarElements;

    public Calendar(Activity calendarActivity) {
        this.calendarActivity = calendarActivity;
        this.calendarActivity.setContentView(R.layout.activity_calendar);
        this.calendarView = (ListView) calendarActivity.findViewById(R.id.calendar);
        this.calendarElements = new ArrayList<CalendarElement>();
        this.calendarAdapter = new CalendarAdapter(calendarActivity, this);
    }

    public Activity getCalendarActivity() {
        return calendarActivity;
    }

    public ArrayList<CalendarElement> getElements() {
        return calendarElements;
    }

}
