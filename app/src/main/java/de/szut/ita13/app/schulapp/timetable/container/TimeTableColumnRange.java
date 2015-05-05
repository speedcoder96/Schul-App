package de.szut.ita13.app.schulapp.timetable.container;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Rene on 24.02.2015.
 */
public class TimeTableColumnRange {

    private Activity activity = TimeTable.getActivityInstance();
    private ArrayList<TimeTableSubject> timeTableSubjects;

    public TimeTableColumnRange() {
        this.timeTableSubjects = new ArrayList<TimeTableSubject>();
    }

    public void addSubject(TimeTableSubject timeTableSubject) {
        timeTableSubjects.add(timeTableSubject);
    }

    public void setProperties(String subjectName, String shortName, String room, String teacher, int color) {
        for(TimeTableSubject timeTableSubject : timeTableSubjects) {
            timeTableSubject.setShortName(shortName);
            timeTableSubject.setSubjectName(subjectName);
            timeTableSubject.setRoom(room);
            timeTableSubject.setTeacher(teacher);
            timeTableSubject.setValidSubject(TimeTableSubject.REGULAR_SUBJECT);
            timeTableSubject.setColor(activity.getResources().getColor(color));
        }
    }
}
