package de.szut.ita13.app.schulapp;

import android.content.Intent;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTableSetup {

    public static final int REQUEST_CODE_SETUP = 1;

    private TimeTableActivity timeTableActivity;
    private ViewPager viewPager;
    private TimeTablePagerAdapter timeTablePagerAdapter;
    private TimeTableDataSource dataSource;
    private TimeTableModifier timeTableModifier;

    public TimeTableSetup(TimeTableActivity timeTableActivity, TimeTableModifier timeTableModifier) {
        this.timeTableActivity = timeTableActivity;
        this.timeTableModifier = timeTableModifier;
        timeTableActivity.setContentView(R.layout.timetable_activity_layout);
        viewPager = (ViewPager) timeTableActivity.findViewById(R.id.pager);
        dataSource = new TimeTableDataSource(timeTableActivity);
        dataSource.open();
        if(!dataSource.areSettingsInDatabase()) {
            Intent intent = new Intent(timeTableActivity, TimeTableSetupActivity.class);
            timeTableActivity.startActivityForResult(intent, REQUEST_CODE_SETUP);
        } else {
            TimeTableSetupBundle bundle = dataSource.getSettings();
            generateTimetables(bundle);
        }
        dataSource.close();
    }

    public void onReceiveSetupBundle(TimeTableSetupBundle timeTableSetupBundle) {
        generateTimetables(timeTableSetupBundle);
        dataSource.open();
        dataSource.saveSettings(timeTableSetupBundle);
        dataSource.close();
    }

    private void generateTimetables(TimeTableSetupBundle timeTableSetupBundle) {
        int timeTableCount = (timeTableSetupBundle.isTwoWeeksRhythm()) ? 2 : 1;
        for(int i = 0; i < timeTableCount; i++) {
            timeTableModifier.addTimeTable(new TimeTable(timeTableSetupBundle, "Woche " + (char)(65 + i)));
        }
        timeTablePagerAdapter = new TimeTablePagerAdapter(timeTableActivity.getSupportFragmentManager(),
                timeTableActivity, timeTableModifier);
        viewPager.setAdapter(timeTablePagerAdapter);
    }

}
