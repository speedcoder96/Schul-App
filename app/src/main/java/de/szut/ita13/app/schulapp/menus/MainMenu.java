package de.szut.ita13.app.schulapp.menus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.view.View;
import android.widget.ImageButton;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;
import de.szut.ita13.app.schulapp.timetable.TimeTableActivity;
import de.szut.ita13.app.schulapp.calendar.views.CalendarActivity;

/**
 * Created by Rene on 06.05.2015.
 */
public class MainMenu extends Activity implements View.OnClickListener {

    private static Class<?>[] MENU_ACTIVITY_CLASSES = {
            TimeTableActivity.class, CalendarActivity.class
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
            ImageButton button = (ImageButton)findViewById(BUTTON_IDS[i]);
            button.setOnClickListener(this);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        showConfirmDialog();
    }

    private void showConfirmDialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Beenden");
        alertDialogBuilder.setMessage("Wollen Sie die App wirklich beenden?");
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialogBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Process.killProcess(Process.myPid());
            }
        });
        alertDialogBuilder.setNegativeButton("Nein", null);
        alertDialogBuilder.create().show();
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < MENU_ACTIVITY_CLASSES.length; i++) {
            ImageButton button = (ImageButton)findViewById(BUTTON_IDS[i]);
            if(v == button) {
                Intent intent = new Intent();
                intent.setClass(this, MENU_ACTIVITY_CLASSES[i]);
                startActivity(intent);
                break;
            }
        }
    }
}
