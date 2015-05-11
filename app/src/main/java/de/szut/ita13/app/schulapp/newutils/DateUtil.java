package de.szut.ita13.app.schulapp.newutils;

import android.widget.Toast;

import java.util.Date;
import java.util.GregorianCalendar;

import de.szut.ita13.app.schulapp.calendar.container.Calendar;

/**
 * Created by Rene on 08.05.2015.
 */
public class DateUtil {

    public static final int[] DAYS_IN_MONTH = {31,28,31,30,31,30,31,31,30,31,30,31};
    public static final int[] MONTHS_IDS = {0,3,3,6,1,4,6,2,5,0,3,5};

    public static int WEEKDAY_INDEX = 0;
    public static int LEAPYEAR_CORRECTION = 1;

    public static int ACTUAL_DATE_DAY = 0;
    public static int ACTUAL_DATE_MONTH = 1;
    public static int ACTUAL_DATE_YEAR = 2;

    public static int[] getWeekdayIndex(int day, int month, int year) {
        int[] firstDay = new int[2];
        int monthID = MONTHS_IDS[month - 1];
        int centuryDigit = ((3 - ((year / 100) % 4)) * 2);
        int yearDigit = (year % 100) + ((year % 100) / 4);
        int leapYearCorrection = (year % 4 == 0 && month < 3) ? -1 : 0;
        firstDay[0] = (day + monthID + centuryDigit + yearDigit + leapYearCorrection) % 7;
        firstDay[1] = leapYearCorrection;
        return firstDay;
    }

    public static int getWeekNumber(int day, int month, int year) {
        int daysPast = daysPastSince(day, month, year);
        int weekdayIndex = getWeekdayIndex(day, month, year)[DateUtil.WEEKDAY_INDEX];
        int differenceToMonday = Math.abs(1 - weekdayIndex);
        return (daysPast / 7) + (((daysPast - differenceToMonday) % 7) / 4);
    }

    public static int daysPastSince(int day, int month, int year) {
        int daysPast = 0;
        int leapYearCorrection = 0;
        if(year % 4 == 0 && month > 2) {
            leapYearCorrection = 1;
        }
        for(int i = 0; i < month - 1; i++) {
            daysPast += DAYS_IN_MONTH[i];
        }
        daysPast += day;
        daysPast += leapYearCorrection;
        return daysPast;
    }

    public static int[] getActualDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int year = calendar.get(GregorianCalendar.YEAR);
        int[] date = new int[3];
        date[ACTUAL_DATE_DAY] = day;
        date[ACTUAL_DATE_MONTH] = month;
        date[ACTUAL_DATE_YEAR] = year;
        return date;
    }

    public static boolean isActualDate(int[] currentDate, int day, int month, int year) {
        return currentDate[ACTUAL_DATE_DAY] == day &&
               currentDate[ACTUAL_DATE_MONTH] == month &&
               currentDate[ACTUAL_DATE_YEAR] == year;
    }


}
