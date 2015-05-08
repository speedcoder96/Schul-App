package de.szut.ita13.app.schulapp.calendar.container;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by Rene on 04.05.2015.
 */
public class CalendarMonth {

    public static final int[] MONTH_OPERATOR = {-1, 0, 1};
    public static final int DEFAULT_NUMBER_OF_MONTHS = 3;
    public static final int PREVIOUS_MONTH = 0;
    public static final int CURRENT_MONTH = 1;
    public static final int NEXT_MONTH = 2;

    private Calendar calendar;
    private ArrayList<CalendarElement> calendarElements;

    public CalendarMonth(Calendar calendar) {
        this.calendar = calendar;
        this.calendarElements = new ArrayList<CalendarElement>();
    }

    public void addCalendarElement(CalendarElement element) {
        calendarElements.add(element);
    }

    public void removeCalendarElement(CalendarElement element) {
        calendarElements.remove(element);
    }

    public ArrayList<CalendarElement> getCalendarElements() {
        return calendarElements;
    }

    public void setCalendarHeader(CalendarHeader header) {
        if(calendarElements.size() == 0) {
            calendarElements.add(header);
        }
    }

    public int getNumberOfWeeks() {
        return calendarElements.size() - 1;
    }

    public static CalendarMonth generate(Calendar calendar, int month, int year) {
        CalendarMonth calendarMonth = new CalendarMonth(calendar);
        calendarMonth.setCalendarHeader(new CalendarHeader());
        int[] firstOfMonth = DateUtil.getWeekdayIndex(1, month, year);
        int leapYearCorrection = (firstOfMonth[1] == -1) ? 1 : 0;
        int firstOfMonthOffset = firstOfMonth[0];
        boolean hasFilledBlanks = false;
        CalendarWeek week = new CalendarWeek(calendar);
        for(int i = 0; i < DateUtil.DAYS_IN_MONTH[month - 1] + leapYearCorrection; i++) {
            if((i + firstOfMonthOffset) % 7 == 0 && i != 0) {
                calendarMonth.addCalendarElement(week);
                week = new CalendarWeek(calendar);
                week.setWeekNumber(DateUtil.getWeekNumber(i + 1, month, year));
            }
            if(!hasFilledBlanks) {
                for(int j = 0; j < firstOfMonth[0]; j++) {
                    week.addCalendarDate(new CalendarDate(CalendarDate.NONE));
                }
                hasFilledBlanks = true;
            }
            week.addCalendarDate(new CalendarDate((i / 7) * 7 + (i % 7) + 1, month, year));
        }
        calendarMonth.addCalendarElement(week);
        return calendarMonth;
    }

    public static CalendarMonth[] generateDefaultMonths(Calendar calendar, int month, int year) {
        CalendarMonth[] calendarMonths = new CalendarMonth[DEFAULT_NUMBER_OF_MONTHS];
        for(int i = 0; i < calendarMonths.length; i++) {
            int currentMonth = (month + MONTH_OPERATOR[i]);
            int currentYear = year;
            if(currentMonth < 1) {
                currentMonth = 12;
                currentYear -= 1;
            }
            else if(currentMonth > 12) {
                currentMonth = 1;
                currentYear += 1;
            }
            calendarMonths[i] = CalendarMonth.generate(calendar, currentMonth, currentYear);
        }
        return calendarMonths;
    }

}
