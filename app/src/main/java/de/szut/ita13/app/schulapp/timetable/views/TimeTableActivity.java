
package de.szut.ita13.app.schulapp.timetable.views;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.views.CalendarActivity;
import de.szut.ita13.app.schulapp.timetable.dao.TimeDataSource;
import de.szut.ita13.app.schulapp.timetable.entity.Time;
import de.szut.ita13.app.schulapp.timetable.models.ITA13Model;
import de.szut.ita13.app.schulapp.timetable.container.TimeTable;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableColumnRange;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableSubject;
import de.szut.ita13.app.schulapp.utilities.DateUtilities;
import de.szut.ita13.app.schulapp.utilities.InvalidTimeException;


public class TimeTableActivity extends ActionBarActivity {

    public static final String TAG = TimeTableActivity.class.getSimpleName();

    private TimeTable timeTable;
    private TimeDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        timeTable = new TimeTable(this, new ITA13Model());
        try {
            timeTable.createTimeTable();
        } catch (InvalidTimeException e) {
            e.printStackTrace();
        }

        TimeTableColumnRange timeTableColumnRange  = timeTable.getTimeTableColumnRange(DateUtilities.DAY_FRIDAY, 0,3);
        timeTableColumnRange.setProperties("Mathe", "MAT", "202", "Engelke", R.color.yellow);
        timeTableColumnRange = timeTable.getTimeTableColumnRange(DateUtilities.DAY_THURSDAY, 0, 5);
        timeTableColumnRange.setProperties("Sport", "SPO", "TH", "Dünschede", R.color.red);

        ListView lv = (ListView) findViewById(R.id.timetableDatabase);
        datasource = new TimeDataSource(this);
        datasource.open();

        List<Time> values = datasource.getAllNames();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this,
                android.R.layout.simple_list_item_1, values);
        lv.setAdapter(adapter);
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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