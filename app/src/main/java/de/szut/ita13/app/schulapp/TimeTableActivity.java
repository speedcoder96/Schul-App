package de.szut.ita13.app.schulapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


/**
 * Created by Rene on 19.06.2015.
 */
public class TimeTableActivity extends FragmentActivity implements View.OnClickListener {

    private TimeTableSetup timeTableSetup;
    public static TimeTableModifier timeTableModifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeTableModifier = new TimeTableModifier(this);
        timeTableSetup = new TimeTableSetup(this, timeTableModifier);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_timetable_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TimeTableSetupBundle bundle = new TimeTableSetupBundle.Builder(data)
                .build();
        timeTableSetup.onReceiveSetupBundle(bundle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        TimeTableItem item = (TimeTableItem)view.getTag();
        Log.d("TimeTableActivity", "Information" + item.getInformation());
    }


}
