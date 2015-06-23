package de.szut.ita13.app.schulapp.timetable;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;

/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTableRowItem {

    public final static int[] IDS = {
            R.id.time, R.id.monday, R.id.tuesday, R.id.wednesday, R.id.thursday, R.id.friday
    };
    public final static String[] LABELS = {
            "Zeit", "Mo", "Di", "Mi", "Do", "Fr"
    };

    private ArrayList<TimeTableItem> items;
    private TimeTable timeTable;
    private CalendarTime startTime;
    private CalendarTime endTime;
    private boolean header;
    private boolean now;

    public TimeTableRowItem(TimeTable timeTable){
        this.timeTable = timeTable;
        items = new ArrayList<>();
    }

    public ArrayList<TimeTableItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<TimeTableItem> items) {
        this.items = items;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public CalendarTime getStartTime() {
        return startTime;
    }

    public void setStartTime(CalendarTime startTime) {
        this.startTime = startTime;
    }

    public CalendarTime getEndTime() {
        return endTime;
    }

    public void setEndTime(CalendarTime endTime) {
        this.endTime = endTime;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public boolean isNow() {
        return now;
    }

    public void setNow(boolean now) {
        this.now = now;
    }
}