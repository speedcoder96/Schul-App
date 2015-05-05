package de.szut.ita13.app.schulapp.timetable.container;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.adapter.TimeTableRowAdapter;
import de.szut.ita13.app.schulapp.utilities.DateUtilities;
import de.szut.ita13.app.schulapp.utilities.InvalidTimeException;
import de.szut.ita13.app.schulapp.utilities.Time;
import de.szut.ita13.app.schulapp.utilities.TimeUtilities;


/**
 * Created by Rene on 24.02.2015.
 * TimeTable is the class, which holds all of the TimeTableElements
 * that include TimeTableRowHeader, TimeTableRows and so on.
 * One TimeTableRow holds the subjects for all days of the week at
 * one specific time.
 * Different methods in this class give access to retrieve a certain
 * TimeTableRow, TimeTableColumn, TimeTableColumnRange or even a Subject.
 *
 */
public class TimeTable implements TimeTableUpdatable {
    /**
     * Tag that identifies logs of this class
     */
    public static final String TAG = TimeTable.class.getSimpleName();

    /**
     * represents the time table header index (value = 0)
     */
    public static final int TIME_TABLE_HEADER_INDEX = 0;

    /**
     * holds a static accessible instance of the main activity
     */
    private static Activity activity;

    /**
     * is the ListView object that holds all of the sub views
     */
    private ListView timeTableList;

    /**
     * holds all of the TimeTableElements that builds up the entire time table
     */
    private ArrayList<TimeTableElement> timeTableElements;

    /**
     * is the TimeTableRowAdapter (Controller) that put the values of the
     * TimeTableElements and the inflated views together
     */
    private TimeTableRowAdapter timeTableRowAdapter;

    /**
     * holds the time table model
     */
    private TimeTableModel timeTableModel;

    /**
     * is the language index that is used for the time table header strings
     * it decides if the strings are represented in german or english
     */
    private int language;

    /**
     * the timer that updates the UI-Elements
     */
    private TimeTableTimer timer;

    /**
     * holds the colors that indicates the current running hour
     */
    private int[] timeIndicatorColors;

    /**
     * represents the default time indicator colors
     */
    public static final int[] DEFAULT_TIME_INDICATOR_COLORS = {
            R.color.orange, R.color.dark_gray
    };

    /**
     * represents the selection modis
     */
    public static final int TIME_SELECTED = 0, TIME_UNSELECTED = 1;

    /**
     * The constructor of the time table class
     * @param activity the activity instance of the main activity class
     * @param timeTableModel the model that creates the time table
     **/

    public TimeTable(Activity activity, TimeTableModel timeTableModel) {
        this.activity = activity;
        this.timeTableModel = timeTableModel;
        this.timeIndicatorColors = DEFAULT_TIME_INDICATOR_COLORS;
        this.language = this.timeTableModel.getHeaderLanguage();
        activity.setContentView(R.layout.activity_time_table);
        timeTableElements = new ArrayList<TimeTableElement>();
        String[] headers = DateUtilities.getShortenedNames(
                DateUtilities.getDisplayNames(DateUtilities.TIME_TABLE_HEADER_NAMES, language), 2);
        timeTableElements.add(new TimeTableRowHeader(headers));
        timeTableList = (ListView) activity.findViewById(R.id.timetableList);
        timeTableRowAdapter = new TimeTableRowAdapter(activity, this);
        timeTableList.setAdapter(timeTableRowAdapter);
        timer = new TimeTableTimer();
        timer.addUpdatable(this);
    }

    /**
     * Creates the time table out of the given TimeTableModel
     * @throws InvalidTimeException get thrown if a time isn't valid
     */
    public void createTimeTable() throws InvalidTimeException {
        Time startTime = TimeUtilities.getTime(timeTableModel.getFirstLessonTime());
        for(int timeTableRowIndex = 0; timeTableRowIndex < timeTableModel.getLessonsCount(); timeTableRowIndex++) {
            ArrayList<TimeTableSubject> timeTableSubjects = new ArrayList<TimeTableSubject>();
            try {
                Time currentTime = TimeUtilities.getNextTimeIncludingBreak(
                        startTime, timeTableModel.getLessonLength(), timeTableRowIndex, timeTableModel.getBreaksInterval(),
                        timeTableModel.getBreakLength());
                for (int subjectIndex = 0; subjectIndex < DateUtilities.SCHOOL_WEEKDAY_COUNT; subjectIndex++) {
                    timeTableSubjects.add(new TimeTableSubject(TimeTableSubject.FREE_SUBJECT));
                }
                timeTableElements.add(new TimeTableRow(timeTableRowIndex + 1, currentTime,
                                TimeUtilities.getNextTimeIncludingBreak(startTime, timeTableModel.getLessonLength(),
                                timeTableRowIndex + 1, timeTableModel.getBreaksInterval(),
                                        timeTableModel.getBreakLength()), timeTableSubjects));
            } catch(InvalidTimeException e){
                e.printStackTrace();
            }
        }
        updateTimeNow();
    }

    /**
     * Updates the time column and sets a timer
     * which automatically updates the UI-Elements
     */
    public void updateTimeNow() {
        for (int i = 1; i < timeTableElements.size(); i++) {
           TimeUtilities.isNow(timeTableElements, i);
        }
        Time actualTime = TimeUtilities.getActualTimeStringIncludingSeconds();
        Time nextTime = TimeUtilities.getNextUpdateTime(timeTableElements);
        timer.startTimer(actualTime, nextTime);
    }

    /**
     * Retrieves the time table header out of all time table elements
     * @return returns the time table header
     */
    public TimeTableRowHeader getTimeTableRowHeader() {
        return (TimeTableRowHeader) timeTableElements.get(TIME_TABLE_HEADER_INDEX);
    }

    /**
     * Retrieves a time table column specified by the day out of all time table elements
     * @param day the day which specifies the time table column
     * @return returns the time table column with all subjects
     */
    public TimeTableColumn getTimeTableColumn(int day) {
        TimeTableColumn column = new TimeTableColumn();
        for(TimeTableElement timeTableElement : timeTableElements) {
            if(timeTableElement instanceof TimeTableRow) {
                TimeTableRow timeTableRow = (TimeTableRow) timeTableElement;
                column.addSubject(timeTableRow.getSubjectAt(day));
            }
        }
        return column;
    }

    /**
     * Retrieves a subject specified by the day and hour out of all the time
     * table elements
     * @param day the day which specifies the time table column
     * @param hour the hour which specifies the subject in the time table column
     * @return returns the subject
     */
    public TimeTableSubject getTimeTableSubject(int day, int hour) {
        TimeTableElement timeTableElement = timeTableElements.get(hour + 1);
        TimeTableRow timeTableRow = (TimeTableRow) timeTableElement;
        TimeTableSubject timeTableSubject = timeTableRow.getSubjectAt(day);
        return timeTableSubject;
    }


    /**
     * Retrieves a TimeTableColumnRange out of all the time table elements by day and
     * a range between the firstHour and lastHour
     * @param day the day which specifies the time table column
     * @param firstHour firstHour of the range
     * @param lastHour lastHour of the range (index isn't included -> exclusive)
     * @return returns the specified time table column
     */
    public TimeTableColumnRange getTimeTableColumnRange(int day, int firstHour, int lastHour) {
        TimeTableColumnRange timeTableColumnRange = new TimeTableColumnRange();
        if(lastHour < timeTableElements.size() && firstHour >= 0 && firstHour < lastHour) {
            for(int i = firstHour; i < lastHour; i++) {
                TimeTableElement timeTableElement = timeTableElements.get(i + 1);
                TimeTableRow timeTableRow = (TimeTableRow) timeTableElement;
                timeTableColumnRange.addSubject(timeTableRow.getSubjectAt(day));
            }
            return timeTableColumnRange;
        }
        return null;
    }

    /**
     * Sets the timeIndicatorColors. The first index represents
     * the selected and the second the unselected one
     * @param timeIndicatorColors
     */
    public void setTimeIndicatorColors(int[] timeIndicatorColors) {
        this.timeIndicatorColors = timeIndicatorColors;
    }

    /**
     * Returns the color id specified through the index that
     * is passed as parameter
     * @param index the index that specifies the result value
     * @return returns the color id specifies by the index value
     */
    public int getTimeIndicatorColors(int index) {
        if(index >= 0 && index < timeIndicatorColors.length) {
            return timeIndicatorColors[index];
        }
        return R.color.orange;
    }

    /**
     * @return returns all of the time table elements
     */
    public ArrayList<TimeTableElement> getTimeTableElements() {
        return timeTableElements;
    }

    /**
     * @return returns the time table row adapter which is used to combine
     * the values with the UI
     */
    public TimeTableRowAdapter getTimeTableRowAdapter() {
        return timeTableRowAdapter;
    }

    /**
     * @return returns the time table model that is build up by the user
     */
    public TimeTableModel getTimeTableModel() {
        return timeTableModel;
    }

    /**
     * @return returns the activity instance for other classes
     */
    public static Activity getActivityInstance() {
        return activity;
    }


    public void update() {
        updateTimeNow();
        timeTableRowAdapter.notifyDataSetChanged();
    }

}
