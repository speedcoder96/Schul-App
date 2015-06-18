package de.szut.ita13.app.schulapp.calendar.container;

import java.util.ArrayList;

/**
 * Created by Rene on 29.04.2015.
 */
public class CalendarWeek implements CalendarElement {

    private int weekNumber;
    private boolean oddWeek;

    private Calendar calendar;

    private ArrayList<CalendarDate> calendarDates;

    public CalendarWeek(Calendar calendar) {
        this.calendar = calendar;
        this.calendarDates = new ArrayList<CalendarDate>();
    }

    public void addCalendarDate(CalendarDate calendarDate) {
        calendarDates.add(calendarDate);
    }

    @Override
    public Object getItem(int index) {
        if(index >= 0 && index < calendarDates.size())
            return calendarDates.get(index);
        return null;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
        this.oddWeek = weekNumber % 2 == 1;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    @Override
    public int getSize() {
        return calendarDates.size();
    }

    @Override
    public int getLayoutID(int index) {
        return LAYOUT_IDS[index];
    }


    public static CalendarWeek getActualWeek(CalendarMonth calendarMonth, int weekNumber) {
        for(CalendarElement calendarElement : calendarMonth.getCalendarElements()) {
            if(calendarElement instanceof CalendarWeek) {
                CalendarWeek week = (CalendarWeek) calendarElement;
                if(weekNumber == week.getWeekNumber()) {
                    return week;
                }
            }
        }
        return null;
    }
}
