package de.szut.ita13.app.schulapp.calendar.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rama on 17.05.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static enum Appointments {

        ID, TITLE, DATE, START, END, NOTE;

        public static final String DATABASE_NAME = "database.db";
        public static final int DATABASE_VERSION = 1;

        public static String TABLE_NAME = "appointments";
        public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " " +
                ID.name() + "," + TITLE.name() + "," + DATE.name() + "," + START.name() + "," +
                END.name() + "," + NOTE.name();

        public static String[] names() {
            Appointments[] appointments = values();
            String[] names = new String[appointments.length];
            for (int i = 0; i < appointments.length; i++) {
                names[i] = appointments[i].name();
            }
            return names;
        }
    }

    public DatabaseHelper(Context context) {
        super(context, Appointments.DATABASE_NAME, null, Appointments.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(Appointments.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Appointments.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
