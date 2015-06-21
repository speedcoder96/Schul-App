package de.szut.ita13.app.schulapp;

import java.util.ArrayList;
import java.util.Random;

import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;

/**
 * Created by Michele on 21.06.2015.
 */
public class TimeTable implements TimeTableMatrix {

    public static final int DAYS_IN_WEEK = 5;

    private String name;
    private ArrayList<TimeTableRowItem> rowItems;
    private TimeTableSetupBundle timeTableSetupBundle;

    public TimeTable(TimeTableSetupBundle timeTableSetupBundle, String name){
        this.rowItems = new ArrayList<>();
        this.name = name;
        this.timeTableSetupBundle = timeTableSetupBundle;
        new TimeTable.Builder(this).build();
    }

    public String getName() {
        return name;
    }

    public int getRowCount(){
        return rowItems.size();
    }

    public int getColumnCount(int row) {
        return rowItems.get(row).getItems().size();
    }

    public TimeTableRowItem getRowAt(int index) {
        return rowItems.get(index);
    }

    public TimeTableItem getItemAt(int row, int column) {
        return rowItems.get(row).getItems().get(column);
    }

    public void setItemAt(int row, int column, TimeTableItem item) {
        rowItems.get(row).getItems().set(column, item);
    }

    public void setRowAt(int row, TimeTableRowItem rowItem) {
        rowItems.set(row, rowItem);
    }

    public static class Builder{

        private TimeTable timeTable;

        public Builder (TimeTable timeTable){
            this.timeTable = timeTable;
        }

        public void build(){

            TimeTableRowItem header = new TimeTableRowItem(timeTable);
            header.setHeader(true);
            timeTable.rowItems.add(header);

            for (int i = 0; i < timeTable.timeTableSetupBundle.getLessonCount(); i++){
                CalendarTime currentTime = nextTime(i);
                TimeTableRowItem rowItem = new TimeTableRowItem(timeTable);
                rowItem.setStartTime(currentTime);
                for(int j = 0; j < DAYS_IN_WEEK; j++){
                    TimeTableItem item = new TimeTableItem(rowItem);
                    item.setSubject(randomString(5));
                    rowItem.getItems().add(item);
                }
                timeTable.rowItems.add(rowItem);
            }
        }

        private CalendarTime nextTime(int index) {
            int totalMinutes = timeTable.timeTableSetupBundle.getStartTime().totalMinutes();
            int nextTotalMinutes = totalMinutes + (timeTable.timeTableSetupBundle.getLessonLength() * index) +
                    (timeTable.timeTableSetupBundle.getBreakLength() * (index / timeTable.timeTableSetupBundle.getBreakInterval()));
            return new CalendarTime(nextTotalMinutes);
        }

        private String randomString(int length) {
            Random random = new Random();
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < length; i++) {
                builder.append((char)(random.nextInt(26) + 65));
            }
            return builder.toString();
        }
    }

}
