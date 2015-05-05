package de.szut.ita13.app.schulapp.timetable.container;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.utilities.Time;


/**
 * Created by Rene on 23.02.2015.
 * TimeTableRow is one class that inherits from TimeTableElement.
 * TimeTableRow holds the subjects for all days of the week at
 * one specific time.
 * Also it provides the access to the hour index, the time of
 * the hour and a flag that is set whenever it is now.
 */
public class TimeTableRow extends TimeTableElement {


    private int hour;
    private Time time;
    private Time nextTime;
    private ArrayList<TimeTableSubject> timeTableSubjects;
    private boolean now;

    public TimeTableRow(int hour, Time time, Time nextTime, ArrayList<TimeTableSubject> timeTableSubjects) {
        this.hour = hour;
        this.time = time;
        this.nextTime = nextTime;
        this.timeTableSubjects = timeTableSubjects;
    }

    public TimeTableSubject getSubjectAt(int position) {
        return timeTableSubjects.get(position);
    }

    public void setNow(boolean now) {
        this.now = now;
    }

    public boolean isNow() {
        return now;
    }

    public int subjectCount() {
        return timeTableSubjects.size();
    }

    public int getHour() {
        return hour;
    }

    public Time getTime() {
        return time;
    }

    public Time getNextTime() {
        return nextTime;
    }
}
