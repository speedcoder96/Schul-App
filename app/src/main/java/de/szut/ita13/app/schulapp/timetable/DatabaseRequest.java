package de.szut.ita13.app.schulapp.timetable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chris on 28.04.2015.
 */
public class DatabaseRequest extends SQLiteOpenHelper{

    public static final String TABLE_TIME = "time";
    public static final String COLUMN_TIME_ID = "id";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_END = "end";
    public static final String COLUMN_PAUSE = "pause";

    public static final String TABLE_DAY = "day";
    public static final String COLUMN_DAY_ID = "id";
    public static final String COLUMN_TIMEID = "timeId";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_SHORTCUT = "shortcut";
    public static final String COLUMN_TEACHER = "teacher";
    public static final String COLUMN_ROOM = "room";

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_TABLE_TIME = "create table "
            + TABLE_TIME + "("
            + COLUMN_TIME_ID + " integer primary key autoincrement, "
            + COLUMN_START + " text not null, "
            + COLUMN_END + " text not null, "
            + COLUMN_PAUSE + " text not null);";

    private static final String CREATE_TABLE_DAY = "create table "
            + TABLE_DAY + "("
            + COLUMN_DAY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TIMEID + "text not null, "
            + COLUMN_SUBJECT + "text not null, "
            + COLUMN_SHORTCUT + "text not null, "
            + COLUMN_TEACHER + "text not null, "
            + COLUMN_ROOM + "text not null);"
            + "FOREIGN KEY (" + COLUMN_TIMEID + ") REFERENCES"
            + TABLE_TIME + "( " + COLUMN_TIME_ID + ");";

    public DatabaseRequest(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TIME + CREATE_TABLE_DAY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseRequest.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAY);
        onCreate(db);
    }
}
