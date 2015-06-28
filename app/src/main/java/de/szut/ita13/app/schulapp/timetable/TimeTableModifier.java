package de.szut.ita13.app.schulapp.timetable;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Rene on 23.06.2015.
 */
public class TimeTableModifier {

    private TimeTableActivity timeTableActivity;
    public TimeTableDataSource dataSource;
    public boolean editMode;
    private ArrayList<TimeTable> timeTables;
    private TimeTableUIUpdater[] timeTableFragments;

    public TimeTableModifier(TimeTableActivity timeTableActivity) {
        this.timeTableActivity = timeTableActivity;
        dataSource = new TimeTableDataSource(timeTableActivity);
        this.timeTables = new ArrayList<>();
        editMode = false;
        timeTableFragments = new TimeTableUIUpdater[2];
    }

    public void setTimeTableFragment(int i, TimeTableFragment fragment) {
        timeTableFragments[i] = fragment;
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

    public void update() {
        for(TimeTableUIUpdater fragment : timeTableFragments) {
            if(fragment != null) {
                fragment.changedUI();
                Log.d("TimeTableModifier", "Test");
            }
        }
    }

}
