package de.szut.ita13.app.schulapp.timetable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ramazan on 17.05.2015.
 */
public class TimeTableDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "timetable.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_SUBJECT = "Subjects";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COLORID = "colorId";
    public static final String COLUMN_TEACHER = "teacher";
    public static final String COLUMN_ROOM = "room";
    public static final String[] SUBJECT_ALL_COLUMNS = {
            COLUMN_ID, COLUMN_NAME, COLUMN_COLORID, COLUMN_TEACHER, COLUMN_ROOM
    };

    public static final String CREATE_TABLE_SUBJECT = "CREATE TABLE " + TABLE_SUBJECT + " ( " +
            COLUMN_ID + " integer primary key autoincrement," +
            COLUMN_NAME + " text not null," +
            COLUMN_COLORID + " integer not null," +
            COLUMN_TEACHER + " text not null," +
            COLUMN_ROOM + " text not null ); ";


    public static final String TABLE_TIMETABLE = "TimeTable";
    public static final String COLUMN_ID2 = "id";
    public static final String COLUMN_ROW = "row";
    public static final String COLUMN_COLUMN = "column";
    public static final String COLUMN_SUBJECTID = "subjectId";
    public static final String COLUMN_WEEK = "week";
    public static final String[] TIMETABLE_ALL_COLUMNS = {
            COLUMN_ID2, COLUMN_ROW, COLUMN_COLUMN, COLUMN_SUBJECTID, COLUMN_WEEK
    };

    public static final String CREATE_TABLE_TIMETABLE = "CREATE TABLE " + TABLE_TIMETABLE + " ( " +
            COLUMN_ID2 + " integer primary key autoincrement," +
            COLUMN_ROW + " integer not null," +
            COLUMN_COLUMN + " integer not null," +
            COLUMN_WEEK + " integer not null," +
            COLUMN_SUBJECTID + " integer not null ); ";


    public static final String TABLE_SETTINGS = "Settings";
    public static final String COLUMN_START_TIME = "starttime";
    public static final String COLUMN_LESSON_COUNT = "lessoncount";
    public static final String COLUMN_LESSON_LENGTH = "lessonlength";
    public static final String COLUMN_BREAK_LENGTH = "breaklength";
    public static final String COLUMN_BREAK_INTERVAL = "breakinterval";
    public static final String COLUMN_TWO_WEEKS = "twoweeks";
    public static final String[] SETTINGS_ALL_COLUMNS = {
            COLUMN_START_TIME, COLUMN_LESSON_COUNT, COLUMN_LESSON_LENGTH,
            COLUMN_BREAK_LENGTH, COLUMN_BREAK_INTERVAL, COLUMN_TWO_WEEKS
    };

    public static final String CREATE_TABLE_SETTINGS = "CREATE TABLE " + TABLE_SETTINGS + " ( " +
            COLUMN_START_TIME + " text not null," +
            COLUMN_LESSON_COUNT + " integer not null," +
            COLUMN_LESSON_LENGTH + " integer not null," +
            COLUMN_BREAK_LENGTH + " integer not null," +
            COLUMN_BREAK_INTERVAL + " integer not null," +
            COLUMN_TWO_WEEKS + " integer not null ); ";

    private Context context;
    public TimeTableDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SETTINGS);
        sqLiteDatabase.execSQL(CREATE_TABLE_TIMETABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_SUBJECT);
        Cursor c = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                Log.d("DatabaseHelper", "Table Name=> "+c.getString(0));
                c.moveToNext();
            }
        }
        Log.d("DatabaseHelper", "OnCreate");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d("DatabaseHelper", "OnOpen" + db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("DatabaseHelper", "OnUpgrade");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        onCreate(sqLiteDatabase);
    }
}
