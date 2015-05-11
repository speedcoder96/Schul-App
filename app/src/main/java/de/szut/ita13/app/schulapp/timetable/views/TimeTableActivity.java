
package de.szut.ita13.app.schulapp.timetable.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.views.CalendarActivity;
import de.szut.ita13.app.schulapp.timetable.models.ITA13Model;
import de.szut.ita13.app.schulapp.timetable.container.TimeTable;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableColumnRange;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableSubject;
import de.szut.ita13.app.schulapp.utilities.DateUtilities;
import de.szut.ita13.app.schulapp.utilities.InvalidTimeException;


public class TimeTableActivity extends ActionBarActivity {

    public static final String TAG = TimeTableActivity.class.getSimpleName();

    private TimeTable timeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        timeTable = new TimeTable(this, new ITA13Model());
        try {
            timeTable.createTimeTable();
        } catch (InvalidTimeException e) {
            e.printStackTrace();
        }

        TimeTableColumnRange timeTableColumnRange  = timeTable.getTimeTableColumnRange(DateUtilities.DAY_FRIDAY, 0,3);
        timeTableColumnRange.setProperties("Mathe", "MAT", "202", "Engelke", R.color.yellow);
        timeTableColumnRange = timeTable.getTimeTableColumnRange(DateUtilities.DAY_THURSDAY, 0, 5);
        timeTableColumnRange.setProperties("Sport", "SPO", "TH", "Dünschede", R.color.green);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_time_table, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_new:
                openNew();
                return true;
            case R.id.action_edit:
                openEdit();
                return true;
            case R.id.action_remove:
                openRemove();
                return true;
            case R.id.action_overflow:
                openOverflow();
                return true;
            case R.id.action_settings:
                openSettings();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openNew() {
    }

    private void openEdit() {
    }

    private void openRemove() {
    }

    private void openOverflow() {
    }

    private void openSettings() {
    }

    public void clickedSubject(View view) {
        TextView textView = (TextView) view;
        TimeTableSubject timeTableSubject = (TimeTableSubject) textView.getTag();
        Toast.makeText(this, timeTableSubject.getInformation(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();

        intent.setClass(this.getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
    }
}