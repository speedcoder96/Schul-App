package de.szut.ita13.app.schulapp.calendar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarAdapter extends BaseAdapter {

    private Calendar calendar;
    private Activity calendarActivity;
    private ArrayList<CalendarElement> calendarElements;
    private final LayoutInflater calendarLayoutInflater;

    public CalendarAdapter(Context context, Calendar calendar) {
        this.calendar = calendar;
        this.calendarActivity = calendar.getCalendarActivity();
        this.calendarLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.calendarElements = calendar.getElements();
    }

    @Override
    public int getCount() {
        return calendarElements.size();
    }

    @Override
    public Object getItem(int position) {
        return calendarElements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CalendarElement calendarElement = calendarElements.get(position);
        if(calendarElement instanceof CalendarHeader) {
            view = calendarLayoutInflater.inflate(R.layout.calendar_header_layout, parent, false);
            view.setBackgroundColor(calendarActivity.getResources().getColor(R.color.orange));
        } else if(calendarElement instanceof CalendarWeek) {
            view = calendarLayoutInflater.inflate(R.layout.calendar_week_layout, parent, false);
            view.setBackgroundColor(calendarActivity.getResources().getColor(R.color.red));
        }
        return view;
    }
}
