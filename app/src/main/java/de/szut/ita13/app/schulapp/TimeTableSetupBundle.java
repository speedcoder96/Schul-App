package de.szut.ita13.app.schulapp;

import android.content.Context;

import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;

/**
 * Created by Rene on 19.06.2015.
 */
public class TimeTableSetupBundle {

    private Context context;

    private CalendarTime startTime;
    private int lessonLength;
    private int lessonCount;
    private int breakLength;
    private int breakInterval;
    private boolean twoWeeksRhythm;

    public TimeTableSetupBundle(Context context) {
        this.context = context;
    }

    public CalendarTime getStartTime() {
        return startTime;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public int getLessonLength() {
        return lessonLength;
    }

    public int getBreakLength() {
        return breakLength;
    }

    public int getBreakInterval() {
        return breakInterval;
    }

    public boolean getTwoWeeksRhythm() {
        return twoWeeksRhythm;
    }

    public static class Builder {

        private TimeTableSetupBundle timeTableSetupBundle;

        public Builder(Context context) {
            timeTableSetupBundle = new TimeTableSetupBundle(context);
        }

        public Builder setStartTime(CalendarTime startTime) {
            timeTableSetupBundle.startTime = startTime;
            return this;
        }

        public Builder setLessonLength(int lessonLength) {
            timeTableSetupBundle.lessonLength = lessonLength;
            return this;
        }

        public Builder setLessonCount(int lessonCount) {
            timeTableSetupBundle.lessonCount = lessonCount;
            return this;
        }

        public Builder setBreakLength(int breakLength) {
            timeTableSetupBundle.breakLength = breakLength;
            return this;
        }

        public Builder setBreakInterval(int breakInterval) {
            timeTableSetupBundle.breakInterval = breakInterval;
            return this;
        }

        public Builder setTwoWeeksRhythm(boolean twoWeeksRhythm) {
            timeTableSetupBundle.twoWeeksRhythm = twoWeeksRhythm;
            return this;
        }

        public TimeTableSetupBundle build() {
            return timeTableSetupBundle;
        }



    }

}
