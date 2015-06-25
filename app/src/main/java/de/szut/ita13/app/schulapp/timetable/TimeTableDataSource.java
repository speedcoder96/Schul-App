package de.szut.ita13.app.schulapp.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;
import de.szut.ita13.app.schulapp.calendar.dao.DatabaseHelper;


/**
 * Created by Rene on 19.06.2015.
 */
public class TimeTableDataSource {


    private SQLiteDatabase database;
    private TimeTableDatabaseHelper dbHelper;

    public TimeTableDataSource(Context context) {
        dbHelper = new TimeTableDatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public boolean saveSettings(TimeTableSetupBundle bundle) {
        boolean settingsInDatabase = areSettingsInDatabase();
        if(!settingsInDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TimeTableDatabaseHelper.COLUMN_START_TIME, bundle.getStartTime().getTimeString());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_LESSON_COUNT, bundle.getLessonCount());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_LESSON_LENGTH, bundle.getLessonLength());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_BREAK_LENGTH, bundle.getBreakLength());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_BREAK_INTERVAL, bundle.getBreakInterval());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_TWO_WEEKS, (bundle.isTwoWeeksRhythm()) ? 2 : 1);
            long id = database.insert(TimeTableDatabaseHelper.TABLE_SETTINGS, null, contentValues);
            Log.d("TimeTableDataSource", "Save Settings");
        } else {
            Log.d("TimeTableDataSource", "Settings already exists");
        }
        return settingsInDatabase;
    }

    public boolean areSettingsInDatabase() {
        String selectQuery = "SELECT * FROM " + TimeTableDatabaseHelper.TABLE_SETTINGS + ";";
        Cursor cursor = database.rawQuery(selectQuery, new String[]{});
        boolean settingsInDatabase = cursor.getCount() == 1;
        return settingsInDatabase;
    }

    public TimeTableSetupBundle getSettings() {
        String selectQuery = "SELECT * FROM " + TimeTableDatabaseHelper.TABLE_SETTINGS + ";";
        Cursor cursor = database.rawQuery(selectQuery, new String[]{});
        TimeTableSetupBundle bundle = null;
        if(cursor.getCount() >= 1) {
            cursor.moveToFirst();
            bundle = new TimeTableSetupBundle.Builder()
                    .setStartTime(new CalendarTime(cursor.getString(
                            cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_START_TIME))))
                    .setLessonCount(cursor.getInt(
                            cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_LESSON_COUNT)))
                    .setLessonLength(cursor.getInt(
                            cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_LESSON_LENGTH)))
                    .setBreakLength(cursor.getInt(
                            cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_BREAK_LENGTH)))
                    .setBreakInterval(cursor.getInt(
                            cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_BREAK_INTERVAL)))
                    .setTwoWeeksRhythm(cursor.getInt(
                            cursor.getColumnIndex(TimeTableDatabaseHelper.COLUMN_TWO_WEEKS)) == 2)
                    .build();

        }
        return bundle;
    }

    public ArrayList<TimeTableItem> getItems() {
        String selectQuery = "SELECT * FROM " + TimeTableDatabaseHelper.TABLE_SUBJECT + ";";
        Cursor cursor = database.rawQuery(selectQuery, new String[]{});
        TimeTableItem.ArrayListBuilder builder = new TimeTableItem.ArrayListBuilder(cursor);
        return builder.build();
    }


    public void insertTimetableItem(TimeTableItem item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimeTableDatabaseHelper.COLUMN_NAME, item.getSubject());
        contentValues.put(TimeTableDatabaseHelper.COLUMN_COLORID, item.getColorId());
        contentValues.put(TimeTableDatabaseHelper.COLUMN_TEACHER, item.getTeacher());
        contentValues.put(TimeTableDatabaseHelper.COLUMN_ROOM, item.getRoom());
        long id = database.insert(TimeTableDatabaseHelper.TABLE_SUBJECT, null, contentValues);
    }

    public void updateTimetableItem(TimeTableItem item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimeTableDatabaseHelper.COLUMN_NAME, item.getSubject());
        contentValues.put(TimeTableDatabaseHelper.COLUMN_COLORID, item.getColorId());
        contentValues.put(TimeTableDatabaseHelper.COLUMN_TEACHER, item.getTeacher());
        contentValues.put(TimeTableDatabaseHelper.COLUMN_ROOM, item.getRoom());
        database.update(TimeTableDatabaseHelper.TABLE_SUBJECT, contentValues,
                TimeTableDatabaseHelper.COLUMN_ID + " = " + item.getID(), null);
    }

    public void deleteTimeTableItem(TimeTableItem item) {
        database.delete(TimeTableDatabaseHelper.TABLE_SUBJECT, TimeTableDatabaseHelper.COLUMN_ID
                + " = " + item.getID(), null);
    }


}
