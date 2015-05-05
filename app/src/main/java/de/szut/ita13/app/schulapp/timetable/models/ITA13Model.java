package de.szut.ita13.app.schulapp.timetable.models;


import de.szut.ita13.app.schulapp.timetable.container.TimeTableModel;
import de.szut.ita13.app.schulapp.utilities.DateUtilities;

/**
 * Created by Rene on 24.02.2015.
 */
public class ITA13Model implements TimeTableModel {


    @Override
    public boolean isTwoWeekChange() {
        return false;
    }

    @Override
    public String getFirstLessonTime() {
        return "8:10";
    }

    @Override
    public int getLessonsCount() {
        return 10;
    }

    @Override
    public int getLessonLength() {
        return 45;
    }

    @Override
    public int getBreakLength() {
        return 20;
    }

    public int getBreaksInterval() {
        return 2;
    }

    public int getHeaderLanguage() {
        return DateUtilities.GERMAN_NAME_INDEX;
    }
}
