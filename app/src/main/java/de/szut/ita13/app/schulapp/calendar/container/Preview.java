package de.szut.ita13.app.schulapp.calendar.container;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Rene on 15.06.2015.
 */
public class Preview {

    public static int DEFAULT_PREVIEW_DAYS = 7;

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
        CalendarMonth actualMonth = calendarMonths[CalendarMonth.CURRENT_MONTH];
        CalendarWeek cw = CalendarMonth.getActualWeek(actualMonth);
        ArrayList<CalendarWeek> actualWeek = new ArrayList<CalendarWeek>();
        actualWeek.add(cw);
        if (actualWeek.size() != 0) {
            int actualDateIndex = CalendarDate.getActualDateIndex(actualWeek.get(0));
            if (actualDateIndex != 0) {
                int previewCounter = DEFAULT_PREVIEW_DAYS;
                while(previewCounter > 0) {
                    int weekNumber = actualWeek.get(0).getWeekNumber();
                    for(int i = 0; i < actualWeek.size(); i++) {
                        int attachedItems = attachAppointments(preview, actualWeek.get(i), actualDateIndex, previewCounter);
                        previewCounter -= attachedItems;
                        actualDateIndex = 0;
                    }
                    actualWeek = nextWeek(calendarMonths, weekNumber + 1);
                }
            }
        }
        return preview;
    }

    private static int attachAppointments(Preview preview, CalendarWeek calendarWeek, int start, int previewCounter) {
        previewCounter = (previewCounter <= calendarWeek.getSize()) ? previewCounter : calendarWeek.getSize();
        for (int i = start; i < previewCounter; i++) {
            CalendarDate calendarDate = (CalendarDate) calendarWeek.getItem(i);
            if(!calendarDate.isNone()) {
                if (calendarDate.hasAppointments()) {
                    ArrayList<CalendarAppointment> appointments = calendarDate.getCalendarAppointments();
                    for (CalendarAppointment appointment : appointments) {
                        preview.addPreviewAppointment(appointment);
                    }
                }
            } else {
                previewCounter += 1;
                continue;
            }
        }
        return previewCounter - start;
    }

    private static ArrayList<CalendarWeek> nextWeek(CalendarMonth[] calendarMonths, int weekNumber) {
        ArrayList<CalendarWeek> calendarWeeks = new ArrayList<CalendarWeek>();
        for(int m = 1; m < calendarMonths.length; m++) {
            ArrayList<CalendarElement> calendarElements = calendarMonths[m].getCalendarElements();
            for(int i = 1; i < calendarElements.size(); i++) {
                CalendarWeek calendarWeek = (CalendarWeek)calendarElements.get(i);
                if(calendarWeek.getWeekNumber() == weekNumber) {
                    calendarWeeks.add(calendarWeek);
                }
            }
        }
        return calendarWeeks;
    }
}
