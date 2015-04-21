
package de.szut.ita13.app.schulapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickedSubject(View view) {
        TextView textView = (TextView) view;
        TimeTableSubject timeTableSubject = (TimeTableSubject) textView.getTag();
        Toast.makeText(this, timeTableSubject.getInformation(), Toast.LENGTH_SHORT).show();
    }
}