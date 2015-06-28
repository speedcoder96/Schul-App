package de.szut.ita13.app.schulapp.timetable;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTableItem{

    private int id;
    private TimeTableRowItem rowItem;
    private String subject;
    private String room;
    private String teacher;
    private int colorId;


    public TimeTableItem(TimeTableRowItem rowItem){
        this.rowItem = rowItem;
    }

    public String getInformation(){
        return subject + "\n" + room;
    }

    public int getID() {
        return id;
    }

    public TimeTableRowItem getRowItem() {
        return rowItem;
    }

    public String getSubject() {
        return subject;
    }

    public String getRoom() {
        return room;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getColorId() {
        return colorId;
    }


    public static class Builder {

        private TimeTableItem item;

        public Builder(TimeTableRowItem rowItem) {
            item = new TimeTableItem(rowItem);
        }

        public Builder setSubject(String subject) {
            item.subject = subject;
            return this;
        }

        public Builder setRoom(String room) {
            item.room = room;
            return this;
        }

        public Builder setTeacher(String teacher) {
            item.teacher = teacher;
            return this;
        }

        public Builder setID(int id) {
            item.id = id;
            return this;
        }

        public Builder setColorID(int colorId) {
            item.colorId = colorId;
            return this;
        }

        public Builder setRowItem(TimeTableRowItem rowItem) {
            item.rowItem = rowItem;
            return this;
        }

        public TimeTableItem build() {
            return item;
        }

    }

    public static class ArrayListBuilder {

        private ArrayList<TimeTableItem> items;
        private Cursor cursor;

        public ArrayListBuilder(Cursor cursor) {
            this.cursor = cursor;
            items = new ArrayList<TimeTableItem>();
        }

        public ArrayList<TimeTableItem> build() {
            TimeTableActivity.subjectMap = new HashMap<>();
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                TimeTableItem item = new TimeTableItem.Builder(null)
                        .setID(cursor.getInt(
                                cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_ID)))
                        .setRoom(cursor.getString(
                                cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_ROOM)))
                        .setColorID(cursor.getInt(
                                cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_COLORID)))
                        .setTeacher(cursor.getString(
                                cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_TEACHER)))
                        .setSubject(cursor.getString(
                                cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_NAME)))
                        .build();
                items.add(item);
                TimeTableActivity.subjectMap.put(item.getID(), item);
                cursor.moveToNext();
            }
            return items;
        }

    }
}
