package de.szut.ita13.app.schulapp;

/**
 * Created by Rene on 24.02.2015.
 */
public interface TimeTableModel {

    public boolean isTwoWeekChange();

    public String getFirstLessonTime();

    public int getLessonsCount();

    public int getLessonLength();

    public int getBreakLength();

    public int getBreaksInterval();

    public int getHeaderLanguage();




}
