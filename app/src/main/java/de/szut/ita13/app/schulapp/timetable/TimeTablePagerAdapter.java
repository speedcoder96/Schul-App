package de.szut.ita13.app.schulapp.timetable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTablePagerAdapter extends FragmentStatePagerAdapter {

    private TimeTableModifier timeTableModifier;
    private TimeTableActivity timeTableActivity;

    public TimeTablePagerAdapter(FragmentManager fm, TimeTableActivity timeTableActivity, TimeTableModifier timeTableModifier) {
        super(fm);
        this.timeTableActivity = timeTableActivity;
        this.timeTableModifier = timeTableModifier;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return timeTableModifier.getTimeTable(position).getName();
    }

    @Override
    public Fragment getItem(int i) {
        TimeTableFragment fragment = new TimeTableFragment(timeTableActivity, timeTableModifier.getTimeTable(i));
        fragment.setTimeTablePagerAdapter(this);
        timeTableModifier.setTimeTableFragment(i, fragment);

        return fragment;
    }

    @Override
    public int getCount() {
        return timeTableModifier.getTimeTableCount();
    }
}
