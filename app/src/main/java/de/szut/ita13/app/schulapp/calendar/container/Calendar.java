package de.szut.ita13.app.schulapp.calendar.container;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAdapter;
import de.szut.ita13.app.schulapp.calendar.dao.CalendarDataSource;

/**
 * Created by Rene on 29.04.2015.
 */
public class Calendar implements ViewPager.OnPageChangeListener {

    public static CalendarMap calendarMap;
    public static CalendarDataSource dataSource;

    private static FragmentActivity calendarActivity;
    private CalendarMonth[] calendarMonths;

    private CalendarViewPagerAdapter calendarViewPagerAdapter;
    private ViewPager viewPager;

    public Calendar(FragmentActivity calendarActivity) {
        this.calendarMap = new CalendarMap();
        dataSource = new CalendarDataSource(calendarActivity);

        Calendar.calendarActivity = calendarActivity;
        Calendar.calendarActivity.setContentView(R.layout.activity_calendar);

        calendarMonths = CalendarMonth.generateDefaultMonths(this, 6, 2015);

        calendarViewPagerAdapter = new CalendarViewPagerAdapter(calendarActivity.getSupportFragmentManager(), this);

        viewPager = (ViewPager)calendarActivity.findViewById(R.id.pager);
        viewPager.setOnPageChangeListener(this);

        viewPager.setAdapter(calendarViewPagerAdapter);
        viewPager.setCurrentItem(1);
    }

    public void generateNext(CalendarMonth calendarMonth) {
        int month = calendarMonth.getMonthIndex();
        int year = calendarMonth.getYear();
        calendarMonths = CalendarMonth.generateDefaultMonths(this, month, year);
        calendarViewPagerAdapter.notifyDataSetChanged();

    }

    public static FragmentActivity getCalendarActivity() {
        return calendarActivity;
    }

    public CalendarMonth[] getCalendarMonths() {
        return calendarMonths;
    }

    public CalendarMonth getCalendarMonth(int pointer) {
        switch(pointer) {
            case CalendarMonth.PREVIOUS_MONTH:
                return calendarMonths[CalendarMonth.PREVIOUS_MONTH];
            case CalendarMonth.CURRENT_MONTH:
                return calendarMonths[CalendarMonth.CURRENT_MONTH];
            case CalendarMonth.NEXT_MONTH:
                return calendarMonths[CalendarMonth.NEXT_MONTH];
            default:
                return null;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 2) {
            generateNext(calendarMonths[position]);
            viewPager.setCurrentItem(1);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class CalendarViewPagerAdapter extends FragmentPagerAdapter {

        private Calendar calendar;

        public CalendarViewPagerAdapter(FragmentManager fm, Calendar calendar) {
            super(fm);
            this.calendar = calendar;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return calendar.getCalendarMonths()[position].getTitleString();
        }

        @Override
        public Fragment getItem(int position) {
            CalendarMonth calendarMonth = calendar.getCalendarMonth(position);
            Fragment fragment = new CalendarViewPagerFragment(calendar, calendarMonth);
            return fragment;
        }

        @Override
        public int getCount() {
            return calendar.getCalendarMonths().length;
        }
    }

    public static class CalendarViewPagerFragment extends Fragment {

        private Calendar calendar;
        private CalendarMonth calendarMonth;

        public CalendarViewPagerFragment(Calendar calendar, CalendarMonth calendarMonth) {
            this.calendar = calendar;
            this.calendarMonth = calendarMonth;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.calendar_view_pager_fragment_layout, container, false);

            ListView listView = (ListView)rootView.findViewById(R.id.calendar);
            listView.setAdapter(new CalendarAdapter(getCalendarActivity(), calendar, calendarMonth));
            Log.d("Calendar", "Generate");

            return rootView;
        }
    }

}
