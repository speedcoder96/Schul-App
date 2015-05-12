package de.szut.ita13.app.schulapp.calendar.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import de.szut.ita13.app.schulapp.R;


/**
 * Created by ramazan and rene on 12.05.2015.
 */
public class CalendarTimePicker extends View {
    public CalendarTimePicker(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_timepicker,null);
    }
}
