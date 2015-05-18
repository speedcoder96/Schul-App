package de.szut.ita13.app.schulapp.calendar.views;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;
import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by Rene on 27.04.2015.
 */
public class CalendarActivity extends ActionBarActivity {

    public static final String TAGS = CalendarActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar calendar = new Calendar(this);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
