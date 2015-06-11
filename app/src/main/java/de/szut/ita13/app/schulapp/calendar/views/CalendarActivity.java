package de.szut.ita13.app.schulapp.calendar.views;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;

/**
 * Created by Rene on 27.04.2015.
 */
public class CalendarActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Calendar(this);
    }

}
