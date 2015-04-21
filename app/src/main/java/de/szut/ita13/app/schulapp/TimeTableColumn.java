package de.szut.ita13.app.schulapp;

import java.util.ArrayList;

/**
 * Created by Rene on 24.02.2015.
 * TimeTableColumn is a container class, which holds
 * a certain column from the time table.
 * To retrieve a TimeTableColumn, the TimeTable class
 * provides a getTimeTableColumn() method
 */
public class TimeTableColumn {

    private ArrayList<TimeTableSubject> timeTableSubjects;

    public TimeTableColumn() {
        this.timeTableSubjects = new ArrayList<TimeTableSubject>();
    }

    public ArrayList<TimeTableSubject> getTimeTableSubjects() {
        return timeTableSubjects;
    }

    public TimeTableSubject getSubjectAtHour(int hour) {
        if(hour >= 0 && hour < timeTableSubjects.size()) {
            return timeTableSubjects.get(hour);
        }
        return null;
    }

    public boolean setSubjectAtHour(int hour, TimeTableSubject timeTableSubject) {
        if(hour >= 0 && hour < timeTableSubjects.size()) {
            timeTableSubject.setValidSubject(TimeTableSubject.REGULAR_SUBJECT);
            timeTableSubjects.set(hour, timeTableSubject);
            return true;
        }
        return false;
    }

    public void addSubject(TimeTableSubject timeTableSubject) {
        timeTableSubjects.add(timeTableSubject);
    }

}
