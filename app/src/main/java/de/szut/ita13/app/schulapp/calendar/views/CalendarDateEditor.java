package de.szut.ita13.app.schulapp.calendar.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;

/**
 * Created by Rene on 11.05.2015.
 */
public class CalendarDateEditor extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendardate_editor);
        Intent intent = getIntent();
        CalendarDate calendarDate = (CalendarDate)intent.getSerializableExtra(CalendarDate.SERIALIZABLE_KEY);

    }
}
