package de.szut.ita13.app.schulapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.szut.ita13.app.schulapp.DatabaseRequest;
import de.szut.ita13.app.schulapp.entity.Day;

/**
 * Created by Chris on 28.04.2015.
 */
public class DayDataSource implements DataSource {

    // Database fields
    private SQLiteDatabase database;
    private DatabaseRequest dbHelper;
    private String[] allColumns = { DatabaseRequest.TABLE_DAY, DatabaseRequest.COLUMN_DAY_ID,
            DatabaseRequest.COLUMN_TIMEID, DatabaseRequest.COLUMN_SUBJECT, DatabaseRequest.COLUMN_SHORTCUT,
            DatabaseRequest.COLUMN_TEACHER, DatabaseRequest.COLUMN_ROOM };

    public DayDataSource(Context context) {
        dbHelper = new DatabaseRequest(context);
    }

    @Override
    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    public Day createTest(String day) {
        ContentValues values = new ContentValues();
        values.put(DatabaseRequest.COLUMN_DAY_ID, day);
        values.put(DatabaseRequest.COLUMN_TIMEID, day);
        values.put(DatabaseRequest.COLUMN_SUBJECT, day);
        values.put(DatabaseRequest.COLUMN_SHORTCUT, day);
        values.put(DatabaseRequest.COLUMN_TEACHER, day);
        values.put(DatabaseRequest.COLUMN_ROOM, day);
        long insertId = database.insert(DatabaseRequest.TABLE_DAY, null,
                values);
        Cursor cursor = database.query(DatabaseRequest.TABLE_DAY,
                allColumns, DatabaseRequest.COLUMN_DAY_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Day newDay = cursorToDay(cursor);
        cursor.close();
        return newDay;
    }

    public void delete (Day dayObject) {
        long id = dayObject.getId();
        long timeId = dayObject.getTimeId();
        String day = dayObject.getDay();
        String subject = dayObject.getSubject();
        String shortcut = dayObject.getShortcut();
        String teacher = dayObject.getTeacher();
        String room = dayObject.getRoom();
        database.delete(DatabaseRequest.TABLE_DAY, DatabaseRequest.COLUMN_DAY_ID
                + " = " + id, null);
    }

    public List<Day> getAllNames() {
        List<Day> tests = new ArrayList<Day>();

        Cursor cursor = database.query(DatabaseRequest.TABLE_DAY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Day day = cursorToDay(cursor);
            tests.add(day);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return tests;
    }

    private Day cursorToDay(Cursor cursor) {
        Day day = new Day();
        day.setId(cursor.getLong(0));
        day.setDay(cursor.getString(1));
        day.setSubject(cursor.getString(2));
        day.setShortcut(cursor.getString(3));
        day.setTeacher(cursor.getString(4));
        day.setRoom(cursor.getString(5));
        return day;
    }
}
