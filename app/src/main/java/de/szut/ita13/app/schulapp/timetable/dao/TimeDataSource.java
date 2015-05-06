package de.szut.ita13.app.schulapp.timetable.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.szut.ita13.app.schulapp.timetable.DatabaseRequest;
import de.szut.ita13.app.schulapp.timetable.entity.Day;
import de.szut.ita13.app.schulapp.timetable.entity.Time;

/**
 * Created by Chris on 28.04.2015.
 */
public class TimeDataSource implements DataSource {

    // Database fields
    private SQLiteDatabase database;
    private DatabaseRequest dbHelper;
    private String[] allColumns = { DatabaseRequest.COLUMN_TIME_ID, DatabaseRequest.COLUMN_START,
            DatabaseRequest.COLUMN_END, DatabaseRequest.COLUMN_PAUSE };

    public TimeDataSource(Context context) {
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

    public Time createTest(String time) {
        ContentValues values = new ContentValues();
        values.put(DatabaseRequest.COLUMN_TIME_ID, time);
        values.put(DatabaseRequest.COLUMN_START, time);
        values.put(DatabaseRequest.COLUMN_END, time);
        values.put(DatabaseRequest.COLUMN_PAUSE, time);
        long insertId = database.insert(DatabaseRequest.TABLE_TIME, null,
                values);
        Cursor cursor = database.query(DatabaseRequest.TABLE_TIME,
                allColumns, DatabaseRequest.COLUMN_TIME_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Time newTime = cursorToTime(cursor);
        cursor.close();
        return newTime;
    }

    public void delete (Time timeObject) {
        long id = timeObject.getId();
        String start = timeObject.getStart();
        String end = timeObject.getEnd();
        String pause = timeObject.getPause();
        database.delete(DatabaseRequest.TABLE_TIME, DatabaseRequest.COLUMN_TIME_ID
                + " = " + id, null);
    }

    public List<Time> getAllNames() {
        List<Time> tests = new ArrayList<Time>();

        Cursor cursor = database.query(DatabaseRequest.TABLE_TIME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Time time = cursorToTime(cursor);
            tests.add(time);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return tests;
    }

    private Time cursorToTime(Cursor cursor) {
        Time time = new Time();
        time.setId(cursor.getLong(0));
        time.setStart(cursor.getString(1));
        time.setEnd(cursor.getString(2));
        time.setPause(cursor.getString(3));
        return time;
    }
}
