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
    private PagerTitleStrip pagerTitleStrap;
    private TimeTablePagerAdapter timeTablePagerAdapter;
    private TimeTableDataSource dataSource;

    public TimeTableSetup(TimeTableActivity timeTableActivity) {
        this.timeTableActivity = timeTableActivity;
        timeTableActivity.setContentView(R.layout.timetable_activity_layout);
        viewPager = (ViewPager) timeTableActivity.findViewById(R.id.pager);
        pagerTitleStrap = (PagerTitleStrip) timeTableActivity.findViewById(R.id.pager_title_strip);
        dataSource = new TimeTableDataSource(timeTableActivity);
        Intent intent = new Intent(timeTableActivity, TimeTableSetupActivity.class);
        timeTableActivity.startActivityForResult(intent, REQUEST_CODE_SETUP);
    }

    public void onReceiveSetupBundle(TimeTableSetupBundle timeTableSetupBundle) {
        generateTimetables(timeTableSetupBundle);
        dataSource.saveSettings(timeTableSetupBundle);
    }

    private void generateTimetables(TimeTableSetupBundle timeTableSetupBundle) {
        int timeTableCount = (timeTableSetupBundle.isTwoWeeksRhythm()) ? 2 : 1;
        ArrayList<TimeTable> timeTables = new ArrayList<>();
        for(int i = 0; i < timeTableCount; i++) {
            timeTables.add(new TimeTable(timeTableSetupBundle, "Woche " + (char)(65 + i)));
        }
        timeTablePagerAdapter = new TimeTablePagerAdapter(timeTableActivity.getSupportFragmentManager(), timeTableActivity, timeTables);
        viewPager.setAdapter(timeTablePagerAdapter);
    }

}
