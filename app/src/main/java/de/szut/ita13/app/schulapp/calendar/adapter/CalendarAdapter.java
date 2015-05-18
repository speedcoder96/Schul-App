package de.szut.ita13.app.schulapp.calendar.adapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarElement;
import de.szut.ita13.app.schulapp.calendar.container.CalendarHeader;
import de.szut.ita13.app.schulapp.calendar.container.CalendarMonth;
import de.szut.ita13.app.schulapp.calendar.container.CalendarWeek;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarAdapter extends BaseAdapter {

    private Calendar calendar;
    private Activity calendarActivity;
    private CalendarMonth currentMonth;
    private ArrayList<CalendarElement> currentMonthElements;
    private final LayoutInflater calendarLayoutInflater;

    public CalendarAdapter(Context context, Calendar calendar) {
        this.calendar = calendar;
        this.calendarActivity = calendar.getCalendarActivity();
        this.currentMonth = calendar.getCalendarMonth(CalendarMonth.CURRENT_MONTH);
        this.currentMonthElements = currentMonth.getCalendarElements();
        this.calendarLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return currentMonthElements.size();
    }

    @Override
    public Object getItem(int position) {
        return currentMonthElements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CalendarElement calendarElement = currentMonthElements.get(position);
        if(calendarElement instanceof CalendarHeader) {
            view = calendarLayoutInflater.inflate(R.layout.calendar_header_layout, parent, false);
            view.setBackgroundColor(calendarActivity.getResources().getColor(R.color.orange));
            CalendarHeader header = (CalendarHeader) calendarElement;
            for(int i = 0; i < header.getSize(); i++) {
                TextView textView = (TextView) view.findViewById(header.getLayoutID(i));
                String weekday = String.valueOf(header.getItem(i));
                textView.setText(weekday);
                if(i == 0) {
                    textView.setTextColor(calendarActivity.getResources().getColor(R.color.red));
                }
            }
        } else if(calendarElement instanceof CalendarWeek) {
            view = calendarLayoutInflater.inflate(R.layout.calendar_week_layout, parent, false);
            CalendarWeek week = (CalendarWeek) calendarElement;
            TextView weekTextView = (TextView)view.findViewById(R.id.week);
            weekTextView.setText(String.valueOf(week.getWeekNumber()));
            for(int i = 0; i < week.getSize(); i++) {
                TextView textView = (TextView) view.findViewById(week.getLayoutID(i));
                CalendarDate calendarDate = (CalendarDate) week.getItem(i);
                textView.setOnClickListener(calendarDate);
                if(!calendarDate.isNone())
                    textView.setText(String.valueOf(calendarDate.getDay()));
                if(calendarDate.isActualDate()) {
                    textView.setBackgroundColor(calendarActivity.getResources().getColor(R.color.yellow));
                }
            }
        }
        return view;
    }
}
