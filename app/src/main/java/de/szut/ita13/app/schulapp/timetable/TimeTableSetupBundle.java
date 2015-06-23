package de.szut.ita13.app.schulapp.timetable;

import android.content.Intent;

import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;

/**
 * Created by Rene on 19.06.2015.
 */
public class TimeTableSetupBundle {

    private CalendarTime startTime;
    private int lessonLength;
    private int lessonCount;
    private int breakLength;
    private int breakInterval;
    private boolean twoWeeksRhythm;

    public TimeTableSetupBundle() {
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

    public boolean isTwoWeeksRhythm() {
        return twoWeeksRhythm;
    }

    public static class Builder {

        private TimeTableSetupBundle timeTableSetupBundle;

        public Builder() {
            timeTableSetupBundle = new TimeTableSetupBundle();
        }

        public Builder(Intent setupIntent) {
            timeTableSetupBundle = new TimeTableSetupBundle();
            setStartTime(new CalendarTime(setupIntent.getStringExtra(TimeTableSetupActivity.START_TIME_KEY)));
            setBreakLength(setupIntent.getIntExtra(TimeTableSetupActivity.BREAK_LENGTH_KEY, 0));
            setBreakInterval(setupIntent.getIntExtra(TimeTableSetupActivity.BREAK_INTERVAL_KEY, 0));
            setTwoWeeksRhythm(setupIntent.getBooleanExtra(TimeTableSetupActivity.TWO_WEEKS_RHYTHM_KEY, true));
            setLessonLength(setupIntent.getIntExtra(TimeTableSetupActivity.LESSON_LENGTH_KEY, 0));
            setLessonCount(setupIntent.getIntExtra(TimeTableSetupActivity.LESSON_COUNT_KEY, 0));
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
