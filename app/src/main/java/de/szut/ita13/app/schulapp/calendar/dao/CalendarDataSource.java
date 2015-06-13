package de.szut.ita13.app.schulapp.calendar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by ramazan on 19.05.2015.
 */
public class CalendarDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public CalendarDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }

    public CalendarAppointment selectAppointmentRange(int currentMonth, int currentYear) {
        String[] range = DateUtil.getRange(currentMonth, currentYear);
        String selectQuery = "SELECT * FROM "+ DatabaseHelper.Appointments.TABLE_NAME +
                " WHERE date(" + DatabaseHelper.Appointments.DATE.name() + ", YYYY-MM-DD) BETWEEN " +
                "date(" + range[0] + ", YYYY-MM-DD) AND date(" + range[1] + ", YYYY-MM-DD);";
        Cursor cursor = database.rawQuery(selectQuery, null);

        return null;
    }

    public void insertAppointment(CalendarAppointment appointment) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Appointments.TITLE.name(),appointment.getSubject());
        values.put(DatabaseHelper.Appointments.NOTE.name(),appointment.getNote());
        values.put(DatabaseHelper.Appointments.DATE.name(), appointment.getCalendarDate().getDateString(CalendarDate.DATABASE_DATE_FORMAT));
        values.put(DatabaseHelper.Appointments.START.name(), appointment.getStartTime().getTimeString());
        values.put(DatabaseHelper.Appointments.END.name(), appointment.getEndTime().getTimeString());
        long id = database.insert(DatabaseHelper.Appointments.TABLE_NAME,null,values);
        appointment.setRefID(id);
    }
    public void updateAppointment(CalendarAppointment appointment){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Appointments.TITLE.name(),appointment.getSubject());
        values.put(DatabaseHelper.Appointments.NOTE.name(),appointment.getNote());
        values.put(DatabaseHelper.Appointments.DATE.name(), appointment.getCalendarDate().getDateString(CalendarDate.DATABASE_DATE_FORMAT));
        values.put(DatabaseHelper.Appointments.START.name(), appointment.getStartTime().getTimeString());
        values.put(DatabaseHelper.Appointments.END.name(), appointment.getEndTime().getTimeString());
        database.update(DatabaseHelper.Appointments.TABLE_NAME,values,DatabaseHelper.Appointments.ID.name()
               + " = " + appointment.getRefID(),null);

    }
    public void deleteAppointment(long id){
        database.delete(DatabaseHelper.Appointments.TABLE_NAME, DatabaseHelper.Appointments.ID.name()
                + " = " + id, null);
    }


}
