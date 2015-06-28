package de.szut.ita13.app.schulapp.timetable;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Michelé on 28.06.2015.
 */
public class TimeTableLessonItem {
    private int id;
    private int row;
    private int column;
    private int week;
    private int subjectID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public static class ArrayListBuilder {
        public Hashtable<Integer, Hashtable<Integer, TimeTableLessonItem>> ArrayListBuilder(Cursor cursor) {
            cursor.moveToFirst();
            Hashtable<Integer, Hashtable<Integer, TimeTableLessonItem>> lessonItems = new Hashtable<>();
            while (!cursor.isAfterLast()) {
                TimeTableLessonItem item = new TimeTableLessonItem();
                item.setId(cursor.getInt(cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_ID2)));
                item.setColumn(cursor.getInt(cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_COLUMN)));
                item.setRow(cursor.getInt(cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_ROW)));
                item.setSubjectID(cursor.getInt(cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_SUBJECTID)));
                item.setWeek(cursor.getInt(cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_WEEK)));


                if (!(lessonItems.containsKey(item.getRow()))) {
                    lessonItems.put(item.getRow(), new Hashtable<Integer, TimeTableLessonItem>());
                    Log.d("ItemsPutNeu", item.getRow() + " |  " + item.getColumn());
                    lessonItems.get(item.getRow()).put(item.getColumn(), item);
                } else {
                    Log.d("ItemsPutAlt", item.getRow() + " |  " + item.getColumn());
                    lessonItems.get(item.getRow()).put(item.getColumn(), item);
                }

                cursor.moveToNext();
            }
            return lessonItems;
        }
    }
}
