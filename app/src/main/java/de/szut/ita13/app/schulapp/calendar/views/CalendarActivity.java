package de.szut.ita13.app.schulapp.calendar.views;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;
import de.szut.ita13.app.schulapp.menus.MainMenu;

/**
 * Created by Rene on 27.04.2015.
 */
public class CalendarActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Calendar(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this.getApplicationContext(), MainMenu.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}
