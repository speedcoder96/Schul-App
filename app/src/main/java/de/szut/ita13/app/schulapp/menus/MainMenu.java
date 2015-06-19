package de.szut.ita13.app.schulapp.menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.views.CalendarActivity;
import de.szut.ita13.app.schulapp.TimeTableSetupActivity;

/**
 * Created by Rene on 06.05.2015.
 */
public class MainMenu extends Activity implements View.OnClickListener {

    private static Class<?>[] MENU_ACTIVITY_CLASSES = {
            TimeTableSetupActivity.class, CalendarActivity.class
    };

    private static int[] MENU_NAME_ID = {
            R.string.timetable_menu_name, R.string.calendar_menu_name
    };

    private static int[] BUTTON_IDS = {
            R.id.menu_button_timetable, R.id.menu_button_calendar
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.menu_activity_layout);
        for(int i = 0; i < BUTTON_IDS.length; i++) {
            Button button = (Button)findViewById(BUTTON_IDS[i]);
            button.setOnClickListener(this);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < MENU_ACTIVITY_CLASSES.length; i++) {
            Button button = (Button)findViewById(BUTTON_IDS[i]);
            if(v == button) {
                Intent intent = new Intent();
                intent.setClass(this, MENU_ACTIVITY_CLASSES[i]);
                startActivity(intent);
                break;
            }
        }
    }
}
