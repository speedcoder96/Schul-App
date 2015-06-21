package de.szut.ita13.app.schulapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTablePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<TimeTable> timeTables;
    private Context context;

    public TimeTablePagerAdapter(FragmentManager fm, Context context, ArrayList<TimeTable> timeTables) {
        super(fm);
        this.context = context;
        this.timeTables = timeTables;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return timeTables.get(position).getName();
    }

    @Override
    public Fragment getItem(int i) {
        return new TimeTableFragment(context, timeTables.get(i));
    }

    @Override
    public int getCount() {
        return timeTables.size();
    }
}
