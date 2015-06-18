package de.szut.ita13.app.schulapp.timetable.container;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.adapter.TimeTableSubjectAdapter;

/**
 * Created by Michel� on 15.06.2015.
 */
public class TimeTableSubjectActivity extends ActionBarActivity {

    Button dfragbutton;
    private FragmentManager fm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();

        setContentView(R.layout.activity_main);
        dfragbutton = (Button) findViewById(R.id.dfragbutton);

        dfragbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                DFragment dFragment = new DFragment(TimeTableSubjectActivity.this);
                // Show DialogFragment
                dFragment.show(fm, "Dialog Fragment");
            }
        });
    }

}
