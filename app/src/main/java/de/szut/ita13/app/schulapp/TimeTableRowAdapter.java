package de.szut.ita13.app.schulapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.utilities.InvalidTimeException;
import de.szut.ita13.app.schulapp.utilities.TimeUtilities;

/**
 * Created by Rene on 23.02.2015.
 */
public class TimeTableRowAdapter extends BaseAdapter {

    private TimeTable timeTable;
    private ArrayList<TimeTableElement> timeTableElements;
    private final LayoutInflater layoutInflater;

    public TimeTableRowAdapter(Context context, TimeTable timeTable) {
        this.timeTable = timeTable;
        this.timeTableElements = timeTable.getTimeTableElements();
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return timeTableElements.size();
    }

    @Override
    public Object getItem(int position) {
        return timeTableElements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        TimeTableElement timeTableElement = timeTableElements.get(position);
        if(timeTableElement instanceof TimeTableRowHeader) {
            view = layoutInflater.inflate(R.layout.timetable_header_layout, parent, false);
            TimeTableRowHeader timeTableRowHeader = (TimeTableRowHeader) timeTableElement;
            for (int i = 0; i < timeTableRowHeader.getHeaderCount(); i++) {
                TextView textView = (TextView) view.findViewById(timeTableRowHeader.getLayoutId(i));
                textView.setText(timeTableRowHeader.getHeaderAt(i));
            }
        } else {
            view = layoutInflater.inflate(R.layout.timetable_row_layout, parent, false);
            TimeTableRow timeTableRow = (TimeTableRow) timeTableElement;
            TextView textView = (TextView) view.findViewById(timeTableRow.getLayoutId(0));
            textView.setText(timeTableRow.getHour() + "\n" + TimeUtilities.getTimeString(timeTableRow.getTime()));
            if(timeTableRow.isNow()) {
                textView.setTypeface(null, Typeface.BOLD);
                textView.setBackgroundColor(TimeTable.getActivityInstance().getResources().getColor(
                        timeTable.getTimeIndicatorColors(TimeTable.TIME_SELECTED)));
            } else {
                textView.setBackgroundColor(TimeTable.getActivityInstance().getResources().getColor(
                        timeTable.getTimeIndicatorColors(TimeTable.TIME_UNSELECTED)));
                textView.setTypeface(null, Typeface.NORMAL);
            }
            for (int i = 1; i < timeTableRow.getLayoutIdCount(); i++) {
                textView = (TextView) view.findViewById(timeTableRow.getLayoutId(i));
                TimeTableSubject timeTableSubject = timeTableRow.getSubjectAt(i - 1);
                timeTableSubject.setTime(timeTableRow.getTime());
                try {
                    timeTableSubject.setNextTime(TimeUtilities.getTime(
                            TimeUtilities.totalMinutesOf(timeTableRow.getTime()) +
                                    timeTable.getTimeTableModel().getLessonLength()));
                } catch (InvalidTimeException e) {
                    e.printStackTrace();
                }
                textView.setTag(timeTableSubject);
                if(timeTableSubject.isValidSubject())  {
                    textView.setBackgroundColor(timeTableSubject.getColor());
                    textView.setClickable(true);
                    textView.setText(timeTableSubject.getSubjectName() + "\n" + timeTableSubject.getRoom());
                } else {
                    textView.setClickable(false);
                    textView.setBackgroundColor(timeTableSubject.getColor());
                }
            }
        }
        return view;
    }
}
