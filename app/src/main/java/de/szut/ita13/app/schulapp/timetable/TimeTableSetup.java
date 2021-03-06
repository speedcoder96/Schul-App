package de.szut.ita13.app.schulapp.timetable;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTableSetup {

    public static final int REQUEST_CODE_SETUP = 1;

    private TimeTableActivity timeTableActivity;
    private ViewPager viewPager;
    private TimeTablePagerAdapter timeTablePagerAdapter;
    private TimeTableModifier timeTableModifier;

    public TimeTableSetup(TimeTableActivity timeTableActivity, TimeTableModifier timeTableModifier) {
        this.timeTableActivity = timeTableActivity;
        this.timeTableModifier = timeTableModifier;
        timeTableActivity.setContentView(R.layout.timetable_activity_layout);
        viewPager = (ViewPager) timeTableActivity.findViewById(R.id.pager);

        this.timeTableModifier.dataSource.open();
        if(!this.timeTableModifier.dataSource.areSettingsInDatabase()) {
            Intent intent = new Intent(timeTableActivity, TimeTableSetupActivity.class);
            timeTableActivity.startActivityForResult(intent, REQUEST_CODE_SETUP);
        } else {
            TimeTableSetupBundle bundle = timeTableModifier.dataSource.getSettings();
            generateTimetables(bundle);
        }
        this.timeTableModifier.dataSource.close();
    }

    public void onReceiveSetupBundle(TimeTableSetupBundle timeTableSetupBundle) {
        generateTimetables(timeTableSetupBundle);
        timeTableModifier.dataSource.open();
        timeTableModifier.dataSource.saveSettings(timeTableSetupBundle);
        timeTableModifier.dataSource.close();
    }

    private void generateTimetables(TimeTableSetupBundle timeTableSetupBundle) {
        int timeTableCount = (timeTableSetupBundle.isTwoWeeksRhythm()) ? 2 : 1;
        for(int i = 0; i < timeTableCount; i++) {
            timeTableModifier.addTimeTable(new TimeTable(timeTableSetupBundle, "Woche " + (char) (65 + i)));
        }
        timeTablePagerAdapter = new TimeTablePagerAdapter(timeTableActivity.getSupportFragmentManager(),
                timeTableActivity, timeTableModifier);
        viewPager.setAdapter(timeTablePagerAdapter);
    }

}
