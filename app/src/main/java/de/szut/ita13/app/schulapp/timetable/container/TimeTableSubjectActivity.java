package de.szut.ita13.app.schulapp.timetable.container;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.adapter.TimeTableSubjectAdapter;

/**
 * Created by Michel� on 15.06.2015.
 */
public class TimeTableSubjectActivity extends ActionBarActivity {

    Button dfragbutton;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        dfragbutton = (Button) findViewById(R.id.dfragbutton);

        // Capture button clicks
        dfragbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                DFragment dFragment = new DFragment(TimeTableSubjectActivity.this);
                // Show DialogFragment
                dFragment.show(fm, "Dialog Fragment");
            }
        });
    }
}
