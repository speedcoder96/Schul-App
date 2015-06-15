package de.szut.ita13.app.schulapp.timetable.container;

import android.app.Activity;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.utilities.Time;
import de.szut.ita13.app.schulapp.utilities.TimeUtilities;


/**
 * Created by Rene on 23.02.2015.
 * The TimeTableSubject class represents one subject
 * that holds information like name, short name, start time,
 * end time, teacher, room and so on.
 */
public class TimeTableSubject {

    /**
     * the amount of minutes of a default subject time length
     */
    public static final int DEFAULT_SUBJECT_TIME_LENGTH = 45, DOUBLE_SUBJECT_TIME_LENGTH = DEFAULT_SUBJECT_TIME_LENGTH * 2;

    /**
     * represents the invalid and valid subject flag
     */
    public static final boolean FREE_SUBJECT = false, REGULAR_SUBJECT = true;

    /**
     * represents the maximum characters of a subject name
     */
    public static final int MAXIMUM__CHARACTER_LENGTH_OF_SUBJECT_NAME = 5;

    /**
     * lesson/subject start
     */
    private Time time;

    /**
     * lesson/subject end
     */
    private Time nextTime;

    /**
     * lesson/subject name
     */
    private String name;

    /**
     * lesson/subject name in short (is used if the number of characters
     * of the name is greater than 5)
     */
    private String shortName;

    /**
     * lesson/subject teacher
     */
    private String teacher;

    /**
     * lesson/subject room
     */
    private String room;

    /**
     * checks if it's a valid lesson/subject
     */
    private boolean validSubject;

    /**
     * holds the color id of the lesson/subject
     */
    private int color;

    /**
     * holds the activity instance that is retrieved by the
     * getActivityInstance method
     */
    private Activity activity = TimeTable.getActivityInstance();

    /**
     * TimeTableSubject constructor
     * @param validSubject stands for the validity of the subject
     */
    public TimeTableSubject(boolean validSubject) {
        this.validSubject = validSubject;
       // this.color = activity.getResources().getColor(R.color.light_gray);
    }

    /**
     * Sets the time when the lesson/subject starts
     * @param time the time that is set
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * @return Returns the time when the lesson/subject starts
     */
    public Time getTime() {
        return time;
    }

    /**
     * Sets the time when the lesson/subject ends
     * @param nextTime the time that is set
     */
    public void setNextTime(Time nextTime) {
        this.nextTime = nextTime;
    }

    /**
     * @return Returns the time when the lesson/subject ends
     */
    public Time getNextTime() {
        return nextTime;
    }

    /**
     * Sets the name of the lesson/subject. Calls the limitSubjectName method.
     * @param name the name that is set
     */
    public void setSubjectName(String name) {
        this.name = limitSubjectName(name, shortName);
    }

    /**
     * @return Returns the name of the lesson/subject
     */
    public String getSubjectName() {
        return name;
    }

    /**
     * Sets thee short name for this lesson/subject
     * @param shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return Returns the short name for this lesson/subject
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the teacher that teaches the lesson/subject
     * @param teacher the teacher that is set
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     * @return Returns the teacher that teaches
     * this lesson/subject
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Sets the room of this subject
     * @param room the room that is set
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * @return Returns the room of the lesson/subject
     */
    public String getRoom() {
        return room;
    }

    /**
     * Sets the validity of the subject
     * @param validSubject the validity that is set for the subject
     */
    public void setValidSubject(boolean validSubject) {
        this.validSubject = validSubject;
        this.color = activity.getResources().getColor(R.color.light_gray);
    }

    /**
     * @return Returns if the lesson/subject is valid
     */
    public boolean isValidSubject() {
        return validSubject;
    }

    /**
     * Sets the color id that is used for this TimeTableSubject
     * @param color the color that is set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @return Returns the id of the color that is used for
     * this TimeTableSubject
     */
    public int getColor() {
        return color;
    }

    /**
     * @return Returns all of the information that the
     * TimeTableSubject object contains and which should be displayed
     */
    public String getInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append(TimeUtilities.getTimeString(time) + "-" + TimeUtilities.getTimeString(nextTime)).append("\n");
        builder.append(name).append("\n");
        builder.append(teacher).append("\n");
        builder.append(room);
        return builder.toString();
    }

    /**
     * Limits the display name on the short name, if the name is over
     * 5 characters in length
     * @param name the name for the lesson/subject
     * @param shortName the short name that is used instead of the name
     * @return returns the name that is used to display
     */
    public static String limitSubjectName(String name, String shortName) {
        if(name.length() > MAXIMUM__CHARACTER_LENGTH_OF_SUBJECT_NAME) {
            return shortName;
        }
        return name;
    }

}

