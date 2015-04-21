package de.szut.ita13.app.schulapp.utilities;

import java.text.SimpleDateFormat;
/**
 * Created by Rene on 14.02.2015.
 */
public class DateUtilities {

    /**
     * the names of the months in english
     */
    public static final String[] EN_MONTH_NAMES = {
        "January","February","March","April","May","June","July",
        "August","September","October","November","December"
    };

    /**
     * the names of the months in german
     */
    public static final String[] DE_MONTH_NAMES = {
        "Januar","Februar","März","April","Mai","Juni","Juli",
        "August","September","Oktober","November","Dezember"
    };

    /**
     * the names of the months in all languages
     */
    public static final String[][] MONTH_NAMES = {
            EN_MONTH_NAMES, DE_MONTH_NAMES
    };

    /**
     * the names of the weekdays in english
     */
    public static final String[] EN_WEEKDAY_NAMES = {
        "Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"
    };

    /**
     * the names of the weekdays in german
     */
    public static final String[] DE_WEEKDAY_NAMES = {
        "Sonntag","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag"
    };

    /**
     * the indices of the names of the week
     */
    public static final int DAY_MONDAY = 0, DAY_TUESDAY = 1, DAY_WEDNESDAY = 2, DAY_THURSDAY = 3, DAY_FRIDAY = 4;

    /**
     * the names of the time table headers in german
     */
    public static String[] DE_TIME_TABLE_HEADER_NAMES = {
        "Zeit",DE_WEEKDAY_NAMES[1],DE_WEEKDAY_NAMES[2],DE_WEEKDAY_NAMES[3],DE_WEEKDAY_NAMES[4],DE_WEEKDAY_NAMES[5]
    };

    /**
     * the names of the time table headers in german
     */
    public static String[] EN_TIME_TABLE_HEADER_NAMES = {
            "Time",EN_WEEKDAY_NAMES[1],EN_WEEKDAY_NAMES[2],EN_WEEKDAY_NAMES[3],EN_WEEKDAY_NAMES[4],EN_WEEKDAY_NAMES[5]
    };

    /**
     * the names of the time table header in all languages
     */
    public static String[][] TIME_TABLE_HEADER_NAMES = {
            EN_TIME_TABLE_HEADER_NAMES, DE_TIME_TABLE_HEADER_NAMES
    };

    /**
     * the names of the weekdays in all languages
     */
    public static final String[][] WEEKDAY_NAMES = {
        EN_WEEKDAY_NAMES, DE_WEEKDAY_NAMES
    };

    /**
     * the index of the english names of the months in MONTH_NAMES and WEEKDAY_NAMES
     */
    public static final int ENGLISH_NAME_INDEX = 0;

    /**
     * the index of the german names of the months in MONTH_NAMES and WEEKDAY_NAMES
     */
    public static final int GERMAN_NAME_INDEX = 1;

    /**
     * the amount of days in a regular school week
     */
    public static final int SCHOOL_WEEKDAY_COUNT = 5;

    /**
     * the minimum year in gregorian calendar
     */
    public static final int MINIMUM_YEAR = 1590;

    /**
     * the amount of months in a year
     */
    public static final int MONTH_IN_A_YEAR = 12;

    /**
     * the number of the first month
     */
    public static final int FIRST_MONTH = 1;

    /**
     * the number of the last month
     */
    public static final int LAST_MONTH = MONTH_IN_A_YEAR;

    /**
     * the number of the first day
     */
    public static final int FIRST_DAY = 1;

    /**
     * the amount of days in month in a regular year
     */
    public static final int[] REGULAR_DAYS_IN_A_MONTH = {
        31,28,31,30,31,30,31,31,30,31,30,31
    };

    /**
     * the amount of days in month in a leap year
     */
    public static final int[] LEAP_DAYS_IN_A_MONTH = {
        31,29,31,30,31,30,31,31,30,31,30,31
    };

    /**
     * the amount of days in month in
     */
    public static final int[][] ALL_DAYS_IN_A_MONTH = {
            REGULAR_DAYS_IN_A_MONTH, LEAP_DAYS_IN_A_MONTH
    };

    /**
     * is the leap year divider
     */
    public static final int LEAP_YEAR_INTERVAL = 4;

    /**
     * the seperator that divides day, month and year
     */
    public static final String SEPERATOR_BETWEEN_DAY_MONTH_YEAR = "/.";

    /**
     * the serperator that divides day, month and year like in the
     * following format [DD.MM.YYYY]
     */
    public static final String MAIN_SEPERATOR_BETWEEN_DAY_MONTH_YEAR = ".";

    /**
     * the amount of parts in a date
     */
    public static final int DATE_PART_COUNT = 3;


    public static boolean isValidDate(Date date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();

        if(month >= FIRST_MONTH && month <= LAST_MONTH) {
            if(year > MINIMUM_YEAR) {
                int maximumDays = ALL_DAYS_IN_A_MONTH[(year % LEAP_YEAR_INTERVAL == 0 && month < 3) ? 1 : 0][month - 1];
                if(day >= 1 && day <= maximumDays) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public static final String[] getShortenedNames(String[] names, int length) {
        for(int i = 1; i < names.length; i++) {
            names[i] = names[i].substring(0, length);
        }
        return names;
    }

    public static final String[] getDisplayNames(String[][] names, int language) {
        switch(language) {
            case GERMAN_NAME_INDEX:
                return names[GERMAN_NAME_INDEX];
            case ENGLISH_NAME_INDEX:
                return names[ENGLISH_NAME_INDEX];
            default:
                return null;
        }
    }

    public static final String getDateString() {
        return new SimpleDateFormat("EE, dd.MM.yyyy").format(new java.util.Date());
    }

}
