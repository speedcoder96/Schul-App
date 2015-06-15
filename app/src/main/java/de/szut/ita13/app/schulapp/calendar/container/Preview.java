package de.szut.ita13.app.schulapp.calendar.container;

import java.util.ArrayList;

/**
 * Created by Rene on 15.06.2015.
 */
public class Preview {

    private ArrayList<CalendarAppointment> previewAppointments;

    public Preview() {
        previewAppointments = new ArrayList<CalendarAppointment>();
    }

    public void addPreviewAppointment(CalendarAppointment previewAppointment) {
        previewAppointments.add(previewAppointment);
    }

    public CalendarAppointment getItem(int index) {
       return previewAppointments.get(index);
    }

    public int size() {
        return previewAppointments.size();
    }

    public static Preview createPreview(CalendarMonth[] calendarMonths) {
        Preview preview = new Preview();
        CalendarMonth actualMonth = null;
        int weekNumber = CalendarMonth.HAS_NO_ACTUAL_WEEK;
        for (CalendarMonth calendarMonth : calendarMonths) {
            weekNumber = CalendarMonth.getActualWeek(calendarMonth);
            if (weekNumber != CalendarMonth.HAS_NO_ACTUAL_WEEK) {
                actualMonth = calendarMonth;
                break;
            }
        }
        if (actualMonth != null) {
            CalendarWeek actualWeek = CalendarWeek.getActualWeek(actualMonth, weekNumber);
            int[] weekRange = CalendarMonth.getWeekRange(actualMonth);
            int actualDateIndex = CalendarDate.getActualDateIndex(actualWeek);
            if (actualDateIndex != 0) {
                int lastWeekIndex = weekRange[1];
                if (actualWeek.getWeekNumber() + 1 <= lastWeekIndex) {
                    int previewCounter = 7 - actualDateIndex;
                    for (int i = actualDateIndex; i < actualWeek.getSize(); i++) {
                        CalendarDate calendarDate = (CalendarDate) actualWeek.getItem(i);
                        if (calendarDate.hasAppointments()) {
                            ArrayList<CalendarAppointment> appointments = calendarDate.getCalendarAppointments();
                            for (CalendarAppointment appointment : appointments) {
                                preview.addPreviewAppointment(appointment);
                            }
                        }
                    }
                    CalendarWeek nextWeek = CalendarWeek.getActualWeek(actualMonth, weekNumber + 1);
                    for (int i = 0; i < previewCounter; i++) {
                        CalendarDate calendarDate = (CalendarDate) nextWeek.getItem(i);
                        if (calendarDate.hasAppointments()) {
                            ArrayList<CalendarAppointment> appointments = calendarDate.getCalendarAppointments();
                            for (CalendarAppointment appointment : appointments) {
                                preview.addPreviewAppointment(appointment);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < actualWeek.getSize(); i++) {
                        CalendarDate calendarDate = (CalendarDate) actualWeek.getItem(i);
                        if (calendarDate.hasAppointments()) {
                            ArrayList<CalendarAppointment> appointments = calendarDate.getCalendarAppointments();
                            for (CalendarAppointment appointment : appointments) {
                                preview.addPreviewAppointment(appointment);
                            }
                        }
                    }
                }
            }
        }
        return preview;
    }
}
