package de.szut.ita13.app.schulapp.timetable.container;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.utilities.InvalidTimeException;
import de.szut.ita13.app.schulapp.utilities.Time;
import de.szut.ita13.app.schulapp.utilities.TimeUtilities;


/**
 * Created by Rene on 28.02.2015.
 * The TimeTableTimer class makes sure that the
 * UI-Elements gets updated, when the next lesson begins.
 * The timer is set by the difference between the actual
 * lesson and the next lesson in milliseconds.
 */
public class TimeTableTimer  {

    /**
     * the tag that identifies the logs of this class
     */
    public static final String TAG = TimeTableTimer.class.getSimpleName();

    /**
     * holds the handler instance which handles the runnable
     * that is executed when the timer is done
     */
    private final Handler handler;

    /**
     * the runnable that executes the update method
     */
    private Runnable runnable;

    /**
     * the time of the timer in milliseconds
     */
    private long timeMilliseconds;

    /**
     * checks if the timer is scheduled or not
     */
    private boolean scheduled = false;

    /**
     * holds the updatable elements
     */
    private ArrayList<TimeTableUpdatable> timeTableUpdatables;

    public TimeTableTimer() {
        this.handler = new Handler();
        timeTableUpdatables = new ArrayList<TimeTableUpdatable>();
    }

    /**
     * Starts the timer if no one is scheduled at the moment
     * @param timeMilliseconds the time in milliseconds
     */
    public void startTimer(long timeMilliseconds) {
        this.timeMilliseconds = timeMilliseconds;
        if(this.timeMilliseconds > 0 && !scheduled) {
            initializeRunnable();
            handler.postDelayed(runnable, this.timeMilliseconds);
            scheduled = true;
            Log.d(TAG, "Start Timer! - Scheduled:" + scheduled + ",Time:" + timeMilliseconds);
        }
    }

    /**
     * startTimer method that takes two time objects as parameters and
     * calls the actual startTimer method
     * @param actualTime the actual time stamp as time object
     * @param nextTime the next time as time object
     */
    public void startTimer(Time actualTime, Time nextTime) {
        long time = 0;
        if(nextTime == null) {
            try {
                nextTime = TimeUtilities.getTime("8:10");
            } catch (InvalidTimeException e) {
                e.printStackTrace();
            }
        }
        if(time == 0)
            time = TimeUtilities.getDifferenceMilliseconds(actualTime, nextTime);
        startTimer(time);
    }

    /**
     *
     * @return returns the set time for the timer
     */
    public long getTimeMilliseconds() {
        return timeMilliseconds;
    }

    /**
     * initialize the runnable
     */
    private void initializeRunnable() {
        runnable = new Runnable() {
            public void run() {
                stopTimer();
                for(TimeTableUpdatable timeTableUpdatable : timeTableUpdatables) {
                    timeTableUpdatable.update();
                }
                Log.d(TAG, "Views Updated! - Scheduled:" + scheduled + ",Time:" + timeMilliseconds);
            }
        };
    }

    /**
     * stops the timer by removing the runnable which
     * calls the update method
     */
    public void stopTimer() {
        handler.removeCallbacks(runnable);
        scheduled = false;
        Log.d(TAG, "Stop Timer! - Scheduled:" + scheduled + ",Time:" + timeMilliseconds);
    }

    /**
     * @return returns if the timer is scheduled
     */
    public boolean isScheduled() {
        return scheduled;
    }

    public void addUpdatable(TimeTableUpdatable updatable) {
        timeTableUpdatables.add(updatable);
    }

}
