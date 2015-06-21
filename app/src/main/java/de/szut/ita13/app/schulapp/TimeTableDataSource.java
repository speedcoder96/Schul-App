package de.szut.ita13.app.schulapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        open();
        boolean settingsInDatabase = isSettingsInDatabase();
        if(!settingsInDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TimeTableDatabaseHelper.COLUMN_START_TIME, bundle.getStartTime().getTimeString());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_LESSON_COUNT, bundle.getLessonCount());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_LESSON_LENGTH, bundle.getLessonLength());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_BREAK_LENGTH, bundle.getBreakLength());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_BREAK_INTERVAL, bundle.getBreakInterval());
            contentValues.put(TimeTableDatabaseHelper.COLUMN_TWO_WEEKS, (bundle.isTwoWeeksRhythm()) ? 2 : 1);
            long id = database.insert(TimeTableDatabaseHelper.TABLE_SETTINGS, null, contentValues);
            close();
        }
        return settingsInDatabase;
    }

    public boolean isSettingsInDatabase() {
        open();
        String selectQuery = "SELECT * FROM " + TimeTableDatabaseHelper.TABLE_SETTINGS + ";";
        Cursor cursor = database.rawQuery(selectQuery, new String[]{});
        close();
        return cursor.getCount() == 1;
    }


}
