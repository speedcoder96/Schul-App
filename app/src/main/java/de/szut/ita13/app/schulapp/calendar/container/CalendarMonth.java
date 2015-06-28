package de.szut.ita13.app.schulapp.calendar.container;

import android.util.Log;

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
    private int month, year;

    public CalendarMonth(Calendar calendar, int month, int year) {
        this.calendar = calendar;
        this.month = month;
        this.year = year;
        this.calendarElements = new ArrayList<CalendarElement>();
    }

    public String getTitleString() {
        int[] range = getWeekRange(this);
        return DateUtil.MONTH_NAMES[month - 1] + " " + String.valueOf(year) +
                " KW: " + range[0] + "-" + range[1];
    }

    public CalendarWeek firstWeek() {
        return (CalendarWeek)calendarElements.get(1);
    }

    public int getMonthIndex() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void addCalendarElement(CalendarElement element) {
        calendarElements.add(element);
    }

    public ArrayList<CalendarElement> getCalendarElements() {
        return calendarElements;
    }

    public void setCalendarHeader(CalendarHeader header) {
        if(calendarElements.size() == 0) {
            calendarElements.add(header);
        }
    }

    public static CalendarMonth generate(Calendar calendar, int month, int year) {
        CalendarMonth calendarMonth = new CalendarMonth(calendar, month, year);
        calendarMonth.setCalendarHeader(new CalendarHeader());
        int[] currentDate = DateUtil.getActualDate();
        int[] firstOfMonth = DateUtil.getWeekdayIndex(1, month, year);
        int leapYearCorrection = (firstOfMonth[1] == -1) ? 1 : 0;
        int firstOfMonthOffset = (firstOfMonth[0] - 1 == - 1) ? 6 : firstOfMonth[0] - 1;
        int firstWeekNumberOfMonth = DateUtil.getWeekOfYear(1, month, year);
        boolean hasFilledBlanks = false;
        CalendarWeek week = new CalendarWeek(calendar);
        week.setWeekNumber(firstWeekNumberOfMonth);
        firstWeekNumberOfMonth = (firstWeekNumberOfMonth == 53) ? 0 : firstWeekNumberOfMonth;
        for(int i = 0; i < DateUtil.DAYS_IN_MONTH[month - 1] + leapYearCorrection; i++) {
            if((i + firstOfMonthOffset) % 7 == 0 && i != 0) {
                calendarMonth.addCalendarElement(week);
                week = new CalendarWeek(calendar);
                firstWeekNumberOfMonth = DateUtil.getWeekOfYear(i + 1, month, year);
                week.setWeekNumber(firstWeekNumberOfMonth);
            }
            if(!hasFilledBlanks) {
                for(int j = 0; j < firstOfMonthOffset; j++) {
                    week.addCalendarDate(new CalendarDate(CalendarDate.NONE));
                }
                hasFilledBlanks = true;
            }
            boolean isActual = DateUtil.isActualDate(currentDate, i + 1, month, year);
            CalendarDate calendarDate = new CalendarDate(i + 1, month, year, (firstOfMonthOffset + i) % 7, isActual);
            week.addCalendarDate(calendarDate);
            Calendar.calendarMap.putCalendarDate(calendarDate, calendarDate.getDateString(CalendarDate.DATABASE_DATE_FORMAT));
        }

        calendarMonth.addCalendarElement(week);
        return calendarMonth;
    }

    public static CalendarMonth[] generateDefaultMonths(Calendar calendar, int month, int year) {
        Calendar.calendarMap = new CalendarMap();
        CalendarMonth[] calendarMonths = new CalendarMonth[DEFAULT_NUMBER_OF_MONTHS];
        for(int i = 0; i < calendarMonths.length; i++) {
            int currentMonth = (month + MONTH_OPERATOR[i]); // -1 0 1
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
        Calendar.calendarMap.load(Calendar.dataSource, month, year);
        return calendarMonths;
    }

    public static CalendarWeek getActualWeek(CalendarMonth calendarMonth) {
        ArrayList<CalendarElement> calendarElements = calendarMonth.getCalendarElements();
        for(int i = 1; i < calendarElements.size(); i++) {
            CalendarWeek week = (CalendarWeek)calendarElements.get(i);
            for(int j = 0; j < week.getSize(); j++) {
                CalendarDate calendarDate = (CalendarDate)week.getItem(j);
                if(calendarDate.isActualDate()) {
                    return week;
                }
            }
        }
        return null;
    }

    public static int[] getWeekRange(CalendarMonth calendarMonth) {
        int[] range = new int[2];
        ArrayList<CalendarElement> calendarElements = calendarMonth.getCalendarElements();
        CalendarWeek firstWeek = (CalendarWeek)calendarElements.get(1);
        CalendarWeek lastWeek = (CalendarWeek)calendarElements.get(calendarElements.size() - 1);
        range[0] = firstWeek.getWeekNumber();
        range[1] = lastWeek.getWeekNumber();
        return range;
    }


}
