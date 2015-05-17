package de.szut.ita13.app.schulapp.calendar.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rama on 17.05.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "appointments";
    public static final String ID = "id";

    public static enum Appointments {

        ID, TITLE, DATE, START, END, NOTE;

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
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
