package de.szut.ita13.app.schulapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * Created by Rene on 19.06.2015.
 */
public class TimeTableActivity extends FragmentActivity {

    private TimeTableSetup timeTableSetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeTableSetup = new TimeTableSetup(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TimeTableSetupBundle bundle = new TimeTableSetupBundle.Builder(data)
                .build();
        timeTableSetup.onReceiveSetupBundle(bundle);
    }
}
