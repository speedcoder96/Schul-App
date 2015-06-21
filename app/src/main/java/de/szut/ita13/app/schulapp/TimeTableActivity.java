package de.szut.ita13.app.schulapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;

/**
 * Created by Rene on 19.06.2015.
 */
public class TimeTableActivity extends FragmentActivity {
    private ViewPager viewPager;
    private  TimeTablePagerAdapter timeTablePagerAdapter;

    public TimeTableActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_activity_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        TimeTableSetupBundle bundle = new TimeTableSetupBundle.Builder(this)
                .setStartTime(new CalendarTime(8,10))
                .setBreakLength(20)
                .setBreakInterval(2)
                .setTwoWeeksRhythm(true)
                .setLessonLength(45)
                .setLessonCount(10)
                .build();
        ArrayList<TimeTable> timeTables = new ArrayList<>();
        timeTables.add(new TimeTable(bundle));
        timeTables.add(new TimeTable(bundle));
        timeTablePagerAdapter = new TimeTablePagerAdapter(getSupportFragmentManager(), timeTables);
        viewPager.setAdapter(timeTablePagerAdapter);
    }

    public class TimeTablePagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<TimeTable> timeTables;

        public TimeTablePagerAdapter(FragmentManager fm, ArrayList<TimeTable> timeTables) {
            super(fm);
            this.timeTables = timeTables;
        }

        @Override
        public Fragment getItem(int i) {
            return new TimeTableFragment(TimeTableActivity.this, timeTables.get(i));
        }

        @Override
        public int getCount() {
            return timeTables.size();
        }
    }

    public class TimeTableFragment extends Fragment{
        private TimeTableAdapter timeTableAdapter;

        public TimeTableFragment(Context context, TimeTable timeTable){
            timeTableAdapter = new TimeTableAdapter(context, timeTable);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.timetable_pagefragment_layout, container, false);
            ListView listView = (ListView) view.findViewById(R.id.timetableList);
            listView.setAdapter(timeTableAdapter);
            return view;
        }
    }
}
