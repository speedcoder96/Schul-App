package de.szut.ita13.app.schulapp;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;
import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by Michelé on 21.06.2015.
 */
public class TimeTable {

    private ArrayList<RowItem> rowItems;
    private TimeTableSetupBundle timeTableSetupBundle;

    public TimeTable(TimeTableSetupBundle timeTableSetupBundle){
        this.rowItems = new ArrayList<>();
        this.timeTableSetupBundle = timeTableSetupBundle;
        new TimeTable.Builder(this).build();
    }

    public int getRowCount(){
        return rowItems.size();
    }

    public RowItem getRowItem(int index){
        return rowItems.get(index);
    }

    public static class RowItem{

        public final static int[] IDS = {
                R.id.time, R.id.monday, R.id.tuesday, R.id.wednesday, R.id.thursday, R.id.friday
        };
        public final static String[] LABELS = {
                "Zeit", "Mo", "Di", "Mi", "Do", "Fr"
        };

        private ArrayList<Item> items;
        private TimeTable timeTable;
        private CalendarTime startTime;
        private CalendarTime endTime;
        private boolean header;
        private boolean now;

        public RowItem(TimeTable timeTable){
            this.timeTable = timeTable;
            items = new ArrayList<>();
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public void setItems(ArrayList<Item> items) {
            this.items = items;
        }

        public TimeTable getTimeTable() {
            return timeTable;
        }

        public void setTimeTable(TimeTable timeTable) {
            this.timeTable = timeTable;
        }

        public CalendarTime getStartTime() {
            return startTime;
        }

        public void setStartTime(CalendarTime startTime) {
            this.startTime = startTime;
        }

        public CalendarTime getEndTime() {
            return endTime;
        }

        public void setEndTime(CalendarTime endTime) {
            this.endTime = endTime;
        }

        public boolean isHeader() {
            return header;
        }

        public void setHeader(boolean header) {
            this.header = header;
        }

        public boolean isNow() {
            return now;
        }

        public void setNow(boolean now) {
            this.now = now;
        }
    }

    public static class Item{

        private int id;
        private RowItem rowItem;
        private String subject;
        private String room;
        private String teacher;
        private int colorId;


        public Item(RowItem rowItem){
            this.rowItem = rowItem;
        }

        public String getInformation(){
            return subject + "\n" + room;
        }

        public int getID() {
            return id;
        }

        public void setID(int id) {
            this.id = id;
        }

        public RowItem getRowItem() {
            return rowItem;
        }

        public void setRowItem(RowItem rowItem) {
            this.rowItem = rowItem;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public int getColorId() {
            return colorId;
        }

        public void setColorId(int colorId) {
            this.colorId = colorId;
        }
    }

    public static class Builder{

        private TimeTable timeTable;

        public Builder (TimeTable timeTable){
            this.timeTable = timeTable;
        }

        public void build(){

            RowItem header = new RowItem(timeTable);
            header.setHeader(true);
            timeTable.rowItems.add(header);

            for (int i = 0; i < timeTable.timeTableSetupBundle.getLessonCount(); i++){
                CalendarTime currentTime = next(i);
                RowItem rowItem = new RowItem(timeTable);
                rowItem.setStartTime(currentTime);
                for(int j = 0; j < 5; j++){
                    Item item = new Item(rowItem);
                    rowItem.items.add(item);
                }
                timeTable.rowItems.add(rowItem);
            }
        }

        private CalendarTime next(int index){
            int totalMinutes = timeTable.timeTableSetupBundle.getStartTime().totalMinutes();
            int nextTotalMinutes = totalMinutes + (timeTable.timeTableSetupBundle.getLessonLength() * index) +
                    (timeTable.timeTableSetupBundle.getBreakLength() * (index / timeTable.timeTableSetupBundle.getBreakInterval()));
            return new CalendarTime(nextTotalMinutes);
        }
    }

}
