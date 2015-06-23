package de.szut.ita13.app.schulapp;

import java.util.ArrayList;

/**
 * Created by Rene on 23.06.2015.
 */
public class TimeTableModifier {

    private TimeTableActivity timeTableActivity;
    private ArrayList<TimeTable> timeTables;

    public TimeTableModifier(TimeTableActivity timeTableActivity) {
        this.timeTableActivity = timeTableActivity;
        this.timeTables = new ArrayList<>();
    }

    public void addTimeTable(TimeTable timeTable) {
        timeTables.add(timeTable);
    }

    public int getTimeTableCount() {
        return timeTables.size();
    }

    public TimeTable getTimeTable(int index) {
        return timeTables.get(index);
    }

}
