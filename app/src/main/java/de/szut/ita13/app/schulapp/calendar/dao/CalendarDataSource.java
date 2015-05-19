package de.szut.ita13.app.schulapp.calendar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;

/**
 * Created by ramazan on 19.05.2015.
 */
public class CalendarDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public CalendarDataSource(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }

    public CalendarAppointment selectAppointment(int uniqueID){
        String selectQuery = "SELECT * FROM"+ DatabaseHelper.Appointments.TABLE_NAME + "WHERE=?";
        Cursor cursor = database.rawQuery(selectQuery,new String[uniqueID]);
        String dateString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Appointments.DATE.name()));
        String[] date = dateString.split("");
   //     CalendarAppointment appointment = new CalendarAppointment(new CalendarDate());



        return null;
    }

    public void insertAppointment(CalendarAppointment appointment){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Appointments.ID.name(),"");
        values.put(DatabaseHelper.Appointments.TITLE.name(),appointment.getSubject());
        values.put(DatabaseHelper.Appointments.NOTE.name(),appointment.getNote());
        values.put(DatabaseHelper.Appointments.DATE.name(), appointment.getCalendarDate().getDateString());
        values.put(DatabaseHelper.Appointments.START.name(), appointment.getStartTime().getTimeString());
        values.put(DatabaseHelper.Appointments.END.name(), appointment.getEndTime().getTimeString());
        database.insert(DatabaseHelper.Appointments.TABLE_NAME,null,values);
    }
    public void updateAppointment(CalendarAppointment appointment){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Appointments.ID.name(),"");
        values.put(DatabaseHelper.Appointments.TITLE.name(),appointment.getSubject());
        values.put(DatabaseHelper.Appointments.NOTE.name(),appointment.getNote());
        values.put(DatabaseHelper.Appointments.DATE.name(), appointment.getCalendarDate().getDateString());
        values.put(DatabaseHelper.Appointments.START.name(), appointment.getStartTime().getTimeString());
        values.put(DatabaseHelper.Appointments.END.name(), appointment.getEndTime().getTimeString());

  //      database.update(DatabaseHelper.Appointments.TABLE_NAME,values,DatabaseHelper.Appointments.ID.name()
  //              + " = " + appointment.getRefID(),null);

    }
    public void deleteAppointment(int uniqueID){
        database.delete(DatabaseHelper.Appointments.TABLE_NAME, DatabaseHelper.Appointments.ID.name()
                + " = " + uniqueID, null);

    }
}
